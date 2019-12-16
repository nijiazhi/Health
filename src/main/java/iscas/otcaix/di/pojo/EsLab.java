package iscas.otcaix.di.pojo;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 检验信息
 **/
public class EsLab {
	private String zyh;
	@Field(type = FieldType.String, analyzer = "ik", searchAnalyzer = "ik", store = true)
	private String jybgbdmc;

	public String getZyh() {
		return zyh;
	}

	public void setZyh(String zyh) {
		this.zyh = zyh;
	}

	public String getJybgbdmc() {
		return jybgbdmc;
	}

	public void setJybgbdmc(String jybgbdmc) {
		this.jybgbdmc = jybgbdmc;
	}
}