package iscas.otcaix.di.similarityEngineImp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import iscas.otcaix.di.dao.IBasicInfoDao;
import iscas.otcaix.di.entity.HealthBasicInfo;
import iscas.otcaix.di.pojo.RetrievalRecord;
import iscas.otcaix.di.similairyEngine.ISimilarityEngine;
import iscas.otcaix.di.similairyEngine.SimlarityFeedback;
import iscas.otcaix.di.util.DateUtil;

/**
 * 最基本的相似度计算引擎，没有学习过程
 * @author Andy 2017.02.08
 */
@Component
public class CommonSimilarityEngine implements ISimilarityEngine {

	@Autowired
	private IBasicInfoDao basicInforDao;

	private CommonSimilarityEngine instance = null;
	
	
	public IBasicInfoDao getBasicInforDao() {
		return basicInforDao;
	}

	public void setBasicInforDao(IBasicInfoDao basicInforDao) {
		this.basicInforDao = basicInforDao;
	}

	public CommonSimilarityEngine() {
		super();
	}

	/**
	 * 单例模式
	 */
	public CommonSimilarityEngine getInstance() {
		if (this.instance == null)
			return new CommonSimilarityEngine();
		return this.instance;
	}
	
	public List<String> similarityQueryForPatientID(String patientId) {
		List<String> similairy_retrieval_result_list = new ArrayList<String>();
		similairy_retrieval_result_list.add(patientId);
		return similairy_retrieval_result_list;
	}

	public List<RetrievalRecord> similarityQueryForPatientRecord(String patientId) {
		List<RetrievalRecord> similairy_retrieval_result_list = new ArrayList<RetrievalRecord>();
		List<String> similairy_patient_index_list = similarityQueryForPatientID(patientId);
		for(String curPatientId : similairy_patient_index_list){
			RetrievalRecord retrievalRecord = new RetrievalRecord(curPatientId);
			HealthBasicInfo basicInfor = basicInforDao.getBasicInfoByPatientId(patientId);
			retrievalRecord.setName(basicInfor.getName());
			retrievalRecord.setSexCode(basicInfor.getSexCode());
			retrievalRecord.setAge(DateUtil.getAge(basicInfor.getBirthday(), new Date()));
			similairy_retrieval_result_list.add(retrievalRecord);
		}
		return similairy_retrieval_result_list;
	}

	
	public Double similarityCompute() {
		return null;
	}

	public boolean similaritymodelTrain() {
		return false;
	}

	public boolean similarityModelDump(String path) {
		return false;
	}

	public boolean similarityModelLoad(String path) {
		return false;
	}

	public boolean feedback(SimlarityFeedback feedback) {
		return false;
	}

	public boolean similaritymodelUdate() {
		return false;
	}

}
