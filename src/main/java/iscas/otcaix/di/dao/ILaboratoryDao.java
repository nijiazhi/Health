package iscas.otcaix.di.dao;

import java.util.List;

import iscas.otcaix.di.entity.HealthLaboratory;

public interface ILaboratoryDao extends IBaseDao<HealthLaboratory, String> {

	List<HealthLaboratory> getLaboratoryByIpid(String ipid);

}
