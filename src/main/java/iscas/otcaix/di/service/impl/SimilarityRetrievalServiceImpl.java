package iscas.otcaix.di.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iscas.otcaix.di.pojo.RetrievalRecord;
import iscas.otcaix.di.service.ISimilarityRetrievalService;
import iscas.otcaix.di.similarityEngineImp.CommonSimilarityEngine;

@Service
public class SimilarityRetrievalServiceImpl implements ISimilarityRetrievalService {
	
	@Autowired
	private CommonSimilarityEngine commonSimilarityEngine;
	
	@SuppressWarnings("unused")
	private static final Logger logger = LogManager.getLogger(SimilarityRetrievalServiceImpl.class);


	public List<RetrievalRecord> retrievalSimilarCaseByPatientId(String patientId) {
		return commonSimilarityEngine.similarityQueryForPatientRecord(patientId);
	}


	public CommonSimilarityEngine getCommonSimilarityEngine() {
		return commonSimilarityEngine;
	}


	public void setCommonSimilarityEngine(CommonSimilarityEngine commonSimilarityEngine) {
		this.commonSimilarityEngine = commonSimilarityEngine;
	}
	
}
