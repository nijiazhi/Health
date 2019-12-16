package iscas.otcaix.di.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import iscas.otcaix.di.dao.IBasicInfoDao;
import iscas.otcaix.di.entity.HealthBasicInfo;

@Repository
public class BasicInfoDaoImpl extends BaseDaoImpl<HealthBasicInfo, String> implements IBasicInfoDao {

	public HealthBasicInfo getBasicInfoByPatientId(String patientId) {
		return null;
	}

	public List<String> getIpidByPatientId(String patientId) {
		return null;
	}

}
