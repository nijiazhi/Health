package iscas.otcaix.di.service.impl;

import java.util.*;
import java.util.Map.Entry;

import iscas.otcaix.di.pojo.*;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.Client;
import org.elasticsearch.cluster.health.ClusterHealthStatus;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.highlight.HighlightField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import iscas.otcaix.di.dao.impl.ElasticPatientDao;
import iscas.otcaix.di.util.ESUtil;

@Service
public class EsSearchService {
    private final static Logger logger = LoggerFactory.getLogger(EsSearchService.class);

    private static final String esIndexName = "healthcare";

    @Autowired
    private ElasticPatientDao esPatientDao;

    public Map<String, String> createInpatientIndex() {
        Map<String, String> rs_map = new HashMap<String, String>();
        try {
            System.out.println("\n**************** ElasticSearch createIndex started *********************\n");
            long startDate = System.currentTimeMillis();
            Client esClient = ESUtil.getClient();
            Gson gson = new Gson();

            System.out.println("\n\t***** Screen Index started ********\n");
            //住院  Inpatient
            List<EsInpatient> inpatient_list = esPatientDao.getEsInpatientList();
            for (EsInpatient esMedical : inpatient_list) {
                System.out.println("Inpatient " + esMedical.getZyh());
                esClient.prepareIndex(esIndexName, "inpatient").setSource(gson.toJson(esMedical)).execute().actionGet();
            }
            System.out.println("\n\t**************** Screen Index finished *********************\n");

            esClient.close();
            long endDate = System.currentTimeMillis();
            System.out.println("\n**************** ElasticSearch createIndex finished *********************\n");

            rs_map.put("info", "success");
            rs_map.put("detail", "索引构建成功，总共耗时：" + (endDate - startDate) / 1000 + "秒");
        } catch (Exception e) {
            // TODO: handle exception
            rs_map.put("info", "error");
            rs_map.put("detail", e.getMessage());
        }
        System.out.println(new Gson().toJson(rs_map));
        return rs_map;
    }

    public ElasticPatientDao getEsPatientDao() {
        return esPatientDao;
    }

    public void setEsPatientDao(ElasticPatientDao esPatientDao) {
        this.esPatientDao = esPatientDao;
    }

    /**
     * @param text     全文检索-待查询内容
     * @param dbtype   医疗数据库种类：住院、筛查
     * @param pagefrom
     * @param pagesize
     * @return
     */
    public EsResult fulltextSearchIndex(String text, String dbtype, int pagefrom, int pagesize) {
        EsResult esResult = new EsResult();
        if (text == null || text.isEmpty()) {
            esResult = searchMatchAllIndex(dbtype, pagefrom, pagesize);
        } else {
            esResult = searchSimpleIndex(text, dbtype, pagefrom, pagesize);
        }
        return esResult;
    }

    public EsResult advancedSearchIndex(String es_age, String es_sex, String es_zd, String es_zs) {
        EsResult esResult = new EsResult();

        String ages[] = es_age.split(",");
        int start_age = Integer.parseInt(ages[0]);
        int end_age = Integer.parseInt(ages[1]);

        Client esClient = ESUtil.getClient();

        // 创建查询索引
        SearchRequestBuilder searchReq = esClient.prepareSearch(esIndexName);

        // 设置查询索引类型
        searchReq.setTypes("inpatient", "screening");

        // 1.SearchType.DFS_QUERY_THEN_FETCH = 精确查询
        // 2.SearchType.SCAN = 扫描查询,无序
        searchReq.setSearchType(SearchType.DFS_QUERY_THEN_FETCH);

        // 构造全文或关系的查询
        BoolQueryBuilder bb = QueryBuilders.boolQuery();
        if (!es_zd.isEmpty()) {
            bb = bb.should(QueryBuilders.matchQuery("cyzdbm", es_zd));
        }
        if (!es_zs.isEmpty()) {
            bb = bb.should(QueryBuilders.matchQuery("inrecord.ryzs", es_zs));
        }

        // 构造精确的并且查询
        BoolQueryBuilder bb1 = QueryBuilders.boolQuery();
        if (es_sex.equals("1")) {
            // 男
            bb1 = bb1.must(bb);
            bb1 = bb1.must(QueryBuilders.termQuery("xb", es_sex));
        } else if (es_sex.equals("2")) {
            // 女
            bb1 = bb1.must(bb);
            bb1 = bb1.must(QueryBuilders.termQuery("xb", es_sex));
        } else {
            // 全部
            bb1 = bb1.must(bb);
        }

        // 设置查询关键词
        searchReq.setQuery(bb1);
        searchReq.setPostFilter(QueryBuilders.rangeQuery("nl").from(start_age).to(end_age));

        // 分页应用
        searchReq.setFrom(0).setSize(10);

        // 设置是否按查询匹配度排序
        searchReq.setExplain(true);

        // 设置高亮显示
        searchReq.addHighlightedField("xb");
        searchReq.addHighlightedField("cyzdbm");
        searchReq.addHighlightedField("inrecord.ryzs");
        searchReq.setHighlighterPreTags("<span style=\"color:red\">");
        searchReq.setHighlighterPostTags("</span>");
        // 执行搜索,返回搜索响应信息
        SearchResponse sp = searchReq.execute().actionGet();
        esResult.setTotal(sp.getHits().getTotalHits());

        List<Map<String, Object>> lists = getHitObjs(sp);
        System.out.println("search success ..");
        esClient.close();
        esResult.setLists(lists);
        return esResult;
    }

