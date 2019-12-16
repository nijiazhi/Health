package iscas.otcaix.di.pojo;

import java.util.List;

/**
 * 查询信息
 **/
public class EsQuery {
	private String age;
	private String sex;
	private int pagefrom = 0;
	private int pagesize = 10;
	private List<EsItem> items;

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public List<EsItem> getItems() {
		return items;
	}

	public void setItems(List<EsItem> items) {
		this.items = items;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getPagefrom() {
		return pagefrom;
	}

	public void setPagefrom(int pagefrom) {
		this.pagefrom = pagefrom;
	}

}