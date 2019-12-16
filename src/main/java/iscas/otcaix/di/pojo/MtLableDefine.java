package iscas.otcaix.di.pojo;

/**
 * 标签信息
 **/
public class MtLableDefine {
	private Long id;
	private Long lable_id;
	private String lable_name;
	private String val_name;
	private String val_code;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLable_id() {
		return lable_id;
	}

	public void setLable_id(Long lable_id) {
		this.lable_id = lable_id;
	}

	public String getLable_name() {
		return lable_name;
	}

	public void setLable_name(String lable_name) {
		this.lable_name = lable_name;
	}

	public String getVal_name() {
		return val_name;
	}

	public void setVal_name(String val_name) {
		this.val_name = val_name;
	}

	public String getVal_code() {
		return val_code;
	}

	public void setVal_code(String val_code) {
		this.val_code = val_code;
	}
}