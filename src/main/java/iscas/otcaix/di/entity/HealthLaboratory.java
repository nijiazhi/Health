package iscas.otcaix.di.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LABORATORY")
public class HealthLaboratory{
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;//自增，业务无关
	
	@Column(name="XMBM")
	private String xmbm;

	@Column(name="IPID")
	private String ipid;
	
	@Column(name="XMMC")
	private String xmmc;
	
	@Column(name="JYSJ")
	private String jysj;
	
	@Column(name="JG")
	private String jg;
	
	@Column(name="JGDW")
	private String jgdw;
	
	@Column(name="JGBJ")
	private String jgbj;
	
	@Column(name="CKFW")
	private String ckfw;
	
	@Column(name="YBMC")
	private String ybmc;
	
	
	
	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String getJysj() {
		return jysj;
	}

	public void setJysj(String jysj) {
		this.jysj = jysj;
	}

	public String getJg() {
		return jg;
	}

	public void setJg(String jg) {
		this.jg = jg;
	}

	public String getJgdw() {
		return jgdw;
	}

	public void setJgdw(String jgdw) {
		this.jgdw = jgdw;
	}

	public String getJgbj() {
		return jgbj;
	}

	public void setJgbj(String jgbj) {
		this.jgbj = jgbj;
	}

	public String getCkfw() {
		return ckfw;
	}

	public void setCkfw(String ckfw) {
		this.ckfw = ckfw;
	}

	public String getYbmc() {
		return ybmc;
	}

	public void setYbmc(String ybmc) {
		this.ybmc = ybmc;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getXmbm() {
		return xmbm;
	}

	public void setXmbm(String xmbm) {
		this.xmbm = xmbm;
	}

	public String getIpid() {
		return ipid;
	}

	public void setIpid(String ipid) {
		this.ipid = ipid;
	}
	
	
	
	 
}
