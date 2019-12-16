package iscas.otcaix.di.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import iscas.otcaix.di.dao.IDiagnoseDao;
import iscas.otcaix.di.entity.HealthDiagnose;

@Repository
public class DiagnoseDaoImpl extends BaseDaoImpl<HealthDiagnose, String>implements IDiagnoseDao {

	@SuppressWarnings("unchecked")
	public List<HealthDiagnose> getDiagnoseByIpid(String ipid) {
		Criteria criteria = getCurrentSession().createCriteria(HealthDiagnose.class);
		criteria.add(Restrictions.eq("ipid", ipid));
		List<HealthDiagnose> diagnoseList = new ArrayList<HealthDiagnose>(criteria.list());
		return diagnoseList;
	}

}
