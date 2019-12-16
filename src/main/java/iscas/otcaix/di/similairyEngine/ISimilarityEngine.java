package iscas.otcaix.di.similairyEngine;

import java.util.List;

import iscas.otcaix.di.pojo.RetrievalRecord;

/**
 * 
 * @author Andy 2017.02.08
 */
public interface ISimilarityEngine{
	
	/**
	 * 相似度引擎 --- 训练过程
	 */
	boolean similaritymodelTrain();
	
	/**
	 * 相似度引擎 --- 模型存储
	 * 存储形式一般为文件
	 * @param path
	 */
	boolean similarityModelDump(String path);
	
	/**
	 * 相似度引擎 --- 模型加载
	 * @param path
	 */
	boolean similarityModelLoad(String path);
	
	
	/**
	 * 相似度引擎  --- 相似度查询
	 * @param patientId 病人ID
	 * @return 相似病人集合
	 */
	List<String> similarityQueryForPatientID(String patientId);
	
	
	/**
	 * 相似度引擎  --- 相似度查询
	 * @param patientId 病人ID
	 * @return 相似病人集合
	 */
	List<RetrievalRecord> similarityQueryForPatientRecord(String patientId);
	
	
	
	/**
	 * 相似度引擎  --- 相似度计算
	 * 不同类型的相似度引擎  对应  不同的计算方法
	 */
	Double similarityCompute();
	
	
	/**
	 * 相似度引擎  --- 相似度反馈(存储)
	 * @param feedback
	 */
	boolean feedback(SimlarityFeedback feedback);
	
	/**
	 * 相似度引擎  --- 反馈模型更新
	 * 1、内部可以重新调用similaritymodelTrain，整体更新引擎中的模型
	 * 2、部分更新模型，与算法有关
	 */
	boolean similaritymodelUdate();
	
}
