package com.tz.dao;

import java.util.List;




import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.tz.model.TzMusicComment;

@Repository
@Transactional
public class CommentDao extends BaseDao{
	/**
	 * 保存评论
	 * @param comment
	 * @return
	 */
	public TzMusicComment save(TzMusicComment comment){
		try {
			getSession().save(comment);
			return comment;
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 根据期刊查询对应的评论并且分页
	 * @param periodId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<TzMusicComment> findComments(Integer periodId,Integer pageNo,Integer pageSize){
		String hqlString = "from TzMusicComment where period.period = ?and isDelete = 0 and status = 1 order by createTime desc";
		List<TzMusicComment> comments = getSession().createQuery(hqlString).setInteger(0, periodId).setFirstResult(pageNo).setMaxResults(pageSize).list();
		return comments;
	}
	/**
	 * 评论总数
	 * @param peroidId
	 * @return
	 */
	public Integer countComments(Integer peroidId){
		//查询出已发布的并且未删除的评论
		String countSql = "select count(1) from TzMusicComment where period.id = ? and isDelete = 0 and status = 1";
		Number number   = (Number)getSession().createQuery(countSql).setInteger(0, peroidId).uniqueResult();
		return number!=null?number.intValue():0;
	}
}
