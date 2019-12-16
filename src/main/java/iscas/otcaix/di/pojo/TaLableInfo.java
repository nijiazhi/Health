package iscas.otcaix.di.pojo;

/**
 * 标签信息
 **/
public class TaLableInfo {
	private Long id;
	private String parent_name;
	private String lable_name;
	private String lable_value;
	private Long patient_num;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParent_name() {
		return parent_name;
	}

	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}

	public String getLable_name() {
		return lable_name;
	}

	public void setLable_name(String lable_name) {
		this.lable_name = lable_name;
	}

	public String getLable_value() {
		return lable_value;
	}

	public void setLable_value(String lable_value) {
		this.lable_value = lable_value;
	}

	public Long getPatient_num() {
		return patient_num;
	}

	public void setPatient_num(Long patient_num) {
		this.patient_num = patient_num;
	}
}