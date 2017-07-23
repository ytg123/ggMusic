package com.tz.dao;



import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.tz.model.TzMusicPeriod;
import com.tz.util.TmStringUtils;
/**
 * 音乐期刊
 * @author Administrator
 *
 */
@Repository
@Transactional
public class MusicPeriodDao extends BaseDao<TzMusicPeriod,Integer>{
	/**
	 * 查询期刊
	 * @param id
	 * @return
	 */
	public TzMusicPeriod getMusicPeriod(Integer id){
		String hql  = "from TzMusicPeriod where id = ? and isDelete = 0";
		return (TzMusicPeriod)getSession().createQuery(hql).setInteger(0, id).uniqueResult();
	}
	/**
	 * 搜索查询所有音乐期刊
	 * @return
	 */
	public List<TzMusicPeriod> findAllPeriods(String keyWord,Integer typeId){
		//离线查询
		DetachedCriteria criteria = DetachedCriteria.forClass(TzMusicPeriod.class);
		if(TmStringUtils.isNotEmpty(keyWord)){
			criteria.add(Restrictions.like("title", keyWord, MatchMode.ANYWHERE));
		}
		if(typeId != null){
			criteria.add(Restrictions.eq("musicType.id", typeId));
		}
		return criteria.getExecutableCriteria(getSession()).list();
	}
	/**
	 * 查询所有期刊
	 * @return
	 */
	public List<TzMusicPeriod> getAllPeriods(){
		String hqlString = "from TzMusicPeriod where isDelete = 0";
		List<TzMusicPeriod> pList = getSession().createQuery(hqlString).list();
		return pList;
	}
	/**
	 * 保存期刊
	 * @param period
	 * @return
	 */
	public TzMusicPeriod savMusicPeriod(TzMusicPeriod period){
		try {
			getSession().save(period);
			return period;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 期刊修改
	 * 方法名：update
	 * @param period
	 * @return TzMusicPeriod
	 * @exception 
	 * @since  1.0.0
	 */
	public TzMusicPeriod update(TzMusicPeriod period){
		try {
			updateDefault(period);
			return period;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
