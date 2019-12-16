package iscas.otcaix.di.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import iscas.otcaix.di.pojo.RetrievalRecord;
import iscas.otcaix.di.service.ISimilarityRetrievalService;

@RestController
@RequestMapping(value="/similairyretrieval")
public class SimilarityRetrievalController {

	@Autowired
	private ISimilarityRetrievalService similarityService;

	public ISimilarityRetrievalService getSimilarityService() {
		return similarityService;
	}

	public void setSimilarityService(ISimilarityRetrievalService similarityService) {
		this.similarityService = similarityService;
	}

	private static final Logger logger = LogManager.getLogger(SimilarityRetrievalController.class);
	
	@RequestMapping(value="/{patientId}")
	public ModelAndView similairyRetrievalByPatientId(@PathVariable String patientId){
		logger.entry();
		List<RetrievalRecord> records = similarityService.retrievalSimilarCaseByPatientId(patientId);
		logger.exit();
		if(records!=null && !records.isEmpty()){
			return new ModelAndView("results","records",records);
		}else{
			return new ModelAndView("no-results");
		}
	}
	
}
