package iscas.otcaix.di.similairyEngine;

/**
 * 相似度反馈
 * @author Andy 
 */
public class SimlarityFeedback {
	
	double similairScore;
	double feedbackComment;

	public double getSimilairScore() {
		return similairScore;
	}

	public void setSimilairScore(double similairScore) {
		this.similairScore = similairScore;
	}

	public double getFeedbackComment() {
		return feedbackComment;
	}

	public void setFeedbackComment(double feedbackComment) {
		this.feedbackComment = feedbackComment;
	}
}
