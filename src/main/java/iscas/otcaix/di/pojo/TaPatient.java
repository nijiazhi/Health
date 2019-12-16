package iscas.otcaix.di.pojo;

import java.util.ArrayList;
import java.util.List;


/**
 * 数据库信息
 **/
public class TaPatient {
	private List<TaNode> nodes = new ArrayList<TaNode>();
	private List<TaLink> links = new ArrayList<TaLink>();

	public List<TaNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<TaNode> nodes) {
		this.nodes = nodes;
	}

	public List<TaLink> getLinks() {
		return links;
	}

	public void setLinks(List<TaLink> links) {
		this.links = links;
	}
}