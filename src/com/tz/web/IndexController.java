package com.tz.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tz.dao.MusicPeriodDao;
import com.tz.model.TzMusicPeriod;



@Controller
public class IndexController {
	//期刊注入
	@Autowired
	private MusicPeriodDao musicPeriodDao;
	/**
	 * 跳转到登首页
	 * @return
	 */
	@RequestMapping("/index")
	public String indexs() {
		return "index";
	}
	/**
	 * 期刊列表页
	 * @return
	 */
	@RequestMapping("/list")
	public String index(Model model,HttpServletRequest request) {
		List<TzMusicPeriod> periods = musicPeriodDao.getAllPeriods();
		model.addAttribute("periodList", periods);
		return "list";
	}
	@RequestMapping("/")
	public String indexx() {
		return "redirect:/list";
	}
	/**
	 * 查询期刊到播放页面
	 * @param id
	 * @return
	 */
	@RequestMapping("/music/{id}")
	public  String music(@PathVariable("id")Integer id,Model model){
		TzMusicPeriod tzMusicPeriod = musicPeriodDao.getMusicPeriod(id);
		model.addAttribute("mPeriod", tzMusicPeriod);
		return "index";
	}
}
