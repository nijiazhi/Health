package iscas.otcaix.di.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import iscas.otcaix.di.pojo.EsDrug;
import iscas.otcaix.di.pojo.EsInpatient;
import iscas.otcaix.di.pojo.EsInrecord;
import iscas.otcaix.di.pojo.EsOrder;
import iscas.otcaix.di.pojo.EsScreening;
import iscas.otcaix.di.util.DBUtil;
import iscas.otcaix.di.util.DataSourceFactory;

@Transactional
@Repository
public class ElasticPatientDao {
	
	// 住院数据
	private static final String DATASOURCE_HOSPITALDATA = "dataSource_hospitaldata";
	// 筛查数据
	private static final String DATASOURCE_SCREENDATA = "dataSource_screendata";

	public List<EsInpatient> getEsInpatientList() {
		Map<String, String> icd10_map = getICDCodeObj("std_icd10");
		List<EsInpatient> medical_list = new ArrayList<EsInpatient>();
		// List<String> zyhlist = getZyhList(1000L);
		List<String> zyhlist = getAllZyhList();
		for (String zyh : zyhlist) {
			EsInpatient esMedical = getEsMedicalSub(zyh, icd10_map);
			// 检验
			List<String> jybgbdmc_list = getJyList(zyh);
			esMedical.setJybgbdmcs(jybgbdmc_list);

			// 影像结果
			List<String> yxlg_list = getYxList(zyh);
			esMedical.setYxlgs(yxlg_list);

			// 手术
			List<String> ssczmc_list = getSsList(zyh);
			esMedical.setSsczmcs(ssczmc_list);

			// 入院记录信息
			EsInrecord inrecord = getEsInrecordByZyh(zyh);
			esMedical.setInrecord(inrecord);
			medical_list.add(esMedical);
		}
		return medical_list;
	}

