package iscas.otcaix.di.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import iscas.otcaix.di.pojo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import iscas.otcaix.di.dao.impl.TbPatientDao;

@Service
public class MedicalPortraitService{
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(MedicalPortraitService.class);
	
	private static final String TABLE_LABLE_DEFINE = "mt_tl001_lable_define";
	private static final String TABLE_LABLE_FIELD = "mt_tl001_lable_field";
	
	@Resource
	private TbPatientDao labledefineMapper;


	public TbPatientDao getLabledefineMapper() {
		return labledefineMapper;
	}

	public void setLabledefineMapper(TbPatientDao labledefineMapper) {
		this.labledefineMapper = labledefineMapper;
	}

	public Map<String, List<String>> getTaLableInfoByZD() {
		StringBuffer buffer_info = new StringBuffer();
		List<TaLableInfo> info_list = labledefineMapper.getTaLableInfoByName(TABLE_LABLE_DEFINE, TABLE_LABLE_FIELD, "诊断及分型%");

		List<TaLableInfo> in_list = new ArrayList<TaLableInfo>();
		List<TaLableInfo> out_list = new ArrayList<TaLableInfo>();
		HashSet<String> lable_set = new HashSet<String>();
		for (TaLableInfo taLableInfo : info_list) {
			String names[] = taLableInfo.getLable_name().split("-");
			taLableInfo.setParent_name(names[0]);
			taLableInfo.setLable_name(names[1].trim());
			lable_set.add(taLableInfo.getLable_value().trim());
			if (taLableInfo.getLable_name().equals("入院诊断")) {
				in_list.add(taLableInfo);
			} else {
				out_list.add(taLableInfo);
			}
		}
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("xAxis", new ArrayList<String>(lable_set));
		List<String> in_val_list = new ArrayList<String>();
		List<String> out_val_list = new ArrayList<String>();
		List<String> io_val_list = new ArrayList<String>();
		List<String> is_val_list = new ArrayList<String>();
		List<String> tmp_val_list = new ArrayList<String>();
		long in_count = 0L;
		long out_count = 0L;
		long io_count = 0L;
		for (String lable : lable_set) {
			long in_value = 0L;
			for (TaLableInfo taLableInfo : in_list) {
				if (lable.equals(taLableInfo.getLable_value())) {
					in_value = taLableInfo.getPatient_num();
					break;
				}
			}
			in_count += in_value;
			in_val_list.add(Long.toString(in_value));
			long out_value = 0L;
			for (TaLableInfo taLableInfo : out_list) {
				if (lable.equals(taLableInfo.getLable_value())) {
					out_value = taLableInfo.getPatient_num();
					break;
				}
			}
			out_val_list.add(Long.toString(out_value));
			out_count += out_value;
			List<String> is_list = labledefineMapper.getDiagnosisZyhByName(TABLE_LABLE_DEFINE, TABLE_LABLE_FIELD, "诊断及分型%", lable);
			io_count += is_list.size();
			DecimalFormat df = new DecimalFormat("0.00");
			is_val_list.add(df.format(Double.valueOf(is_list.size() * 100.0 / in_value)));
			tmp_val_list.add(Long.toString(is_list.size()));
		}
		buffer_info.append("根据住院数据标签体系，匹配病案情况为：");
		buffer_info.append("入院诊断病案数" + in_count + "个，");
		buffer_info.append("出院诊断病案数" + out_count + "个，");
		buffer_info.append("其中确诊病案数" + io_count + "个。");
		map.put("ryzd", in_val_list);
		map.put("cyzd", out_val_list);
		map.put("iszd", is_val_list);
		map.put("qrzd", tmp_val_list);
		io_val_list.add(buffer_info.toString());
		map.put("text", io_val_list);
		return map;
	}

