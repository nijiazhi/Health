package iscas.otcaix.di.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DIAGNOSE")
public class HealthDiagnose{
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;//自增，业务无关
	
	@Column(name="IPID")
	private String ipid;
	
	
	@Column(name="ZD")
	private String zd;
	
	@Column(name="JBDM")
	private String jbdm;
	
	@Column(name="ZDSJ")
	private String zdsj;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getZd() {
		return zd;
	}

	public void setZd(String zd) {
		this.zd = zd;
	}

	public String getJbdm() {
		return jbdm;
	}

	public void setJbdm(String jbdm) {
		this.jbdm = jbdm;
	}

	public String getZdsj() {
		return zdsj;
	}

	public void setZdsj(String zdsj) {
		this.zdsj = zdsj;
	}

	public String getIpid() {
		return ipid;
	}

	public void setIpid(String ipid) {
		this.ipid = ipid;
	}
	
	
	
	
}
