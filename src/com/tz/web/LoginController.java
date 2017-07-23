package com.tz.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tz.dao.UserDao;
import com.tz.model.TzMusicUser;
import com.tz.util.TmStringUtils;
import com.tz.util.ip.TmIpUtil;

@Controller
public class LoginController {
	@Autowired
	private UserDao userDao;
	/**
	 * 跳转到登录页
	 * @return
	 */
	@RequestMapping("/login")
	public String index() {
		return "login";
	}
	/**
	 * 跳转到注册页
	 * @return
	 */
	@RequestMapping("/resg")
	public String resg() {
		return "resgiter";
	}
	/**
	 * 注册账号
	 * @param account
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/resged/{account}/{password}",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> resged(@PathVariable("account")String account,@PathVariable("password")String password){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(TmStringUtils.isNotEmpty(account)&&TmStringUtils.isNotEmpty(password)){
			//注册之前,根据用户名和密码判断用户是否存在
			TzMusicUser user = userDao.getMusicUser(account, TmStringUtils.md5Base64(password));
			if(user != null){
				resultMap.put("result", "该用户已存在!");
				return resultMap;
			}else{
				TzMusicUser mUser = new TzMusicUser();
				mUser.setAccount(account);
				mUser.setPassword(TmStringUtils.md5Base64(password));
				mUser.setIsDelete(0);
				TzMusicUser rmUser = userDao.resgUser(mUser);
				if(rmUser != null){
					resultMap.put("result", 1);
					return resultMap;
				}else{
					resultMap.put("result", 0);
					return resultMap;
				}
			}
		}else{
			resultMap.put("result", 0);
			return resultMap;
		}
	}
	/**
	 * 登录验证
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value="/logined/{account}/{password}",method=RequestMethod.POST)
	public @ResponseBody TzMusicUser logined(@PathVariable("account")String account,@PathVariable("password")String password,HttpServletRequest request){
		if(TmStringUtils.isNotEmpty(account)&&TmStringUtils.isNotEmpty(password)){
			//根据用户名和密码判断用户是否存在    zAd+QHTVi1s6/paSGyIDZA==  zAd+QHTVi1s6/paSGyIDZA==
			TzMusicUser user = userDao.getMusicUser(account, TmStringUtils.md5Base64(password));
			if(user != null){
				//为安全性着想  密码设为空
				user.setPassword(null);
				//将用户信息放到session中
				request.getSession().setAttribute("user", user);
				//获取当前ip地址
				String address = TmIpUtil.ipLocation(request);
				request.getSession().setAttribute("address", address);
				return user;
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	/**
	 * 退出
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		//使session失效
		request.getSession().invalidate();
		return "redirect:/";
	}
}