	public Map<String, List<String>> getTaLableInfoByJY() {
		// TODO Auto-generated method stub
		StringBuffer buffer_info = new StringBuffer();
		List<String> key_list = new ArrayList<String>();
		List<String> val_list = new ArrayList<String>();
		List<String> io_list = new ArrayList<String>();
		List<TaLableInfo> info_list = labledefineMapper.getTaLableInfoByName(TABLE_LABLE_DEFINE, TABLE_LABLE_FIELD, "检验-检查项目%");
		List<String> zyh_list1 = labledefineMapper.getMultiTagZyhByName(TABLE_LABLE_DEFINE, TABLE_LABLE_FIELD, "%检查项目", 1L);
		List<String> zyh_list2 = labledefineMapper.getMultiTagZyhByName(TABLE_LABLE_DEFINE, TABLE_LABLE_FIELD, "%检查项目", 2L);
		List<String> zyh_list3 = labledefineMapper.getMultiTagZyhByName(TABLE_LABLE_DEFINE, TABLE_LABLE_FIELD, "%检查项目", 3L);
		List<String> zyh_list4 = labledefineMapper.getMultiTagZyhByName(TABLE_LABLE_DEFINE, TABLE_LABLE_FIELD, "%检查项目", 4L);
		for (TaLableInfo taLableInfo : info_list) {
			String names[] = taLableInfo.getLable_name().split("-");
			taLableInfo.setParent_name(names[0]);
			taLableInfo.setLable_name(names[1].trim());
			key_list.add(taLableInfo.getLable_value().trim());
			val_list.add(Long.toString(taLableInfo.getPatient_num()));
		}
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("xAxis", key_list);
		map.put("value", val_list);

		buffer_info.append("根据住院数据标签体系，匹配病案情况为：");
		buffer_info.append("进行检验的病案数有" + zyh_list1.size() + "个，");
		buffer_info.append("其中，进行检验2个以上检查项目的病案数有" + zyh_list2.size() + "个，");
		buffer_info.append("进行检验3个以上检查项目的病案数有" + zyh_list3.size() + "个，");
		buffer_info.append("进行检验4个以上检查项目的病案数有" + zyh_list4.size() + "个。");
		io_list.add(buffer_info.toString());
		map.put("text", io_list);
		return map;
	}

	
	public Map<String, List<String>> getTaLableInfoBySS() {
		// TODO Auto-generated method stub
		StringBuffer buffer_info = new StringBuffer();
		List<String> key_list = new ArrayList<String>();
		List<String> val_list = new ArrayList<String>();
		List<String> io_list = new ArrayList<String>();
		List<TaLableInfo> info_list = labledefineMapper.getTaLableInfoByName(TABLE_LABLE_DEFINE, TABLE_LABLE_FIELD, "手术%");
		List<String> zyh_list1 = labledefineMapper.getMultiTagZyhByName(TABLE_LABLE_DEFINE, TABLE_LABLE_FIELD, "%手术操作", 1L);
		List<String> zyh_list2 = labledefineMapper.getMultiTagZyhByName(TABLE_LABLE_DEFINE, TABLE_LABLE_FIELD, "%手术操作", 2L);
		List<String> zyh_list3 = labledefineMapper.getMultiTagZyhByName(TABLE_LABLE_DEFINE, TABLE_LABLE_FIELD, "%手术操作", 3L);
		for (TaLableInfo taLableInfo : info_list) {
			String names[] = taLableInfo.getLable_name().split("-");
			taLableInfo.setParent_name(names[0]);
			taLableInfo.setLable_name(names[1].trim());
			key_list.add(taLableInfo.getLable_value().trim());
			val_list.add(Long.toString(taLableInfo.getPatient_num()));
		}
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("xAxis", key_list);
		map.put("value", val_list);

		buffer_info.append("根据住院数据标签体系，匹配病案情况为：");
		buffer_info.append("进行手术的病案数有" + zyh_list1.size() + "个，");
		buffer_info.append("其中，进行手术2个以上手术操作的病案数有" + zyh_list2.size() + "个，");
		buffer_info.append("进行手术3个以上手术操作的病案数有" + zyh_list3.size() + "个。");
		io_list.add(buffer_info.toString());
		map.put("text", io_list);
		return map;
	}

	
	public Map<String, List<String>> getTaLableInfoByYP() {
		// TODO Auto-generated method stub
		StringBuffer buffer_info = new StringBuffer();
		List<String> key_list = new ArrayList<String>();
		List<String> val_list = new ArrayList<String>();
		List<String> io_list = new ArrayList<String>();
		List<TaLableInfo> info_list = labledefineMapper.getTaLableInfoByName(TABLE_LABLE_DEFINE, TABLE_LABLE_FIELD, "药物%");
		Map<String, List<TaLableInfo>> tagmap = new HashMap<String, List<TaLableInfo>>();
		for (TaLableInfo taLableInfo : info_list) {
			String names[] = taLableInfo.getLable_name().split("-");
			taLableInfo.setParent_name(names[0]);
			taLableInfo.setLable_name(names[1].trim());
			if (tagmap.get(taLableInfo.getLable_name()) == null) {
				List<TaLableInfo> infolist = new ArrayList<TaLableInfo>();
				infolist.add(taLableInfo);
				tagmap.put(taLableInfo.getLable_name(), infolist);
			} else {
				List<TaLableInfo> infolist = tagmap.get(taLableInfo.getLable_name());
				infolist.add(taLableInfo);
			}
		}
		Iterator iter = tagmap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = (String) entry.getKey();
			List<TaLableInfo> val = (List<TaLableInfo>) entry.getValue();
			long val_count = 0L;
			for (TaLableInfo taLableInfo : val) {
				val_count += taLableInfo.getPatient_num();
			}
			key_list.add(key);
			val_list.add(Long.toString(val_count));
		}

		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("xAxis", key_list);
		map.put("value", val_list);

