package iscas.otcaix.di.pojo;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 用药信息
 **/
public class EsDrug {
	private String zyh;
	private String yzid;
	@Field(type = FieldType.String, analyzer = "ik", searchAnalyzer = "ik", store = true)
	private String yptym;

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

	public String getYptym() {
		return yptym;
	}

	public void setYptym(String yptym) {
		this.yptym = yptym;
	}

}