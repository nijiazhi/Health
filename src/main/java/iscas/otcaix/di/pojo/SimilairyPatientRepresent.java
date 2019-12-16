package iscas.otcaix.di.pojo;

public class SimilairyPatientRepresent {
	
	private String patientId;
	private double[] featureVector;

	public SimilairyPatientRepresent(String patientId, double featureVector[]) {
		this.patientId = patientId;
		this.featureVector = featureVector;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public void setFeatureVector(double[] featureVector) {
		this.featureVector = featureVector;
	}

	public double[] getFeatureVector() {
		return featureVector;
	}
	
}
