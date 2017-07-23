package com.tz.web.period;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.select.Evaluator.IsEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import org.springframework.web.servlet.ModelAndView;

import com.tz.dao.MusicPeriodDao;
import com.tz.model.TzMusicPeriod;
import com.tz.model.TzMusicUser;

@Controller
@RequestMapping("/period")
public class MusicPeriodController {
	@Autowired
	private MusicPeriodDao musciPeriodDao;
	/**
	 * 添加音乐页面
	 * @return
	 */
	@RequestMapping("/addMusic")
	public String addMusic(Model model){
		TzMusicPeriod musicPeriod = new TzMusicPeriod();
		musicPeriod.setStatus(1);
		model.addAttribute("period", musicPeriod);
		return "music/add";
	}
	/**
	 * 修改音乐页面
	 * @param id
	 * @return
	 */
	@RequestMapping("/updateMusic/{id}")
	public ModelAndView addMusic(@PathVariable("id")Integer id){
		ModelAndView modelView = new ModelAndView();
		TzMusicPeriod musicPeriod =  musciPeriodDao.getMusicPeriod(id);
		if(musicPeriod != null){
			modelView.addObject("period", musicPeriod);
			modelView.setViewName("music/add");
		}
		return modelView;
	}
	/**
	 * 保存期刊
	 * @param period
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/savePeriod",method=RequestMethod.POST)
	public @ResponseBody TzMusicPeriod savePeriod (TzMusicPeriod period,HttpServletRequest request){
		TzMusicUser user = (TzMusicUser)request.getSession().getAttribute("user");
		period.setMusicUser(user);//保存用户
		period.setStatus(1);//发布状态  0未发布  1发布
		period.setHit(0);//点击数  默认为0
		period.setIsDelete(0);//删除状态 1删除  0未删除
		
		TzMusicPeriod tzMusicPeriod = musciPeriodDao.savMusicPeriod(period);
		
		if(tzMusicPeriod != null){
			return tzMusicPeriod;
		}else{
			return null;
		}
	}
	/**
	 * 修改期刊
	 * @param period
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public @ResponseBody TzMusicPeriod update(TzMusicPeriod period,HttpServletRequest request){
		if(period!=null && period.getId()!=null){
			TzMusicPeriod musicPeriod = musciPeriodDao.update(period);
			if(musicPeriod!=null){
				return musicPeriod;
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	/**
	 * 删除期刊  逻辑删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public @ResponseBody String deletePeriod(Integer id){
		try {
			TzMusicPeriod musicPeriod = new TzMusicPeriod(id);
			musicPeriod.setIsDelete(1);
			musciPeriodDao.update(musicPeriod);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
}
