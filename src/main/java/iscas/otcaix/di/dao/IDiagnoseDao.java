package iscas.otcaix.di.dao;

import java.util.List;

import iscas.otcaix.di.entity.HealthDiagnose;

public interface IDiagnoseDao extends IBaseDao<HealthDiagnose, String> {
	
	List<HealthDiagnose> getDiagnoseByIpid(String ipid);

}
