package com.tz.dao;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.tz.model.TzMusic;
/**
 * 音乐dao数据层
 * @author Administrator
 *
 */
@Repository
@Transactional
public class MusicDao extends BaseDao<TzMusic, Integer>{
	/**
	 * 保存音乐
	 * @param music
	 * @return
	 */
	public TzMusic save(TzMusic music){
		try {
			getSession().save(music);
			return music;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 修改音乐
	 * @param music
	 * @return
	 */
	public TzMusic updateM(TzMusic music){
		try {
			updateDefault(music);
			return music;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 删除音乐
	 * @param music
	 * @return
	 */
	public boolean delete(Integer id){
		try {
			TzMusic music = (TzMusic)getSession().load(TzMusic.class, id);
			getSession().delete(music);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
