package iscas.otcaix.di.dao;

import java.util.List;

import iscas.otcaix.di.entity.HealthOperation;

public interface IOperationDao extends IBaseDao<HealthOperation, String> {

	List<HealthOperation> getOperationByIpid(String ipid);
	
}
