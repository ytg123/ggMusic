package com.tz.dao;



import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;

import com.tz.dao.update.TmBeanUtil;
import com.tz.dao.update.TmUpdater;

/**
 * BaseDao  父类
 * @author Administrator
 *
 */
public class BaseDao<T, PK extends Serializable>{
	//注入
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		if(sessionFactory != null){
			//return sessionFactory.openSession();
			return sessionFactory.getCurrentSession();
		}
		return null;
	}
	/**
	 * 更新对象
	 * 方法名：updateDefault
	 * 创建人：xuchengfei 时间：2015年3月26日-下午10:33:16 
	 * @param entity
	 * @return T
	 * @exception 
	 * @since  1.0.0
	 */
	public T updateDefault(T entity) {
		return updateByUpdater(TmUpdater.create(entity));
	}
	
	
	@SuppressWarnings("rawtypes")
	private ClassMetadata getClassMetadata(Class clazz) {
		return (ClassMetadata) sessionFactory.getClassMetadata(clazz);
	}
	
	@SuppressWarnings("unchecked")
	public T updateByUpdater(TmUpdater updater) {
		ClassMetadata cm = getClassMetadata(updater.getBean().getClass());
		if (cm == null) {
			throw new RuntimeException("所更新的对象没有映射或不是实体对象");
		}
		Object bean = updater.getBean();
		T po = (T) getSession().load(bean.getClass(),cm.getIdentifier(bean));
		updaterCopyToPersistentObject(updater, po);
		return po;
	}
	
	/**
	 * 将更新对象拷贝至实体对象，并处理many-to-one的更新。
	 * 
	 * @param updater
	 * @param po
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void updaterCopyToPersistentObject(TmUpdater updater, Object po) {
		Map map = TmBeanUtil.describe(updater.getBean());
		Set<Map.Entry<String, Object>> set = map.entrySet();
		for (Map.Entry<String, Object> entry : set) {
			String name = entry.getKey();
			Object value = entry.getValue();
			if (!updater.isUpdate(name, value)) {
				continue;
			}
			if (value != null) {
				Class valueClass = value.getClass();
				ClassMetadata cm = getClassMetadata(valueClass);
				if (cm != null) {
					Serializable vid = cm.getIdentifier(value);
					// 如果更新的many to one的对象的id为空，则将many to one设置为null。
					if (vid != null) {
						value = getSession().load(valueClass, vid);
					} else {
						value = null;
					}
				}
			}
			try {
				PropertyUtils.setProperty(po, name, value);
			} catch (Exception e) {
			}
		}
	}
}
