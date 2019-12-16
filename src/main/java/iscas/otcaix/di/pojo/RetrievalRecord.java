package iscas.otcaix.di.pojo;

/**
 * 检索记录
 * @author Andy
 */
public class RetrievalRecord {

	private String patientID;
	private String name;
	private int age;
	private String sexCode;

	public RetrievalRecord(String patientID) {
		super();
		this.patientID = patientID;
	}
	
	public String getPatientID() {
		return patientID;
	}
	
	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSexCode() {
		return sexCode;
	}

	public void setSexCode(String sexCode) {
		this.sexCode = sexCode;
	}

}
