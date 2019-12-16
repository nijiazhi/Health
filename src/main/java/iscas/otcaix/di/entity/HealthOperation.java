package iscas.otcaix.di.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="INP_OPERATION_ITEMS")
public class HealthOperation{
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;//自增序号
	
	@Column(name="HEALTH_EVN_ID")
	private long healthEvnId;//事件记录序号，来自于就诊事件总索引
	 
	@Column(name="OPERATION_NO")
	private String operationNo;//手术序号
	
	@Column(name="SCHEDULE_DATE_TIME")
	private Date scheduledDateTime;//手术时间
	
	@Column(name="OPERATION_CODE")
	private String operationCode;//手术操作编码
	
	@Column(name="OPERATION_NAME")
	private String operationName;//手术操作名称
	
	@Column(name="ANESTHESIA_METHOD")
	private String anesthesiaMethod;//麻醉方法
	
	@Column(name="ANESTHSIA_METHOD_CODE")
	private String anesthsiaMethodCode;//麻醉方法代码
	
	@Column(name="ANESTHESIA_DOCTOR")
	private String anesthesiaDoctor;//麻醉医师
	
	@Column(name="WOUND_GRADE")
	private String woundGrade;//切口等级编码
	
	@Column(name="HEAL_GRADE")
	private String healGrade;//切口愈合等级
	
	@Column(name="SURGEON")
	private String surgeon;//手术者
	
	@Column(name="FIRST_ASSISTANT")
	private String firstAssistant;//第一手术助手
	
	@Column(name="SECOND_ASSISTANT")
	private String secondAssistant;//第二手术助手
	
	@Column(name="THIRD_ASSISTANT")
	private String thirdAssistant;//第三手术助手
	
	@Column(name="FOURTH_ASSISTANT")
	private String fourthAssistant;//第四手术助手
	
	@Column(name="TARGET_SITE_CODE")
	private String targetSiteCode;//手术部位名称
	
	@Column(name="OPERATION_SCALE")
	private String operationScale;//手术等级
	
	@Column(name="OPERATION")
	private String operation;//手术描述
	
	@Column(name="OPERATION_CLASS")
	private String operationClass;//手术类型
	
	@Column(name="OPERATING_DEPT")
	private String operatingDept;//手术科室代码
	
	@Column(name="OPERATING_DEPT_NAME")
	private String operatingDeptName;//手术科室名称
	
	

}
