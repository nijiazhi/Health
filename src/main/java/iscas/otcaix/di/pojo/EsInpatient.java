package iscas.otcaix.di.pojo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 住院数据信息
 * 
 * @author MZR
 **/
// @Document(indexName = "healthcare", type = "inpatient")
public class EsInpatient {
	@Id
	@Field(index = FieldIndex.not_analyzed, store = true)
	private String zyh;

	// 姓名
	private String xm;

	// 性别
	private String xb;

	// 年龄
	private int nl;

	// 医院
	private String dwbm;

	// 科室
	private String rykbmc;

	// 诊断
	@Field(type = FieldType.String, analyzer = "ik", searchAnalyzer = "ik", store = true)
	private String cyzdbm;

	// 转归
	private String lyfsmc;

	// 检验
	@Field(type = FieldType.Nested, analyzer = "ik", searchAnalyzer = "ik", store = true)
	private List<String> jybgbdmcs;

	// 影像结果
	@Field(type = FieldType.Nested, analyzer = "ik", searchAnalyzer = "ik", store = true)
	private List<String> yxlgs;

	// 手术
	@Field(type = FieldType.Nested, analyzer = "ik", searchAnalyzer = "ik", store = true)
	private List<String> ssczmcs;

	// 入院记录信息
	@Field(type = FieldType.Nested, store = true)
	private EsInrecord inrecord;

	public String getZyh() {
		return zyh;
	}

	public void setZyh(String zyh) {
		this.zyh = zyh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public int getNl() {
		return nl;
	}

	public void setNl(int nl) {
		this.nl = nl;
	}

	public String getDwbm() {
		return dwbm;
	}

	public void setDwbm(String dwbm) {
		this.dwbm = dwbm;
	}

	public String getRykbmc() {
		return rykbmc;
	}

	public void setRykbmc(String rykbmc) {
		this.rykbmc = rykbmc;
	}

	public String getCyzdbm() {
		return cyzdbm;
	}

	public void setCyzdbm(String cyzdbm) {
		this.cyzdbm = cyzdbm;
	}

	public String getLyfsmc() {
		return lyfsmc;
	}

	public void setLyfsmc(String lyfsmc) {
		this.lyfsmc = lyfsmc;
	}

	public List<String> getJybgbdmcs() {
		return jybgbdmcs;
	}

	public void setJybgbdmcs(List<String> jybgbdmcs) {
		this.jybgbdmcs = jybgbdmcs;
	}

	public List<String> getYxlgs() {
		return yxlgs;
	}

	public void setYxlgs(List<String> yxlgs) {
		this.yxlgs = yxlgs;
	}

	public List<String> getSsczmcs() {
		return ssczmcs;
	}

	public void setSsczmcs(List<String> ssczmcs) {
		this.ssczmcs = ssczmcs;
	}

	public EsInrecord getInrecord() {
		return inrecord;
	}

	public void setInrecord(EsInrecord inrecord) {
		this.inrecord = inrecord;
	}
}