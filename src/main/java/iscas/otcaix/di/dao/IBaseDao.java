package iscas.otcaix.di.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

public interface IBaseDao<E, I extends Serializable> {
	
	Session getCurrentSession();

	List<E> findAll();

	E find(I id);

	void save(E e);

	void update(E e);

	void saveOrUpdate(E e);
	
	void delete(E e);

	void flush();

}