		List<String> key_pie = new ArrayList<String>();
		List<String> val_pie = new ArrayList<String>();
		List<TaLableInfo> pie_list = labledefineMapper.getTaLableInfoByName(TABLE_LABLE_DEFINE, TABLE_LABLE_FIELD, "%降压药");
		for (TaLableInfo taLableInfo : pie_list) {
			key_pie.add(taLableInfo.getLable_value());
			val_pie.add(Long.toString(taLableInfo.getPatient_num()));
		}
		map.put("key_pie", key_pie);
		map.put("val_pie", val_pie);
		return map;
	}

	
	public Map<String, List<String>> getTaLableInfoByYP_FL(String name) {
		// TODO Auto-generated method stub
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		List<String> key_pie = new ArrayList<String>();
		List<String> val_pie = new ArrayList<String>();
		List<TaLableInfo> pie_list = labledefineMapper.getTaLableInfoByName(TABLE_LABLE_DEFINE, TABLE_LABLE_FIELD, "%" + name);
		for (TaLableInfo taLableInfo : pie_list) {
			key_pie.add(taLableInfo.getLable_value());
			val_pie.add(Long.toString(taLableInfo.getPatient_num()));
		}
		map.put("key_pie", key_pie);
		map.put("val_pie", val_pie);
		return map;
	}

	
	public Map<String, List<String>> getTaLableInfoByZD_FX() {
		StringBuffer buffer_info = new StringBuffer();
		List<TaLableInfo> info_list = labledefineMapper.getTaLableInfoByName(TABLE_LABLE_DEFINE, TABLE_LABLE_FIELD, "诊断及分型%");

		List<TaLableInfo> in_list = new ArrayList<TaLableInfo>();
		List<TaLableInfo> out_list = new ArrayList<TaLableInfo>();
		HashSet<String> lable_set = new HashSet<String>();
		for (TaLableInfo taLableInfo : info_list) {
			String names[] = taLableInfo.getLable_name().split("-");
			taLableInfo.setParent_name(names[0]);
			taLableInfo.setLable_name(names[1].trim());
			lable_set.add(taLableInfo.getLable_value().trim());
			if (taLableInfo.getLable_name().equals("入院诊断")) {
				in_list.add(taLableInfo);
			} else {
				out_list.add(taLableInfo);
			}
		}
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("xAxis", new ArrayList<String>(lable_set));
		List<String> in_val_list = new ArrayList<String>();
		List<String> out_val_list = new ArrayList<String>();
		List<String> io_val_list = new ArrayList<String>();
		long in_count = 0L;
		long out_count = 0L;
		long io_count = 0L;
		for (String lable : lable_set) {
			long in_value = 0L;
			for (TaLableInfo taLableInfo : in_list) {
				if (lable.equals(taLableInfo.getLable_value())) {
					in_value = taLableInfo.getPatient_num();
					break;
				}
			}
			in_count += in_value;
			in_val_list.add(Long.toString(in_value));
			long out_value = 0L;
			for (TaLableInfo taLableInfo : out_list) {
				if (lable.equals(taLableInfo.getLable_value())) {
					out_value = taLableInfo.getPatient_num();
					break;
				}
			}
			out_val_list.add(Long.toString(out_value));
			out_count += out_value;
			if (in_value > out_value) {
				io_count += out_value;
			} else {
				io_count += in_value;
			}
		}
		buffer_info.append("根据住院数据标签体系，匹配病案情况为：");
		buffer_info.append("入院诊断病案数" + in_count + "个，");
		buffer_info.append("出院诊断病案数" + out_count + "个，");
		buffer_info.append("其中确诊病案数" + io_count + "个。");
		map.put("ryzd", in_val_list);
		map.put("cyzd", out_val_list);
		io_val_list.add(buffer_info.toString());
		map.put("text", io_val_list);
		return map;
	}

	
	public Map<String, List<String>> getTaLableInfoBySS_HX() {
		// TODO Auto-generated method stub
		List<String> ss_key_list = new ArrayList<String>();
		List<String> ss_val_list = new ArrayList<String>();
		List<TaLableInfo> ss_list = labledefineMapper.getTaLableInfoByName(TABLE_LABLE_DEFINE, TABLE_LABLE_FIELD, "手术%");
		for (TaLableInfo taLableInfo : ss_list) {
			if (taLableInfo.getPatient_num() > 0L) {
				ss_key_list.add(taLableInfo.getLable_value());
				ss_val_list.add(Long.toString(taLableInfo.getPatient_num()));
			}			
		}
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("ss_key", ss_key_list);
		map.put("ss_val", ss_val_list);
		
		// 入院诊断
		Map<String, List<String>> map_tmp = new HashMap<String, List<String>>();
		List<String> zd_key_list = new ArrayList<String>();
		List<String> zd_val_list = new ArrayList<String>();
		List<TaLableInfo> zd_list = labledefineMapper.getTaLableInfoByName(TABLE_LABLE_DEFINE, TABLE_LABLE_FIELD, "诊断及分型-入院诊断%");		
		for (TaLableInfo taLableInfo : zd_list) {
			String names[] = taLableInfo.getLable_name().split("-");
			taLableInfo.setParent_name(names[0]);
			taLableInfo.setLable_name(names[1].trim());
			List<TaLableInfo> zyh_list = labledefineMapper.getSSTaLableInfoByZD(TABLE_LABLE_DEFINE, TABLE_LABLE_FIELD,
					 "手术%", taLableInfo.getId());
			List<String> zd_ss_list = new ArrayList<String>();
			for (String str_key : ss_key_list) {
				long value = 0L;
				for (TaLableInfo taLableInfo2 : zyh_list) {
					if (str_key.equals(taLableInfo2.getLable_value())) {
						value = taLableInfo2.getPatient_num();
						break;
					}
				}
				zd_ss_list.add(Long.toString(value));
				if (map_tmp.get(str_key) != null) {
					map_tmp.get(str_key).add(Long.toString(value));
				} else {
					List<String> tmp_list = new ArrayList<String>();
					tmp_list.add(Long.toString(value));
					map_tmp.put(str_key, tmp_list);
				}
			}
			map.put(taLableInfo.getLable_value().trim(), zd_ss_list);
			zd_key_list.add(taLableInfo.getLable_value().trim());
			zd_val_list.add(Long.toString(taLableInfo.getPatient_num()));
		}		
		map.put("zd_key", zd_key_list);
		map.put("zd_val", zd_val_list);
		map.putAll(map_tmp);
		return map;
	}

	
	public Map<String, List<String>> getTaLableInfoByJY_HX() {
		// TODO Auto-generated method stub
		List<String> ss_key_list = new ArrayList<String>();
		List<String> ss_val_list = new ArrayList<String>();
		List<TaLableInfo> ss_list = labledefineMapper.getTaLableInfoByName(TABLE_LABLE_DEFINE, TABLE_LABLE_FIELD, "检验-检查项目%");
		for (TaLableInfo taLableInfo : ss_list) {
			if (taLableInfo.getPatient_num() > 0L) {
				ss_key_list.add(taLableInfo.getLable_value());
				ss_val_list.add(Long.toString(taLableInfo.getPatient_num()));
			}
		}
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("ss_key", ss_key_list);
		map.put("ss_val", ss_val_list);
		
		// 入院诊断
		Map<String, List<String>> map_tmp = new HashMap<String, List<String>>();
		List<String> zd_key_list = new ArrayList<String>();
		List<String> zd_val_list = new ArrayList<String>();
		List<TaLableInfo> zd_list = labledefineMapper.getTaLableInfoByName(TABLE_LABLE_DEFINE, TABLE_LABLE_FIELD, "诊断及分型-入院诊断%");		
		for (TaLableInfo taLableInfo : zd_list) {
			String names[] = taLableInfo.getLable_name().split("-");
			taLableInfo.setParent_name(names[0]);
			taLableInfo.setLable_name(names[1].trim());
			List<TaLableInfo> zyh_list = labledefineMapper.getSSTaLableInfoByZD(TABLE_LABLE_DEFINE, TABLE_LABLE_FIELD,
					 "检验-检查项目%", taLableInfo.getId());
			List<String> zd_ss_list = new ArrayList<String>();
			for (String str_key : ss_key_list) {
				long value = 0L;
				for (TaLableInfo taLableInfo2 : zyh_list) {
					if (str_key.equals(taLableInfo2.getLable_value())) {
						value = taLableInfo2.getPatient_num();
						break;
					}
				}
				zd_ss_list.add(Long.toString(value));
				if (map_tmp.get(str_key) != null) {
					map_tmp.get(str_key).add(Long.toString(value));
				} else {
					List<String> tmp_list = new ArrayList<String>();
					tmp_list.add(Long.toString(value));
					map_tmp.put(str_key, tmp_list);
				}
			}
			map.put(taLableInfo.getLable_value().trim(), zd_ss_list);
			zd_key_list.add(taLableInfo.getLable_value().trim());
			zd_val_list.add(Long.toString(taLableInfo.getPatient_num()));
		}		
		map.put("zd_key", zd_key_list);
		map.put("zd_val", zd_val_list);
		map.putAll(map_tmp);
		return map;
	}

	public List<String> getPortraitZDInfo() {
		List<TaLableInfo> info_list = labledefineMapper.getTaLableInfoByName(TABLE_LABLE_DEFINE, TABLE_LABLE_FIELD, "诊断及分型-入院诊断%");

		HashSet<String> lable_set = new HashSet<String>();
		for (TaLableInfo taLableInfo : info_list) {
			String names[] = taLableInfo.getLable_name().split("-");
			taLableInfo.setParent_name(names[0]);
			taLableInfo.setLable_name(names[1].trim());
			lable_set.add(taLableInfo.getLable_value().trim());
		}
		return new ArrayList<String>(lable_set);
	}
	
	public List<String> getPortraitZyhList(String zdName) {
		List<String> is_list = labledefineMapper.getDiagnosisZyhByName(TABLE_LABLE_DEFINE, TABLE_LABLE_FIELD, "诊断及分型%", zdName);
		return is_list;
	}
	
	public Map<String, List<String>> getTaLableInfoByYP_HX() {
		// TODO Auto-generated method stub
		List<TaLableInfo> ss_list = labledefineMapper.getTaLableInfoByName(TABLE_LABLE_DEFINE, TABLE_LABLE_FIELD, "药物%");
		HashSet<String> lable_set = new HashSet<String>();
		Map<String,Long> val_map = new HashMap<String,Long>();
		for (TaLableInfo taLableInfo : ss_list) {
			String names[] = taLableInfo.getLable_name().split("-");
			taLableInfo.setParent_name(names[0]);
			taLableInfo.setLable_name(names[1].trim());
			if (taLableInfo.getPatient_num() > 0L) {
				lable_set.add(taLableInfo.getLable_name());
				if (val_map.get(taLableInfo.getLable_name()) != null) {
					long val_tmp = val_map.get(taLableInfo.getLable_name()) + taLableInfo.getPatient_num();
					val_map.put(taLableInfo.getLable_name(), val_tmp);
				} else {
					val_map.put(taLableInfo.getLable_name(), taLableInfo.getPatient_num());
				}
			}
		}
		List<String> ss_key_list = new ArrayList<String>(lable_set);
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put("ss_key", ss_key_list);
		List<String> ss_val_list = new ArrayList<String>();
		for (String str_key : ss_key_list) {
			ss_val_list.add(Long.toString(val_map.get(str_key)));
		}
		map.put("ss_val", ss_val_list);
		
		// 入院诊断
		Map<String, List<String>> map_tmp = new HashMap<String, List<String>>();
		List<String> zd_key_list = new ArrayList<String>();
		List<String> zd_val_list = new ArrayList<String>();
		List<TaLableInfo> zd_list = labledefineMapper.getTaLableInfoByName(TABLE_LABLE_DEFINE, TABLE_LABLE_FIELD, "诊断及分型-入院诊断%");		
		for (TaLableInfo taLableInfo : zd_list) {
			String names[] = taLableInfo.getLable_name().split("-");
			taLableInfo.setParent_name(names[0]);
			taLableInfo.setLable_name(names[1].trim());
			List<TaLableInfo> zyh_list = labledefineMapper.getSSTaLableInfoByZD(TABLE_LABLE_DEFINE, TABLE_LABLE_FIELD,
					 "药物%", taLableInfo.getId());
			List<String> zd_ss_list = new ArrayList<String>();
			for (String str_key : ss_key_list) {
				long value = 0L;
				for (TaLableInfo taLableInfo2 : zyh_list) {
					if (taLableInfo2.getLable_name().contains(str_key)) {
						value += taLableInfo2.getPatient_num();
					}
				}
				zd_ss_list.add(Long.toString(value));
				if (map_tmp.get(str_key) != null) {
					map_tmp.get(str_key).add(Long.toString(value));
				} else {
					List<String> tmp_list = new ArrayList<String>();
					tmp_list.add(Long.toString(value));
					map_tmp.put(str_key, tmp_list);
				}
			}
			map.put(taLableInfo.getLable_value().trim(), zd_ss_list);
			zd_key_list.add(taLableInfo.getLable_value().trim());
			zd_val_list.add(Long.toString(taLableInfo.getPatient_num()));
		}		
		map.put("zd_key", zd_key_list);
		map.put("zd_val", zd_val_list);
		map.putAll(map_tmp);
		return map;
	}
	
	public TaPatient getMtLablePortraitByPatient(String zyh) {
		TaPatient taPatient = new TaPatient();
		List<TaNode> nodes = new ArrayList<TaNode>();
		List<TaLink> links = new ArrayList<TaLink>();
		nodes.add(new TaNode(0, zyh, 20, "住院号" + zyh));
		List<MtLableDefine> labledefs = labledefineMapper.getMtLableDefinesByPatient(TABLE_LABLE_DEFINE, TABLE_LABLE_FIELD, zyh);
		for (MtLableDefine mtLableDefine : labledefs) {
			String names[] = mtLableDefine.getLable_name().split("-");
			nodes.add(new TaNode(1, names[0], 15, names[0]));
			nodes.add(new TaNode(2, names[1], 10, names[1]));
			nodes.add(new TaNode(3, mtLableDefine.getVal_name(), 5, mtLableDefine.getVal_name()));

			links.add(new TaLink(zyh, names[0], 6, "属性"));
			links.add(new TaLink(names[0], names[1], 3, "属性"));
			links.add(new TaLink(names[1], mtLableDefine.getVal_name(), 1, "值"));
		}
		taPatient.setNodes(nodes);
		taPatient.setLinks(links);
		return taPatient;
	}
}