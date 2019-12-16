package iscas.otcaix.di.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 结果信息
 **/
public class EsResult {
	private long total;
	private List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<Map<String, Object>> getLists() {
		return lists;
	}

	public void setLists(List<Map<String, Object>> lists) {
		this.lists = lists;
	}
}