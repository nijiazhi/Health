package iscas.otcaix.di.pojo;


/**
 * 数据库信息
 **/
public class TaNode {
	private int category;
	private String name;
	private int value;
	private String label;

	public TaNode(int category, String name, int value, String label) {
		this.category = category;
		this.name = name;
		this.value = value;
		this.label = label;
	}
	
	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}