package iscas.otcaix.di.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SIMILARITY")
public class SimilarityPatientIndex {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "PSN_ID_X")
	private long psnIdX;
	@Column(name = "PSN_ID_Y")
	private long psnIdY;
	@Column(name = "SIMILARITY")
	private double similarity;

	public long getPsnIdX() {
		return psnIdX;
	}

	public void setPsnIdX(long psnIdX) {
		this.psnIdX = psnIdX;
	}

	public long getPsnIdY() {
		return psnIdY;
	}

	public void setPsnIdY(long psnIdY) {
		this.psnIdY = psnIdY;
	}

	public double getSimilarity() {
		return similarity;
	}

	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
