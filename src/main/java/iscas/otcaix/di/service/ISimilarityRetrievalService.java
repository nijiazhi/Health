package iscas.otcaix.di.service;

import java.util.List;

import iscas.otcaix.di.pojo.RetrievalRecord;


public interface ISimilarityRetrievalService {
	
	List<RetrievalRecord> retrievalSimilarCaseByPatientId(String patientId);
	
}
