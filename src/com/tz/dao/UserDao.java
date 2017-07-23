package com.tz.dao;



import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.tz.model.TzMusicUser;
/**
 * 根据用户账号和密码查询数据库
 * 用户dao数据层实现类
 * @author Administrator
 *
 */
@Repository
@Transactional
public class UserDao extends BaseDao<TzMusicUser,Integer>{
	/**
	 * 登录查询
	 * @param account
	 * @param password
	 * @return
	 */
	public  TzMusicUser getMusicUser(String account,String password){
		//hql语句写法,面向对象写法 全是JavaBean中的字段
		TzMusicUser tzMusicUser = (TzMusicUser)getSession().createQuery("from TzMusicUser where account = ? and password = ? and isDelete=0")
				.setString(0, account).setString(1, password).uniqueResult();
		
		return tzMusicUser;
	}
	/**
	 * 用户注册
	 * @param account
	 * @param password
	 * @return
	 */
	public TzMusicUser resgUser(TzMusicUser muser){
		try {
			getSession().save(muser);
			return muser;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 查询所有未删除的用户
	 * @return
	 */
	public List<TzMusicUser> muserList(){
		String hqlString = "from TzMusicUser where isDelete = 0";
		return getSession().createQuery(hqlString).list();
	}
	/**
	 * 根据用户id查询未删除的用户
	 * @return
	 */
	public TzMusicUser userById(Integer id){
		String hqlString = "from TzMusicUser where id = ? and isDelete = 0";
		TzMusicUser tUser = (TzMusicUser)getSession().createQuery(hqlString).setInteger(0, id).uniqueResult();
		return tUser;
	}
	/**
	 * 修改以及删除
	 * @param user
	 * @return
	 */
	public TzMusicUser update(TzMusicUser user){
		try {
			updateDefault(user);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
