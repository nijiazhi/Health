package iscas.otcaix.di.pojo;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 入院记录信息
 **/
public class EsInrecord {
	// 主诉
	@Field(type = FieldType.String, analyzer = "ik", searchAnalyzer = "ik", store = true)
	private String ryzs;

	// 症状
	@Field(type = FieldType.String, analyzer = "ik", searchAnalyzer = "ik", store = true)
	private String zz;

	// 现病史
	@Field(type = FieldType.String, analyzer = "ik", searchAnalyzer = "ik", store = true)
	private String xbs;

	// 既往史
	@Field(type = FieldType.String, analyzer = "ik", searchAnalyzer = "ik", store = true)
	private String jws;

	// 个人史
	@Field(type = FieldType.String, analyzer = "ik", searchAnalyzer = "ik", store = true)
	private String grs;

	// 家族史
	@Field(type = FieldType.String, analyzer = "ik", searchAnalyzer = "ik", store = true)
	private String jzs;

	// 月经及生育史
	@Field(type = FieldType.String, analyzer = "ik", searchAnalyzer = "ik", store = true)
	private String yjjsys;

	public String getRyzs() {
		return ryzs;
	}

	public void setRyzs(String ryzs) {
		this.ryzs = ryzs;
	}

	public String getZz() {
		return zz;
	}

	public void setZz(String zz) {
		this.zz = zz;
	}

	public String getXbs() {
		return xbs;
	}

	public void setXbs(String xbs) {
		this.xbs = xbs;
	}

	public String getJws() {
		return jws;
	}

	public void setJws(String jws) {
		this.jws = jws;
	}

	public String getGrs() {
		return grs;
	}

	public void setGrs(String grs) {
		this.grs = grs;
	}

	public String getJzs() {
		return jzs;
	}

	public void setJzs(String jzs) {
		this.jzs = jzs;
	}

	public String getYjjsys() {
		return yjjsys;
	}

	public void setYjjsys(String yjjsys) {
		this.yjjsys = yjjsys;
	}
}