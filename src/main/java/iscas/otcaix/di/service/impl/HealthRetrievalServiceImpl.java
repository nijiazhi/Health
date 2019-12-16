package iscas.otcaix.di.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iscas.otcaix.di.dao.IBasicInfoDao;
import iscas.otcaix.di.dao.IDiagnoseDao;
import iscas.otcaix.di.dao.ILaboratoryDao;
import iscas.otcaix.di.dao.IMedicineDao;
import iscas.otcaix.di.dao.IOperationDao;
import iscas.otcaix.di.entity.HealthBasicInfo;
import iscas.otcaix.di.entity.HealthDiagnose;
import iscas.otcaix.di.entity.HealthLaboratory;
import iscas.otcaix.di.entity.HealthMedicine;
import iscas.otcaix.di.entity.HealthOperation;
import iscas.otcaix.di.service.IHealthRetrievalService;

@Service
public class HealthRetrievalServiceImpl implements IHealthRetrievalService {
	
	@Autowired
	private IBasicInfoDao basicInforDao;
	@Autowired
	private IDiagnoseDao diagnoseDao;
	@Autowired
	private ILaboratoryDao laboratoryDao;
	@Autowired
	private IMedicineDao medicineDao;
	@Autowired
	private IOperationDao operationDao;
	
	public IBasicInfoDao getBasicInforDao() {
		return basicInforDao;
	}


	public void setBasicInforDao(IBasicInfoDao basicInforDao) {
		this.basicInforDao = basicInforDao;
	}


	public IDiagnoseDao getDiagnoseDao() {
		return diagnoseDao;
	}


	public void setDiagnoseDao(IDiagnoseDao diagnoseDao) {
		this.diagnoseDao = diagnoseDao;
	}


	public ILaboratoryDao getLaboratoryDao() {
		return laboratoryDao;
	}


	public void setLaboratoryDao(ILaboratoryDao laboratoryDao) {
		this.laboratoryDao = laboratoryDao;
	}


	public IMedicineDao getMedicineDao() {
		return medicineDao;
	}


	public void setMedicineDao(IMedicineDao medicineDao) {
		this.medicineDao = medicineDao;
	}


	public IOperationDao getOperationDao() {
		return operationDao;
	}


	public void setOperationDao(IOperationDao operationDao) {
		this.operationDao = operationDao;
	}


	@SuppressWarnings("unused")
	private static final Logger logger = LogManager.getLogger(HealthRetrievalServiceImpl.class);

	
	public List<String> queryIpidByPatientId(String patienId) {
		return basicInforDao.getIpidByPatientId(patienId);
	}

	
	public HealthBasicInfo queryHealthBasicInfoPatientId(String patientId) {
		return basicInforDao.getBasicInfoByPatientId(patientId);
	}

	
	public List<HealthLaboratory> queryHealthLaboratoryByPatientId(String patientId) {
		List<String> ipid_list = basicInforDao.getIpidByPatientId(patientId);
		List<HealthLaboratory> result_list = new ArrayList<HealthLaboratory>();
		for(String ipid : ipid_list){
			List<HealthLaboratory> cur_ipid_list = laboratoryDao.getLaboratoryByIpid(ipid);
			result_list.addAll(cur_ipid_list);
		}
		return result_list;
	}

	
	public List<HealthDiagnose> queryHealthDiagnoseByPatientId(String patientId) {
		List<String> ipid_list = basicInforDao.getIpidByPatientId(patientId);
		List<HealthDiagnose> result_list = new ArrayList<HealthDiagnose>();
		for(String ipid : ipid_list){
			List<HealthDiagnose> cur_ipid_list = diagnoseDao.getDiagnoseByIpid(ipid);
			result_list.addAll(cur_ipid_list);
		}
		return result_list;
	}

	
	public List<HealthMedicine> queryHealthMedicineByPatientId(String patientId) {
		List<String> ipid_list = basicInforDao.getIpidByPatientId(patientId);
		List<HealthMedicine> result_list = new ArrayList<HealthMedicine>();
		for(String ipid : ipid_list){
			List<HealthMedicine> cur_ipid_list = medicineDao.getMedicineByIpid(ipid);
			result_list.addAll(cur_ipid_list);
		}
		return result_list;
	}

	
	public List<HealthOperation> queryHealthOperationByPatientId(String patientId) {
		List<String> ipid_list = basicInforDao.getIpidByPatientId(patientId);
		List<HealthOperation> result_list = new ArrayList<HealthOperation>();
		for(String ipid : ipid_list){
			List<HealthOperation> cur_ipid_list = operationDao.getOperationByIpid(ipid);
			result_list.addAll(cur_ipid_list);
		}
		return result_list;
	}

}