    public EsResult multiTermSearch(EsQuery es_query) {
        EsResult esResult = new EsResult();

        Client esClient = ESUtil.getClient();

        // 创建查询索引
        SearchRequestBuilder searchReq = esClient.prepareSearch(esIndexName);
        // 设置查询索引类型
        searchReq.setTypes("inpatient", "screening");
        // 扫描查询,无序
        searchReq.setSearchType(SearchType.DFS_QUERY_THEN_FETCH);

        // 构造全文或关系的查询
        BoolQueryBuilder bb = QueryBuilders.boolQuery();
        List<EsItem> items = es_query.getItems();
        for (EsItem esItem : items) {
            if (esItem.getCondition().equals("0")) {
                // 与
                if (!esItem.getValue().isEmpty()) {
                    if (esItem.getMatch().equals("0")) {
                        // 模糊
                        bb = bb.must(QueryBuilders.matchQuery(esItem.getType(), esItem.getValue()));
                    } else {
                        // 精确
                        bb = bb.must(QueryBuilders.termQuery(esItem.getType(), esItem.getValue()));
                    }
                }
            } else if (esItem.getCondition().equals("1")) {
                // 或
                if (!esItem.getValue().isEmpty()) {
                    if (esItem.getMatch().equals("0")) {
                        // 模糊
                        bb = bb.should(QueryBuilders.matchQuery(esItem.getType(), esItem.getValue()));
                    } else {
                        // 精确
                        bb = bb.should(QueryBuilders.termQuery(esItem.getType(), esItem.getValue()));
                    }
                }
            } else {
                // 非
                if (!esItem.getValue().isEmpty()) {
                    if (esItem.getMatch().equals("0")) {
                        // 模糊
                        bb = bb.mustNot(QueryBuilders.matchQuery(esItem.getType(), esItem.getValue()));
                    } else {
                        // 精确
                        bb = bb.mustNot(QueryBuilders.termQuery(esItem.getType(), esItem.getValue()));
                    }
                }
            }
        }

        // 构造精确的并且查询
        BoolQueryBuilder bb1 = QueryBuilders.boolQuery();
        bb1 = bb1.must(bb);
        // 性别
        String es_sex = es_query.getSex();
        if (es_sex.equals("1")) { // 男

            bb1 = bb1.must(QueryBuilders.termQuery("xb", es_sex));
        } else if (es_sex.equals("2")) {
            // 女
            bb1 = bb1.must(QueryBuilders.termQuery("xb", es_sex));
        }

        // 设置查询关键词
        searchReq.setQuery(bb1);
        // 年龄
        String es_age = es_query.getAge();
        String ages[] = es_age.split(",");
        int start_age = Integer.parseInt(ages[0]);
        int end_age = Integer.parseInt(ages[1]);
        searchReq.setPostFilter(QueryBuilders.rangeQuery("nl").from(start_age).to(end_age));

        // 分页应用
        searchReq.setFrom(es_query.getPagefrom()).setSize(es_query.getPagesize());

        // 设置是否按查询匹配度排序
        searchReq.setExplain(true);

        // 设置高亮显示
        for (EsItem esItem : items) {
            searchReq.addHighlightedField(esItem.getType());
        }
        searchReq.setHighlighterPreTags("<span style=\"color:red\">");
        searchReq.setHighlighterPostTags("</span>");
        // 执行搜索,返回搜索响应信息
        SearchResponse sp = searchReq.execute().actionGet();
        esResult.setTotal(sp.getHits().getTotalHits());

        List<Map<String, Object>> lists = getHitObjs(sp);
        System.out.println("search success ..");
        esClient.close();
        esResult.setLists(lists);
        return esResult;
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getHitObjs(SearchResponse sp) {
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        for (SearchHit hit : sp.getHits().getHits()) {
            Map<String, HighlightField> map_field = hit.getHighlightFields();
            Iterator<Entry<String, HighlightField>> iter = map_field.entrySet().iterator();
            while (iter.hasNext()) {
                @SuppressWarnings("rawtypes")
                Map.Entry entry = (Map.Entry) iter.next();
                String field = (String) entry.getKey();
                HighlightField hField = (HighlightField) entry.getValue();

                String value = "";
                for (Text t : hField.fragments()) {
                    value += t;
                }
                System.out.println(field + "  :  " + value);
                String strs[] = field.split("\\.");
                if (strs.length == 1) {
                    if (field.equals("jybgbdmcs") || field.equals("yxlgs") || field.equals("ssczmcs")) {
                        List<String> obj_list = new ArrayList<String>();
                        obj_list.add(value);
                        hit.getSource().put(field, obj_list);
                    } else {
                        hit.getSource().put(field, value);
                    }
                } else {
                    Map<String, Object> map_sub = (Map<String, Object>) hit.getSource().get(strs[0]);
                    for (int i = 0; i < strs.length - 1; i++) {
                        String string = strs[i];
                        map_sub = (Map<String, Object>) hit.getSource().get(string);
                    }
                    map_sub.put(strs[strs.length - 1], value);
                }
            }
            hit.getSource().put("_type", hit.getType());
            hit.getSource().put("_score", hit.getScore());
            lists.add(hit.getSource());
        }
        return lists;
    }

    /**
     * @param dbtype   医疗数据库种类：住院、筛查
     * @param pagefrom 起始页面
     * @param pagesize 界面内容
     * @return
     */
    private EsResult searchMatchAllIndex(String dbtype, int pagefrom, int pagesize) {
        EsResult esResult = new EsResult();
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        Client esClient = ESUtil.getClient();
        // List<String> rs_list = new ArrayList<String>();
        SearchRequestBuilder searchReq = esClient.prepareSearch(esIndexName);
        if (dbtype.equals("11")) {
            searchReq.setTypes("inpatient", "screening");
        } else {
            searchReq.setTypes("inpatient");
        }
        //设置分页，windows下10000限制
        searchReq.setFrom(pagefrom).setSize(pagesize);

        //执行查询
        SearchResponse sp = searchReq.execute().actionGet();
        // SearchResponse sp =
        // esClient.prepareSearch(esIndexName).execute().actionGet();
        esResult.setTotal(sp.getHits().getTotalHits());
        System.out.println("命中 total = " + sp.getHits().getTotalHits());
        System.out.println("显示 total = " + sp.getHits().getHits().length);
        for (SearchHit hit : sp.getHits().getHits()) {
//            String sourceAsString = hit.sourceAsString();// 以字符串方式打印
//            System.out.println(sourceAsString);
            // rs_list.add(sourceAsString);
            hit.getSource().put("_type", hit.getType());
            hit.getSource().put("_score", hit.getScore());
            lists.add(hit.getSource());
        }
        esResult.setLists(lists);
        esClient.close();
        return esResult;
    }

    private void test1() {
        String INDEX = "index_name";
        String TYPE = "type_name";
        Client client = ESUtil.getClient();
        System.out.println("from size 模式启动！");
        Date begin = new Date();
        long count = client.prepareCount(INDEX).setTypes(TYPE).execute().actionGet().getCount();
        SearchRequestBuilder requestBuilder = client.prepareSearch(INDEX).setTypes(TYPE).setQuery(QueryBuilders.matchAllQuery());
        for(int i=0,sum=0; sum<count; i++){
            SearchResponse response = requestBuilder.setFrom(i).setSize(50000).execute().actionGet();
            sum += response.getHits().hits().length;
            System.out.println("总量"+count+" 已经查到"+sum);
        }
        Date end = new Date();
        System.out.println("耗时: "+(end.getTime()-begin.getTime()));
    }

    private void test2() {
        String INDEX = "index_name";
        String TYPE = "type_name";
        Client client = ESUtil.getClient();
        System.out.println("scroll 模式启动！");
        Date begin = new Date();
        SearchResponse scrollResponse = client.prepareSearch(INDEX)
                .setSearchType(SearchType.SCAN).setSize(10000).setScroll(TimeValue.timeValueMinutes(1))
                .execute().actionGet();
        long count = scrollResponse.getHits().getTotalHits();//第一次不返回数据
        for(int i=0,sum=0; sum<count; i++){
            scrollResponse = client.prepareSearchScroll(scrollResponse.getScrollId())
                    .setScroll(TimeValue.timeValueMinutes(8))
                    .execute().actionGet();
            sum += scrollResponse.getHits().hits().length;
            System.out.println("总量"+count+" 已经查到"+sum);
        }
        Date end = new Date();
        System.out.println("耗时: "+(end.getTime()-begin.getTime()));
    }

    public static String searchByScroll(Client client) {
        String index = "simple-index";
        String type = "simple-type";
// 搜索条件
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch();
        searchRequestBuilder.setIndices(index);
        searchRequestBuilder.setTypes(type);
        searchRequestBuilder.setScroll(new TimeValue(30000));
// 执行
        SearchResponse searchResponse = searchRequestBuilder.get();
        String scrollId = searchResponse.getScrollId();
        logger.info("--------- searchByScroll scrollID {}", scrollId);
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        for (SearchHit searchHit : searchHits) {
            String source = searchHit.getSource().toString();
            logger.info("--------- searchByScroll source {}", source);
        } // for
        return scrollId;
    }

    /**
     * 通过滚动ID获取文档
     * @param client
     * @param scrollId
     */
    public static void searchByScrollId(Client client, String scrollId){
        TimeValue timeValue = new TimeValue(30000);
        SearchScrollRequestBuilder searchScrollRequestBuilder;
        SearchResponse response;
// 结果
        while (true) {
            logger.info("--------- searchByScroll scrollID {}", scrollId);
            searchScrollRequestBuilder = client.prepareSearchScroll(scrollId);
// 重新设定滚动时间
            searchScrollRequestBuilder.setScroll(timeValue);
// 请求
            response = searchScrollRequestBuilder.get();
// 每次返回下一个批次结果 直到没有结果返回时停止 即hits数组空时
            if (response.getHits().getHits().length == 0) {
                break;
            } // if
// 这一批次结果
            SearchHit[] searchHits = response.getHits().getHits();
            for (SearchHit searchHit : searchHits) {
                String source = searchHit.getSource().toString();
                logger.info("--------- searchByScroll source {}", source);
            } // for
// 只有最近的滚动ID才能被使用
            scrollId = response.getScrollId();
        } // while
    }

    /**
     * 清除滚动ID
     * @param client
     * @param scrollId
     * @return
     */
    public static boolean clearScroll(Client client, String scrollId){
        ClearScrollRequestBuilder clearScrollRequestBuilder = client.prepareClearScroll();
        clearScrollRequestBuilder.addScrollId(scrollId);
        ClearScrollResponse response = clearScrollRequestBuilder.get();
        return response.isSucceeded();
    }

    private EsResult searchSimpleIndex(String text, String dbtype, int pagefrom, int pagesize) {
        EsResult esResult = new EsResult();
        Client esClient = ESUtil.getClient();

        // 创建查询索引
        SearchRequestBuilder searchReq = esClient.prepareSearch(esIndexName);

        // 设置查询索引类型
        if (dbtype.equals("11")) {
            searchReq.setTypes("inpatient", "screening");
        } else {
            searchReq.setTypes("inpatient");
        }

        // 1.SearchType.DFS_QUERY_THEN_FETCH = 精确查询
        // 2.SearchType.SCAN =扫描查询,无序
        searchReq.setSearchType(SearchType.DFS_QUERY_THEN_FETCH);

        // 设置查询关键词
        QueryStringQueryBuilder queryStr = QueryBuilders.queryStringQuery(text);
        // 住院
        queryStr.field("zyh");
        queryStr.field("dwbm");// 医院
        queryStr.field("rykbmc");// 科室
        queryStr.field("cyzdbm");// 诊断
        queryStr.field("lyfsmc");// 转归
        queryStr.field("jybgbdmcs");// 检验
        queryStr.field("yxlgs");// 影像结果
        queryStr.field("ssczmcs");// 手术
        queryStr.field("inrecord.ryzs");
        queryStr.field("inrecord.jws");
        queryStr.field("inrecord.grs");
        // 筛查
        queryStr.field("uuName");
        queryStr.field("dfStroke");
        queryStr.field("dfTia");
        queryStr.field("dfHypertension");
        queryStr.field("dfAF");
        queryStr.field("dfSmoking");
        queryStr.field("dfLDL");
        queryStr.field("dfGlycuresis");
        queryStr.field("dfSportsLack");
        queryStr.field("dfOverweight");
        queryStr.field("dfStrokeFamily");
        queryStr.field("dfStatus");
        searchReq.setQuery(QueryBuilders.boolQuery().should(queryStr));

        // searchReq.setMinScore(10);

        // 分页应用
        searchReq.setFrom(pagefrom).setSize(pagesize);

        // 设置是否按查询匹配度排序
        searchReq.setExplain(true);

        // 设置高亮显示
        // 住院
        searchReq.addHighlightedField("zyh");
        searchReq.addHighlightedField("dwbm");// 医院
        searchReq.addHighlightedField("rykbmc");// 科室
        searchReq.addHighlightedField("cyzdbm");// 诊断
        searchReq.addHighlightedField("lyfsmc");// 转归
        searchReq.addHighlightedField("jybgbdmcs");// 检验
        searchReq.addHighlightedField("yxlgs");// 影像结果
        searchReq.addHighlightedField("ssczmcs");// 手术
        searchReq.addHighlightedField("inrecord.ryzs");
        searchReq.addHighlightedField("inrecord.jws");
        searchReq.addHighlightedField("inrecord.grs");
        // 筛查
        searchReq.addHighlightedField("uuName");
        searchReq.addHighlightedField("dfStroke");
        searchReq.addHighlightedField("dfTia");
        searchReq.addHighlightedField("dfHypertension");
        searchReq.addHighlightedField("dfAF");
        searchReq.addHighlightedField("dfSmoking");
        searchReq.addHighlightedField("dfLDL");
        searchReq.addHighlightedField("dfGlycuresis");
        searchReq.addHighlightedField("dfSportsLack");
        searchReq.addHighlightedField("dfOverweight");
        searchReq.addHighlightedField("dfStrokeFamily");
        searchReq.addHighlightedField("dfStatus");

        searchReq.setHighlighterPreTags("<span style=\"color:red\">");
        searchReq.setHighlighterPostTags("</span>");
        // 执行搜索,返回搜索响应信息
        SearchResponse sp = searchReq.execute().actionGet();
        esResult.setTotal(sp.getHits().getTotalHits());

        // 获取搜索的文档结果
        // String rules = lable.getRules();
        // Gson gson = new Gson();
        // Type listType = new TypeToken<MtRuleMatch>() {
        // }.getType();
        // MtRuleMatch rule_match = gson.fromJson(rules, listType);

        List<Map<String, Object>> lists = getHitObjs(sp);
        System.out.println("search success .. " + lists.size());
        esClient.close();
        esResult.setLists(lists);
        return esResult;
    }

    /**
     * 检查健康状态
     *
     * @return
     */
    public boolean ping() {
        Client esClient = ESUtil.getClient();
        try {
            ActionFuture<ClusterHealthResponse> health = esClient.admin().cluster().health(new ClusterHealthRequest());
            ClusterHealthStatus status = health.actionGet().getStatus();
            if (status.value() == ClusterHealthStatus.RED.value()) {
                throw new RuntimeException("elasticsearch cluster health status is red.");
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            esClient.close();
        }
    }

    public Map<String, Object> getEsInpatientByZyh(String zyh) {
        Map<String, Object> result = new HashMap<String, Object>();
        Client esClient = ESUtil.getClient();

        // 创建查询索引
        SearchRequestBuilder searchReq = esClient.prepareSearch(esIndexName);
        // 设置查询索引类型
        searchReq.setTypes("inpatient", "screening");
        // 扫描查询,无序
        searchReq.setSearchType(SearchType.DFS_QUERY_THEN_FETCH);

        // 构造精确的并且查询
        BoolQueryBuilder bb = QueryBuilders.boolQuery();
        bb = bb.must(QueryBuilders.termQuery("zyh", zyh));

        // 设置查询关键词
        searchReq.setQuery(bb);

        // 执行搜索,返回搜索响应信息
        SearchResponse sp = searchReq.execute().actionGet();
        long count = sp.getHits().getTotalHits();
        System.out.println(count);
        SearchHit[] hits = sp.getHits().getHits();
        if (hits.length == 1) {
            SearchHit hit = hits[0];
            result = hit.getSource();
            Gson gson = new Gson();
            System.out.println(gson.toJson(result));
            System.out.println("\n");
            String json = hit.getSourceAsString();
            System.out.println(json);
        }
        System.out.println("search success ..");
        esClient.close();
        return result;
    }
}
