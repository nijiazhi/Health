package iscas.otcaix.di.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PSN_MASTER_INFO")
public class HealthBasicInfo{
	@Id
	@Column(name="PSN_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long psnId;//自增，业务无关
	
	@Column(name="ARCHIVE_DATE")
	private Date archiveDate; //建档时间
	
	@Column(name="NAME")
	private String name; //名字，中文
	
	@Column(name="NAME_PHONETIC")
	private String namePhonetic; //名字，拼音
	
	@Column(name="GIVEN_NAME")
	private String givenName;//名，中文
	
	@Column(name="FAMILY_NAME")
	private String familyName;//姓，中文
	
	@Column(name="BIRTHDAY")
	private Date birthday;//出生日期
	
	@Column(name="SEX_CODE")
	private String sexCode;//性别代码
	
	@Column(name="COUNTRY_CODE")
	private String countryCode;//国籍代码
	
	@Column(name="NATION_CODE")
	private String nationCode;//民族代码
	
	@Column(name="MARITAL_STATUS_CODE")
	private String maritalStatusCode;//婚姻状态代码
	
	@Column(name="EDUCATION_LEVEL_CODE")
	private String educationLevelCode;//文化水平代码

	public long getPsnId() {
		return psnId;
	}

	public void setPsnId(long psnId) {
		this.psnId = psnId;
	}

	public Date getArchiveDate() {
		return archiveDate;
	}

	public void setArchiveDate(Date archiveDate) {
		this.archiveDate = archiveDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNamePhonetic() {
		return namePhonetic;
	}

	public void setNamePhonetic(String namePhonetic) {
		this.namePhonetic = namePhonetic;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSexCode() {
		return sexCode;
	}

	public void setSexCode(String sexCode) {
		this.sexCode = sexCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getNationCode() {
		return nationCode;
	}

	public void setNationCode(String nationCode) {
		this.nationCode = nationCode;
	}

	public String getMaritalStatusCode() {
		return maritalStatusCode;
	}

	public void setMaritalStatusCode(String maritalStatusCode) {
		this.maritalStatusCode = maritalStatusCode;
	}

	public String getEducationLevelCode() {
		return educationLevelCode;
	}

	public void setEducationLevelCode(String educationLevelCode) {
		this.educationLevelCode = educationLevelCode;
	}
	
	
	

}
