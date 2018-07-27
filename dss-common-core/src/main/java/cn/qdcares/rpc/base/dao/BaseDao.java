package cn.qdcares.rpc.base.dao;

import cn.qdcares.rpc.common.page.PageParam;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class BaseDao<T> implements IBaseDao<T> {

	private Class<T> clz;

	private Class<T> getClz() {
		if (clz == null)
			clz = (Class<T>) ((ParameterizedType) this.getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];
		return clz;
	}
	@Autowired
	protected SessionFactory sessionFactory;


	protected Session getSession() {
		try {
			return this.sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			return this.sessionFactory.openSession();
		}
	}

	@Override
	public T add(T t) {
		getSession().save(t);
		return t;
	}

	@Override
	public void delete(long id) {
		delete(get(id));
	}

	@Override
	public void delete(T t) {
		Session session = getSession();
		session.delete(t);
		session.flush();
	}
	
	@Override
	public void flush(){
		getSession().flush();
	}
	

	@Override
	public T get(long id) {
		T entity = (T) getSession().get(getClz(), id);
		return entity;
	}

	@Override
	public List<T> findAll() {
		return findByCriteria();
	}


	protected List<T> findByCriteria(Criterion... criterion) {
		Criteria crit = getSession().createCriteria(getClz());
		for (Criterion c : criterion) {
			if (c!=null) crit.add(c);
		}
		return crit.list();
	}

	protected List<T> findByPageCriteria(PageParam param,Criterion... criterion) {
		Criteria crit = getSession().createCriteria(getClz());
		crit.setFirstResult((param.getPageNum()-1)*param.getNumPerPage()).setMaxResults(param.getNumPerPage());
		for (Criterion c : criterion) {
			if (c!=null) crit.add(c);
		}
		return crit.list();
	}
	
	/*
	 * * 增加了排序的功能。
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByCriteria(Order order, Criterion... criterion) {
		Criteria crit = getSession().createCriteria(getClz());
		for (Criterion c : criterion) {
			crit.add(c);
		}
		if (order != null)
			crit.addOrder(order);
		return crit.list();
	}
	
	public T findUnique(Criterion... criterion) {
		Criteria crit = getSession().createCriteria(getClz());
		for (Criterion c : criterion) {
			if (c!=null){
				crit.add(c);
			}
		}
		List<T> rslt = crit.list();
		if (rslt.size() > 1) {
			throw new RuntimeException("期望唯一结果，但找到多个");
		}else if (rslt.isEmpty()) {
			return null;
		}else {
			return rslt.get(0);
		}
	}



	@Override
	public List<T> list(String hql) {
		return list(hql, null);
	}

	@Override
	public List<T> list(String hql, Object arg) {
		return list(hql, new Object[] { arg });
	}



	@Override
	public List<T> list(String hql, Object[] args) {
		Query q = this.getSession().createQuery(hql);
		if (args != null) {
			int index = 0;
			for (Object arg : args) {
				q.setParameter(index++, arg);
			}
		}
		return q.list();
	}


	public List<Object> listObj(String hql) {
		return listObj(hql, null);
	}

	public List<Object> listObj(String hql, Object arg) {
		return listObj(hql, new Object[] { arg });
	}

	public List<Object> listObj(String hql, Object[] args) {
		Query q = this.getSession().createQuery(hql);
		if (args != null) {
			int index = 0;
			for (Object arg : args) {
				q.setParameter(index++, arg);
			}
		}
		return q.list();
	}




	@Override
	public List<T> listPage(PageParam pageParam, String hql) {
		return listPage(pageParam,hql,null);
	}

	@Override
	public List<T> listPage(PageParam pageParam, String hql, Object arg) {
		return listPage(pageParam, hql, new Object[] { arg });
	}

	@Override
	public List<T> listPage(PageParam pageParam, String hql, Object[] args) {
		Query q = this.getSession().createQuery(hql);
		if (args != null) {
			int index = 0;
			for (Object arg : args) {
				q.setParameter(index++, arg);
			}
		}
		return q.setFirstResult(pageParam.getNumPerPage()*pageParam.getPageNum()).setMaxResults(pageParam.getNumPerPage()).list();
	}

	@Override
	public T load(long id) {
		return (T) getSession().load(getClz(), id);
	}

	@Override
	public T loadByHql(String hql) {
		return loadByHql(hql, null);
	}

	@Override
	public T loadByHql(String hql, Object arg) {
		return loadByHql(hql, new Object[] { arg });
	}

	@Override
	public T loadByHql(String hql, Object[] args) {
		T t = null;
		Query q = setQuery(hql, args);
		t = (T) q.uniqueResult();
		return t;
	}

	@Override
	public Object loadObjByHQL(String hql) {
		return loadObjByHQL(hql, null);
	}

	@Override
	public Object loadObjByHQL(String hql, Object arg) {
		return loadObjByHQL(hql, new Object[] { arg });
	}

	@Override
	public Object loadObjByHQL(String hql, Object[] args) {
		Object t = null;
		Query q = setQuery(hql, args);
		t = (Object) q.uniqueResult();
		return t;
	}

	public Query setQuery(String hql, Object[] args) {
		Query q = this.getSession().createQuery(hql);
		if (args != null) {
			int index = 0;
			for (Object arg : args) {
				q.setParameter(index++, arg);
			}
		}
		return q;
	}



	@Override
	public void update(T t) {
		getSession().update(t);
	}

	@Override
	public void updateByHQL(String hql) {
		this.updateByHQL(hql, null);
	}

	@Override
	public void updateByHQL(String hql, Object arg) {
		this.updateByHQL(hql, new Object[] { arg });

	}

	@Override
	public void updateByHQL(String hql, Object[] args) {
		Query q = setQuery(hql, args);
		q.executeUpdate();
	}

}
