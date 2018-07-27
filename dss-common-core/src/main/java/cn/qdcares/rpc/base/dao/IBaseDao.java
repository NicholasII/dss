package cn.qdcares.rpc.base.dao;

import cn.qdcares.rpc.common.page.PageParam;

import java.util.List;

public interface IBaseDao<T> {
	/**
	 * 添加对象
	 * @param t 要添加的对象
	 * @return
	 */
	public T add(T t);
	/**
	 * 更新对象
	 * @param t
	 */
	public void update(T t);
	/**
	 * 根据对象进行删除
	 * @param t
	 */
	public void delete(T t);
	/**
	 * 根据id进行删除
	 * @param id
	 */
	public void delete(long id);
	
	/**
	 * 返回该表所有对象，不分页
	 * @return
	 */
	public List<T> findAll();
	
	/**
	 * 根据条件列表所有对象，不分页
	 * @param hql 查询条件
	 * @param args 条件参数
	 * @return 返回查询出来的所有数据
	 */
	public List<T> list(String hql, Object[] args);
	/**
	 * 根据条件列表所有对象，不分页
	 * @param hql 查询条件
	 * @return 返回查询出来的所有数据
	 */
	public List<T> list(String hql);
	/**
	 * 根据条件列表所有对象，不分页
	 * @param hql 查询条件
	 * @param arg 一个条件参数
	 * @return 返回查询出来的所有数据
	 */
	public List<T> list(String hql, Object arg);

	/**
	 * 根据条件列表所有对象，分页
	 * @param hql 查询条件
	 * @return 返回查询出来的所有数据
	 */
	public List<T> listPage(PageParam pageParam, String hql);

	/**
	 * 根据条件列表所有对象，分页
	 * @param hql 查询条件
	 * @param args 条件参数
	 * @return 返回查询出来的所有数据
	 */
	public List<T> listPage(PageParam pageParam,String hql, Object[] args);

	/**
	 * 根据条件列表所有对象，分页
	 * @param hql 查询条件
	 * @param arg 一个条件参数
	 * @return 返回查询出来的所有数据
	 */
	public List<T> listPage(PageParam pageParam,String hql, Object arg);

	/**
	 * 根据HQL加载一个对象
	 * @param hql 要加载对象的HQL
	 * @param args 加载的条件参数
	 * @return 加载的对象
	 */
	public T loadByHql(String hql, Object[] args);
	/**
	 * 根据HQL加载一个对象
	 * @param hql
	 * @param arg
	 * @return
	 */
	public T loadByHql(String hql, Object arg) ;
	/**
	 * 根据HQL加载一个对象
	 * @param hql
	 * @return
	 */
	public T loadByHql(String hql);
	/**
	 * 根据ID加载对象
	 * @param id 要加载对象的id
	 * @return 要加载对象的id
	 */
	public T load(long id);

	public T get(long id);


	/**
	 * 根据HQL加载非泛型对象
	 * @param hql
	 * @param args
	 * @return
	 */
	public Object loadObjByHQL(String hql, Object[] args);
	/**
	 * 加载非泛型对象
	 * @param hql
	 * @param arg
	 * @return
	 */
	public Object loadObjByHQL(String hql, Object arg);
	/**
	 * 加载非泛型对象
	 * @param hql
	 * @return
	 */
	public Object loadObjByHQL(String hql);
	/**
	 * 根据hql更新对象
	 * @param hql 要进行更新操作的hql
	 * @param args 更新参数
	 */
	public void updateByHQL(String hql, Object[] args);
	public void updateByHQL(String hql, Object arg);
	public void updateByHQL(String hql);
	/**
	 * flush the session.
	 */
	public void flush();
}
