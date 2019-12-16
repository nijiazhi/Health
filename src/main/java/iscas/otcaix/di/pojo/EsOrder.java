package iscas.otcaix.di.pojo;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 医嘱信息
 **/
public class EsOrder {
	private String zyh;
	private String yzid;
	@Field(type = FieldType.String, analyzer = "ik", searchAnalyzer = "ik", store = true)
	private String yzmc;

	public String getZyh() {
		return zyh;
	}

	public void setZyh(String zyh) {
		this.zyh = zyh;
	}

	public String getYzid() {
		return yzid;
	}

	public void setYzid(String yzid) {
		this.yzid = yzid;
	}

	public String getYzmc() {
		return yzmc;
	}

	public void setYzmc(String yzmc) {
		this.yzmc = yzmc;
	}

}