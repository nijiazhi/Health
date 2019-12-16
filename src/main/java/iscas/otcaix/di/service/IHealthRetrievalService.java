package iscas.otcaix.di.service;

import java.util.List;

import iscas.otcaix.di.entity.HealthBasicInfo;
import iscas.otcaix.di.entity.HealthDiagnose;
import iscas.otcaix.di.entity.HealthLaboratory;
import iscas.otcaix.di.entity.HealthMedicine;
import iscas.otcaix.di.entity.HealthOperation;

public interface IHealthRetrievalService {

	List<String> queryIpidByPatientId(String patienId);

	HealthBasicInfo queryHealthBasicInfoPatientId(String patientId);

	List<HealthLaboratory> queryHealthLaboratoryByPatientId(String patientId);

	List<HealthDiagnose> queryHealthDiagnoseByPatientId(String patientId);
	
	List<HealthMedicine> queryHealthMedicineByPatientId(String patientId);
	
	List<HealthOperation> queryHealthOperationByPatientId(String patientId);

}
