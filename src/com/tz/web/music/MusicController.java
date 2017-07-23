package com.tz.web.music;








import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tz.dao.MusicDao;
import com.tz.model.TzMusic;
import com.tz.web.upload.TmFileUtil;

@Controller
@RequestMapping("/tzmusicg")
public class MusicController {
	@Autowired
	private MusicDao musicDao;
	/**
	 * 保存音乐
	 * @param music
	 * @return
	 * @throws JSONException
	 */
	@RequestMapping(value="/saveMusic",method=RequestMethod.POST)
	public @ResponseBody String save(TzMusic music) throws JSONException{
		music.setPattern(TmFileUtil.getExtNoPoint(music.getPath()));
		music.setLabelPath(null);
		music.setSort(0);
		music.setIsDelete(0);//删除状态0为删除1删除
		music.setHit(0);//点击数
		music.setStatus(0);// 发布状态0未发布1发布
		music = musicDao.save(music);
		String result = JSONUtil.serialize(music,null,null,false,true);
		return result;
	}
	/**
	 * 修改音乐信息
	 * @param music
	 * @return
	 */
	@RequestMapping("/updateM")
	public @ResponseBody String updateMusic(TzMusic music){
		try {
			musicDao.updateM(music);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
	/**
	 * 删除音乐
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteMusic/{id}")
	public @ResponseBody String deleteM(@PathVariable("id")Integer id){
		try {
			/*if(musicDao.delete(id)){
				return "success";
			}else{
				return "fail";
			}*/
			//逻辑删除
			TzMusic music = new TzMusic(id);
			music.setIsDelete(1);
			musicDao.updateM(music);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
}
