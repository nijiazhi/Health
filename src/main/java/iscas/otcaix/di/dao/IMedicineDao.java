package iscas.otcaix.di.dao;

import java.util.List;

import iscas.otcaix.di.entity.HealthMedicine;

public interface IMedicineDao extends IBaseDao<HealthMedicine, String> {

	List<HealthMedicine> getMedicineByIpid(String ipid);
	
}
