package iscas.otcaix.di.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import iscas.otcaix.di.dao.IBaseDao;

public class BaseDaoImpl<E, I extends Serializable> implements IBaseDao<E, I> {
	
	private Class<E> entityClass;
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		Type e = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) e;
		entityClass = (Class<E>) pt.getActualTypeArguments()[0];
	}

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<E> findAll() {
		return getCurrentSession().createCriteria(entityClass).list();
	}

	@SuppressWarnings("unchecked")
	public E find(I id) {
		return (E) getCurrentSession().get(entityClass, id);
	}

	public void save(E e) {
		getCurrentSession().save(e);
	}

	public void update(E e) {
		getCurrentSession().update(e);
	}

	public void saveOrUpdate(E e) {
		getCurrentSession().saveOrUpdate(e);
	}

	public void delete(E e) {
		getCurrentSession().delete(e);
	}

	public void flush() {
		getCurrentSession().flush();
	}

}
