package iscas.otcaix.di.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import iscas.otcaix.di.dao.ILaboratoryDao;
import iscas.otcaix.di.entity.HealthLaboratory;

@Repository
public class LaboratoryDaoImpl extends BaseDaoImpl<HealthLaboratory, String>implements ILaboratoryDao {

	@SuppressWarnings("unchecked")
	public List<HealthLaboratory> getLaboratoryByIpid(String ipid) {
		Criteria criteria = getCurrentSession().createCriteria(HealthLaboratory.class);
		criteria.add(Restrictions.eq("ipid", ipid));
		List<HealthLaboratory> laboratoryList = new ArrayList<HealthLaboratory>(criteria.list());
		return laboratoryList;
	}

}
