package iscas.otcaix.di.dao;

import java.util.List;

import iscas.otcaix.di.entity.HealthBasicInfo;

public interface IBasicInfoDao extends IBaseDao<HealthBasicInfo, String> {

	HealthBasicInfo getBasicInfoByPatientId(String patientId);
	
	List<String> getIpidByPatientId(String patientId);
}
