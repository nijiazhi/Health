package iscas.otcaix.di.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * 筛查数据信息
 **/
// @Document(indexName = "healthcare", type = "screening")
public class EsScreening {
	@Id
	@Field(index = FieldIndex.not_analyzed, store = true)
	private String acid;// 病案号

	private String dfStroke;// 既往有脑卒中

	private String dfTia;// 短暂性脑缺血发作TIA

	private String dfHypertension;// 高血压病（血压≥/90 mmHg或正在服用降压药）

	private String dfAF;// 房颤或瓣膜性心脏病

	private String dfSmoking;// 吸烟

	private String dfLDL;// 血脂异常

	private String dfGlycuresis;// 糖尿病

	private String dfSportsLack;// 体育锻炼很少或轻体力劳动者

	private String dfOverweight;// 明显超重

	private String dfStrokeFamily;// 有脑卒中家族史

	private String dfStatus;// 评分

	@Field(type = FieldType.String, analyzer = "ik", searchAnalyzer = "ik", store = true)
	private String uuName;

	public String getAcid() {
		return acid;
	}

	public void setAcid(String acid) {
		this.acid = acid;
	}

	public String getDfStroke() {
		return dfStroke;
	}

	public void setDfStroke(String dfStroke) {
		this.dfStroke = dfStroke;
	}

	public String getDfTia() {
		return dfTia;
	}

	public void setDfTia(String dfTia) {
		this.dfTia = dfTia;
	}

	public String getDfHypertension() {
		return dfHypertension;
	}

	public void setDfHypertension(String dfHypertension) {
		this.dfHypertension = dfHypertension;
	}

	public String getDfAF() {
		return dfAF;
	}

	public void setDfAF(String dfAF) {
		this.dfAF = dfAF;
	}

	public String getDfSmoking() {
		return dfSmoking;
	}

	public void setDfSmoking(String dfSmoking) {
		this.dfSmoking = dfSmoking;
	}

	public String getDfLDL() {
		return dfLDL;
	}

	public void setDfLDL(String dfLDL) {
		this.dfLDL = dfLDL;
	}

	public String getDfGlycuresis() {
		return dfGlycuresis;
	}

	public void setDfGlycuresis(String dfGlycuresis) {
		this.dfGlycuresis = dfGlycuresis;
	}

	public String getDfSportsLack() {
		return dfSportsLack;
	}

	public void setDfSportsLack(String dfSportsLack) {
		this.dfSportsLack = dfSportsLack;
	}

	public String getDfOverweight() {
		return dfOverweight;
	}

	public void setDfOverweight(String dfOverweight) {
		this.dfOverweight = dfOverweight;
	}

	public String getDfStrokeFamily() {
		return dfStrokeFamily;
	}

	public void setDfStrokeFamily(String dfStrokeFamily) {
		this.dfStrokeFamily = dfStrokeFamily;
	}

	public String getDfStatus() {
		return dfStatus;
	}

	public void setDfStatus(String dfStatus) {
		this.dfStatus = dfStatus;
	}

	public String getUuName() {
		return uuName;
	}

	public void setUuName(String uuName) {
		this.uuName = uuName;
	}

}