package iscas.otcaix.di.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import iscas.otcaix.di.dao.IMedicineDao;
import iscas.otcaix.di.entity.HealthLaboratory;
import iscas.otcaix.di.entity.HealthMedicine;


@Repository
public class MedicineDaoImpl extends BaseDaoImpl<HealthMedicine, String>implements IMedicineDao {

	@SuppressWarnings("unchecked")
	public List<HealthMedicine> getMedicineByIpid(String ipid) {
		Criteria criteria = getCurrentSession().createCriteria(HealthLaboratory.class);
		criteria.add(Restrictions.eq("ipid", ipid));
		List<HealthMedicine> medicineList = new ArrayList<HealthMedicine>(criteria.list());
		return medicineList;
	}

}
