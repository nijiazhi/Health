package iscas.otcaix.di.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import iscas.otcaix.di.dao.IOperationDao;
import iscas.otcaix.di.entity.HealthOperation;


@Repository
public class OperationDaoImpl extends BaseDaoImpl<HealthOperation, String>implements IOperationDao {

	@SuppressWarnings("unchecked")
	public List<HealthOperation> getOperationByIpid(String ipid) {
		Criteria criteria = getCurrentSession().createCriteria(HealthOperation.class);
		criteria.add(Restrictions.eq("ipid", ipid));
		List<HealthOperation> operationlist = new ArrayList<HealthOperation>(criteria.list());
		return operationlist;
	}

}
