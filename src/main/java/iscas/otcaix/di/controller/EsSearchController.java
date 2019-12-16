package iscas.otcaix.di.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import iscas.otcaix.di.pojo.EsQuery;
import iscas.otcaix.di.pojo.EsResult;
import iscas.otcaix.di.service.impl.EsSearchService;

@Controller
public class EsSearchController {

    @Autowired
    private EsSearchService esService;

    public EsSearchService getEsService() {
        return esService;
    }

    public void setEsService(EsSearchService esService) {
        this.esService = esService;
    }

    @RequestMapping(value = "/esindex")
    public String EsIndex() {
        return "health_search";
    }

    @RequestMapping(value = "/medical_portrait")
    public String MedicalPortrait() {
        return "medical_portrait";
    }

    @RequestMapping(value = "/medical_record")
    public String MedicalRecord() {
        return "medical_record";
    }

    @ResponseBody
    @RequestMapping(value = "/medical/inpatient", method = RequestMethod.GET)
    public Map<String, Object> getEsInpatientByZyh(@RequestParam(value = "zyh", required = true) String zyh) {
        return esService.getEsInpatientByZyh(zyh);
    }

    @RequestMapping(value = "/createEsIndex")
    @ResponseBody
    public Map<String, String> createEsIndex() {
        Map<String, String> response = esService.createInpatientIndex();
        return response;
    }

    @RequestMapping(value = "/fulltextSearch")
    @ResponseBody
    public EsResult fulltextSearch(@RequestParam(value = "content", required = true) String content,
                                   @RequestParam(value = "dbtype", required = true) String dbtype,
                                   @RequestParam(required = false, defaultValue = "0") Integer pagefrom,
                                   @RequestParam(required = false, defaultValue = "10") Integer pagesize) throws UnsupportedEncodingException {
        String queryContent = URLDecoder.decode(content, "utf-8");
        EsResult esResult = esService.fulltextSearchIndex(queryContent, dbtype, pagefrom, pagesize);
        return esResult;
    }

    @RequestMapping(value = "/similaritySearch")
    @ResponseBody
    public EsResult similaritySearch(@RequestParam(value = "content", required = true) String content,
                                     @RequestParam(value = "dbtype", required = true) String dbtype,
                                     @RequestParam(required = false, defaultValue = "0") Integer pagefrom,
                                     @RequestParam(required = false, defaultValue = "10") Integer pagesize) throws UnsupportedEncodingException {
        // String queryContent = URLDecoder.decode(content,"utf-8");
        // EsResult esResult = esService.fulltextSearchIndex(queryContent,
        // dbtype, pagefrom, pagesize);
        /*
		 * 脑血管疾病
		 */
        String[] ss = "心肌缺血,颈内动脉-海绵窦瘘".split(",");
        Random random = new Random();
        List<String> querys = new ArrayList<String>();
        for (String s : ss)
            querys.add(s);
        EsResult esResult = esService.fulltextSearchIndex(querys.get(random.nextInt(ss.length)), dbtype, pagefrom,
                pagesize);
        return esResult;
    }

    @RequestMapping(value = "/advancedSearch")
    @ResponseBody
    public EsResult advancedSearch(@RequestParam(value = "es_age", required = true) String es_age,
                                   @RequestParam(value = "es_sex", required = true) String es_sex,
                                   @RequestParam(value = "es_zd", required = false) String es_zd,
                                   @RequestParam(value = "es_zs", required = false) String es_zs) {
        EsResult esResult = esService.advancedSearchIndex(es_age, es_sex, es_zd, es_zs);
        return esResult;
    }

    @RequestMapping(value = "/multiTermSearch", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public EsResult multiTermSearch(@RequestBody EsQuery es_query) {
        EsResult esResult = esService.multiTermSearch(es_query);
        return esResult;
    }
}