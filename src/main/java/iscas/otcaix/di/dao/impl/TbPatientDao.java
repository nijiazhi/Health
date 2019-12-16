package iscas.otcaix.di.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import iscas.otcaix.di.pojo.MtLableDefine;
import iscas.otcaix.di.pojo.TaLableInfo;
import iscas.otcaix.di.util.DBUtil;

@Repository
public class TbPatientDao {
	private static final String DATASOURCE_HOSPITALDATA = "dataSource_hospitaldata";

	public List<TaLableInfo> getTaLableInfoByName(String tablename_ld, String tablename_lf, String name) {
		DBUtil dbUtil = new DBUtil(DataSourceFactory.getConnection(DATASOURCE_HOSPITALDATA));
		List<TaLableInfo> infolist = new ArrayList<TaLableInfo>();
		try {
			String sql = "SELECT " + "ld.id, " + "ld.lable_name, " + "ld.val_name AS lable_value, "
					+ "COUNT(zyh) AS patient_num " + "FROM " + tablename_ld + " AS ld " + "LEFT JOIN " + tablename_lf
					+ " AS lf ON ld.id = " + "lf.lable_id " + "WHERE ld.lable_name LIKE '" + name + "' " + "GROUP BY "
					+ "ld.id";

			System.out.println(sql);
			ResultSet res = dbUtil.query(sql);
			while (res.next()) {
				TaLableInfo taLableInfo = new TaLableInfo();
				taLableInfo.setId(res.getLong(1));
				taLableInfo.setLable_name(res.getString(2));
				taLableInfo.setLable_value(res.getString(3));
				taLableInfo.setPatient_num(res.getLong(4));
				infolist.add(taLableInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.close();
		}
		return infolist;
	}

	public List<String> getMultiTagZyhByName(String tablename_ld, String tablename_lf, String name, Long count) {
		DBUtil dbUtil = new DBUtil(DataSourceFactory.getConnection(DATASOURCE_HOSPITALDATA));
		List<String> infolist = new ArrayList<String>();
		try {
			String sql = "SELECT " + "zyh " + "FROM " + tablename_lf + " " + "WHERE " + "lable_id IN ( " + "SELECT "
					+ "id " + "FROM " + tablename_ld + " " + "WHERE " + "lable_name LIKE '" + name + "' " + ") "
					+ "GROUP BY " + "zyh " + "HAVING " + "COUNT(lable_id) >= " + count;

			ResultSet res = dbUtil.query(sql);
			while (res.next()) {
				infolist.add(res.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.close();
		}
		return infolist;
	}

	public List<String> getDiagnosisZyhByName(String tablename_ld, String tablename_lf, String tag_name,
			String val_name) {
		DBUtil dbUtil = new DBUtil(DataSourceFactory.getConnection(DATASOURCE_HOSPITALDATA));
		List<String> infolist = new ArrayList<String>();
		try {
			String sql = "SELECT " + "zyh " + "FROM " + tablename_lf + " " + "WHERE " + "lable_id IN ( " + "SELECT "
					+ "id " + "FROM " + tablename_ld + " " + "WHERE " + "lable_name LIKE '" + tag_name + "' "
					+ "AND val_name = '" + val_name + "' " + ") " + "GROUP BY " + "zyh " + "HAVING " + "COUNT(zyh) = 2";
			ResultSet res = dbUtil.query(sql);
			while (res.next()) {
				infolist.add(res.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.close();
		}
		return infolist;
	}

	public List<TaLableInfo> getSSTaLableInfoByZD(String tablename_ld, String tablename_lf, String name, Long id) {
		DBUtil dbUtil = new DBUtil(DataSourceFactory.getConnection(DATASOURCE_HOSPITALDATA));
		List<TaLableInfo> infolist = new ArrayList<TaLableInfo>();
		try {
			String sql = "SELECT " + "ld.id, " + "ld.lable_name, " + "ld.val_name AS lable_value, "
					+ "COUNT(zyh) AS patient_num " + "FROM " + tablename_ld + " AS ld " + "LEFT JOIN " + tablename_lf
					+ " AS lf ON ld.id = lf.lable_id " + "WHERE " + "ld.lable_name LIKE '" + name
					+ "' AND zyh IN (SELECT zyh FROM " + tablename_lf + " WHERE lable_id " + "= " + id + ") "
					+ "GROUP BY " + "ld.id";

			ResultSet res = dbUtil.query(sql);
			while (res.next()) {
				TaLableInfo taLableInfo = new TaLableInfo();
				taLableInfo.setId(res.getLong(1));
				taLableInfo.setLable_name(res.getString(2));
				taLableInfo.setLable_value(res.getString(3));
				taLableInfo.setPatient_num(res.getLong(4));
				infolist.add(taLableInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.close();
		}
		return infolist;
	}

	public List<MtLableDefine> getMtLableDefinesByPatient(String tablename_ld, String tablename_lf, String zyh) {
		DBUtil dbUtil = new DBUtil(DataSourceFactory.getConnection(DATASOURCE_HOSPITALDATA));
		List<MtLableDefine> infolist = new ArrayList<MtLableDefine>();
		try {
			String sql = "select " + "id,lable_id,lable_name,val_name,val_code " + "from " + tablename_ld + " "
					+ "WHERE id IN " + "(SELECT lable_id FROM " + tablename_lf + " " + "WHERE zyh = '" + zyh + "')";

			ResultSet res = dbUtil.query(sql);
			while (res.next()) {
				MtLableDefine taLableInfo = new MtLableDefine();
				taLableInfo.setId(res.getLong(1));
				taLableInfo.setLable_id(res.getLong(2));
				taLableInfo.setLable_name(res.getString(3));
				taLableInfo.setVal_name(res.getString(4));
				taLableInfo.setVal_code(res.getString(5));
				infolist.add(taLableInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbUtil.close();
		}
		return infolist;
	}

	public Map<String, String> getOperationNumber(String startdate, String enddate, String hospitalDeps, String sex,
			String age) {
		DBUtil dbUtil = new DBUtil(DataSourceFactory.getConnection(DATASOURCE_HOSPITALDATA));

		String[] tmp = age.substring(1, age.length() - 1).split(",");
		String age1 = tmp[0];
		String age2 = tmp[1];

		String sql = String.format(
				"SELECT " + "mtld.id, " + "mtld.val_name, " + "COUNT(DISTINCT tbif.zyh) AS num " + "FROM "
						+ "TB_Inpatient_FirstPage AS tbif "
						+ "JOIN tb_inpatient_operation AS tbio ON tbio.ZYH = tbif.ZYH "
						+ "JOIN mt_tl001_lable_field AS mtlf ON mtlf.zyh = tbif.zyh "
						+ "JOIN mt_tl001_lable_define AS mtld ON mtld.id = mtlf.lable_id " + "WHERE "
						+ "('%s' <= RYSJ) " + "AND (RYSJ <= '%s') " + "AND (RYKBBM = '%s' OR '%s' = '0') "
						+ "AND (XB='%s' OR '%s'='0') AND CAST(NL AS DECIMAL) BETWEEN '%s' AND '%s' "
						+ "AND mtld.lable_id IN ( " + "SELECT " + "id " + "FROM " + "mt_tl001_lables " + "WHERE "
						+ "`name` = '入院诊断' " + ") " + "GROUP BY " + "mtld.id;",
				startdate, enddate, hospitalDeps, hospitalDeps, sex, sex, age1, age2);

		System.out.println(sql);

		Map<String, String> rs_map = new HashMap<String, String>();
		try {
			ResultSet res = dbUtil.query(sql);
			while (res.next())
				rs_map.put(res.getString(2), res.getString(3));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbUtil.close();
		return rs_map;
	}

	public Map<String, String> getOperationNumberByDie(String startdate, String enddate, String hospitalDeps,
			String sex, String age) {
		DBUtil dbUtil = new DBUtil(DataSourceFactory.getConnection(DATASOURCE_HOSPITALDATA));

		String[] tmp = age.substring(1, age.length() - 1).split(",");
		String age1 = tmp[0];
		String age2 = tmp[1];

		String sql = String.format("SELECT " + "mtld.id, " + "mtld.val_name, " + "COUNT(DISTINCT tbif.zyh) AS num "
				+ "FROM " + "TB_Inpatient_FirstPage AS tbif "
				+ "JOIN tb_inpatient_operation AS tbio ON tbio.ZYH = tbif.ZYH "
				+ "JOIN mt_tl001_lable_field AS mtlf ON mtlf.zyh = tbif.zyh "
				+ "JOIN mt_tl001_lable_define AS mtld ON mtld.id = mtlf.lable_id " + "WHERE " + "LYFSMC = '死亡' AND "
				+ "('%s' <= RYSJ) " + "AND (RYSJ <= '%s') " + "AND (RYKBBM = '%s' OR '%s' = '0') "
				+ "AND (XB='%s' OR '%s'='0') AND CAST(NL AS DECIMAL) BETWEEN '%s' AND '%s' " + "AND mtld.lable_id IN ( "
				+ "SELECT " + "id " + "FROM " + "mt_tl001_lables " + "WHERE " + "`name` = '入院诊断' " + ") " + "GROUP BY "
				+ "mtld.id;", startdate, enddate, hospitalDeps, hospitalDeps, sex, sex, age1, age2);

		System.out.println(sql);

		Map<String, String> rs_map = new HashMap<String, String>();
		try {
			ResultSet res = dbUtil.query(sql);
			while (res.next())
				rs_map.put(res.getString(2), res.getString(3));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbUtil.close();
		return rs_map;
	}
}