	public List<EsScreening> getEsScreeningList() {
		List<EsScreening> screening_list = new ArrayList<EsScreening>();
		DBUtil dbUtil = new DBUtil(DataSourceFactory.getConnection(DATASOURCE_SCREENDATA));
		String sql = "SELECT " + "df.acid, " + "df.dfStroke, " + "df.dfTia, " + "df.dfHypertension, " + "df.dfAF, "
				+ "df.dfSmoking, " + "df.dfLDL, " + "df.dfGlycuresis, " + "df.dfSportsLack, " + "df.dfOverweight, "
				+ "df.dfStrokeFamily, " + "df.dfStatus, " + "uu.uuName " + "FROM " + "dangerfactors2011 AS df "
				+ "JOIN userunit AS uu ON uu.uuCode = df.uuCode limit 10000;";
		try {
			ResultSet res = dbUtil.query(sql);
			while (res.next()) {
				EsScreening esOrder = new EsScreening();
				esOrder.setAcid(res.getString(1));
				if (res.getString(2).equals("1")) {
					esOrder.setDfStroke("脑卒中");
				} else {
					esOrder.setDfStroke("无");
				}

				if (res.getString(3).equals("1")) {
					esOrder.setDfTia("短暂性脑缺血发作TIA");
				} else {
					esOrder.setDfTia("无");
				}

				if (res.getString(4).equals("1")) {
					esOrder.setDfHypertension("高血压病");
				} else {
					esOrder.setDfHypertension("无");
				}

				if (res.getString(5).equals("1")) {
					esOrder.setDfAF("房颤或瓣膜性心脏病");
				} else {
					esOrder.setDfAF("无");
				}

				if (res.getString(6).equals("1")) {
					esOrder.setDfSmoking("吸烟");
				} else {
					esOrder.setDfSmoking("无");
				}

				if (res.getString(7).equals("1")) {
					esOrder.setDfLDL("血脂异常");
				} else {
					esOrder.setDfLDL("无");
				}

				if (res.getString(8).equals("1")) {
					esOrder.setDfGlycuresis("糖尿病");
				} else {
					esOrder.setDfGlycuresis("无");
				}

				if (res.getString(9).equals("1")) {
					esOrder.setDfSportsLack("体育锻炼很少或轻体力劳动者");
				} else {
					esOrder.setDfSportsLack("无");
				}

				if (res.getString(10).equals("1")) {
					esOrder.setDfOverweight("明显超重");
				} else {
					esOrder.setDfOverweight("无");
				}

				if (res.getString(11).equals("1")) {
					esOrder.setDfStrokeFamily("有脑卒中家族史");
				} else {
					esOrder.setDfStrokeFamily("无");
				}

				if (res.getString(12).equals("1")) {
					esOrder.setDfStatus("低危");
				} else if (res.getString(12).equals("2")) {
					esOrder.setDfStatus("中危");
				} else if (res.getString(12).equals("3")) {
					esOrder.setDfStatus("高危");
				} else if (res.getString(12).equals("4")) {
					esOrder.setDfStatus("TIA");
				} else if (res.getString(12).equals("5")) {
					esOrder.setDfStatus("卒中");
				} else {
					esOrder.setDfStatus("未分级");
				}
				esOrder.setUuName(res.getString(13));
				screening_list.add(esOrder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbUtil.close();
		return screening_list;
	}

	@SuppressWarnings("unused")
	private List<EsOrder> getEsOrderListByZyh(String zyh) {
		List<EsOrder> order_list = new ArrayList<EsOrder>();
		DBUtil dbUtil = new DBUtil(DataSourceFactory.getConnection(DATASOURCE_HOSPITALDATA));
		String sql = String.format("SELECT ZYH,YZID,YZMC FROM tb_inpatient_order WHERE ZYH = '%s';", zyh);
		System.out.println(sql);

		try {
			ResultSet res = dbUtil.query(sql);
			if (res.next()) {
				EsOrder esOrder = new EsOrder();
				esOrder.setZyh(res.getString(1));
				esOrder.setYzid(res.getString(2));
				esOrder.setYzmc(res.getString(3));
				order_list.add(esOrder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbUtil.close();
		return order_list;
	}

	@SuppressWarnings("unused")
	private List<EsDrug> getEsDrugListByZyh(String zyh) {
		List<EsDrug> drug_list = new ArrayList<EsDrug>();
		DBUtil dbUtil = new DBUtil(DataSourceFactory.getConnection(DATASOURCE_HOSPITALDATA));
		String sql = String.format("SELECT ZYH,YZID,YPTYM FROM tb_inpatient_drug WHERE ZYH = '%s';", zyh);
		System.out.println(sql);

		try {
			ResultSet res = dbUtil.query(sql);
			if (res.next()) {
				EsDrug esDrug = new EsDrug();
				esDrug.setZyh(res.getString(1));
				esDrug.setYzid(res.getString(2));
				esDrug.setYptym(res.getString(3));
				drug_list.add(esDrug);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbUtil.close();
		return drug_list;
	}

	@SuppressWarnings("unused")
	private List<EsInrecord> getEsInrecordListByZyh(String zyh) {
		List<EsInrecord> inrecord_list = new ArrayList<EsInrecord>();
		DBUtil dbUtil = new DBUtil(DataSourceFactory.getConnection(DATASOURCE_HOSPITALDATA));
		String sql = String.format("SELECT RYZS,ZZ,XBS,JWS,GRS,JZS,YJJSYS FROM tb_inpatient_inrecord WHERE ZYH = '%s';",
				zyh);
		System.out.println(sql);

		try {
			ResultSet res = dbUtil.query(sql);
			while (res.next()) {
				EsInrecord esInrecord = new EsInrecord();
				esInrecord.setRyzs(res.getString(1));
				esInrecord.setZz(res.getString(2));
				esInrecord.setXbs(res.getString(3));
				esInrecord.setJws(res.getString(4));
				esInrecord.setGrs(res.getString(5));
				esInrecord.setJzs(res.getString(6));
				esInrecord.setYjjsys(res.getString(7));
				inrecord_list.add(esInrecord);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbUtil.close();
		return inrecord_list;
	}

	private EsInrecord getEsInrecordByZyh(String zyh) {
		DBUtil dbUtil = new DBUtil(DataSourceFactory.getConnection(DATASOURCE_HOSPITALDATA));
		String sql = String.format("SELECT RYZS,ZZ,XBS,JWS,GRS,JZS,YJJSYS FROM tb_inpatient_inrecord WHERE ZYH = '%s';",
				zyh);
		// System.out.println(sql);

		EsInrecord esInrecord = new EsInrecord();
		try {
			ResultSet res = dbUtil.query(sql);
			if (res.next()) {
				esInrecord.setRyzs(res.getString(1));
				esInrecord.setZz(res.getString(2));
				esInrecord.setXbs(res.getString(3));
				esInrecord.setJws(res.getString(4));
				esInrecord.setGrs(res.getString(5));
				esInrecord.setJzs(res.getString(6));
				esInrecord.setYjjsys(res.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbUtil.close();
		return esInrecord;
	}

	private EsInpatient getEsMedicalSub(String zyh, Map<String, String> icd10_map) {
		DBUtil dbUtil = new DBUtil(DataSourceFactory.getConnection(DATASOURCE_HOSPITALDATA));
		String sql = String.format(
				"SELECT ZYH,XM,XB,NL,DWBM,RYKBMC,CYZDBM,LYFSMC " + "FROM tb_inpatient_firstpage " + "WHERE ZYH = '%s';",
				zyh);
		// System.out.println(sql);

		EsInpatient esMedical = new EsInpatient();
		try {
			ResultSet res = dbUtil.query(sql);
			if (res.next()) {
				esMedical.setZyh(res.getString(1));
				esMedical.setXm(res.getString(2));
				esMedical.setXb(res.getString(3));
				esMedical.setNl(res.getInt(4));
				esMedical.setDwbm(res.getString(5));
				esMedical.setRykbmc(res.getString(6));
				if (icd10_map.get(res.getString(7)) == null) {
					esMedical.setCyzdbm(res.getString(7));
				} else {
					esMedical.setCyzdbm(icd10_map.get(res.getString(7)));
				}
				esMedical.setLyfsmc(res.getString(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbUtil.close();
		return esMedical;
	}

	@SuppressWarnings("unused")
	private List<String> getZyhList(Long count) {
		DBUtil dbUtil = new DBUtil(DataSourceFactory.getConnection(DATASOURCE_HOSPITALDATA));
		List<String> zyhlist = new ArrayList<String>();
		try {
			String sql = "SELECT ZYH FROM tb_inpatient_inrecord LIMIT " + count;

			ResultSet res = dbUtil.query(sql);
			while (res.next()) {
				zyhlist.add(res.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.close();
		}
		return zyhlist;
	}

	private List<String> getAllZyhList() {
		DBUtil dbUtil = new DBUtil(DataSourceFactory.getConnection(DATASOURCE_HOSPITALDATA));
		List<String> zyhlist = new ArrayList<String>();
		try {
			String sql = "SELECT ZYH FROM tb_inpatient_firstpage;";

			ResultSet res = dbUtil.query(sql);
			while (res.next()) {
				zyhlist.add(res.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.close();
		}
		return zyhlist;
	}

	private Map<String, String> getICDCodeObj(String tablename) {
		DBUtil dbUtil = new DBUtil(DataSourceFactory.getConnection(DATASOURCE_HOSPITALDATA));

		String sql = String.format("SELECT code,name FROM %s;", tablename);

		Map<String, String> rs_map = new HashMap<String, String>();
		try {
			ResultSet res = dbUtil.query(sql);
			while (res.next())
				rs_map.put(res.getString(1), res.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbUtil.close();
		return rs_map;
	}

	private List<String> getJyList(String zyh) {
		DBUtil dbUtil = new DBUtil(DataSourceFactory.getConnection(DATASOURCE_HOSPITALDATA));
		List<String> zyhlist = new ArrayList<String>();
		try {
			String sql = String.format("SELECT JYBGBDMC FROM tb_inpatient_lab_master WHERE ZYH = '%s';", zyh);

			ResultSet res = dbUtil.query(sql);
			while (res.next()) {
				zyhlist.add(res.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.close();
		}
		return zyhlist;
	}

	private List<String> getYxList(String zyh) {
		DBUtil dbUtil = new DBUtil(DataSourceFactory.getConnection(DATASOURCE_HOSPITALDATA));
		List<String> zyhlist = new ArrayList<String>();
		try {
			String sql = String.format("SELECT YXJG FROM tb_inpatient_image WHERE ZYH = '%s';", zyh);

			ResultSet res = dbUtil.query(sql);
			while (res.next()) {
				zyhlist.add(res.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.close();
		}
		return zyhlist;
	}

	private List<String> getSsList(String zyh) {
		DBUtil dbUtil = new DBUtil(DataSourceFactory.getConnection(DATASOURCE_HOSPITALDATA));
		List<String> zyhlist = new ArrayList<String>();
		try {
			String sql = String.format("SELECT SSCZMC FROM tb_inpatient_operation WHERE ZYH = '%s';", zyh);

			ResultSet res = dbUtil.query(sql);
			while (res.next()) {
				zyhlist.add(res.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.close();
		}
		return zyhlist;
	}
}
