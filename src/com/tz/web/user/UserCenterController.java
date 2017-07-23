package com.tz.web.user;



import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tz.dao.UserDao;
import com.tz.model.TzMusic;
import com.tz.model.TzMusicUser;
import com.tz.util.TmStringUtils;


/**
 * 用户中心类,只有登录才能进来哦
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/user")
public class UserCenterController {
	@Autowired
	private UserDao userDao;
	//跳往首页
	@RequestMapping("/index")
	public String index(){
		return "user/index";
	}
	/**
	 * 用户管理页面
	 * @return
	 */
	@RequestMapping("/userSys")
	public String userSys(Model model,HttpServletRequest request){
		List<TzMusicUser> userList = userDao.muserList();
		//拿到登录用户信息
		TzMusicUser logUser = (TzMusicUser)request.getSession().getAttribute("user");
		if(userList != null && userList.size() >0){
			model.addAttribute("userList", userList);
			model.addAttribute("logUser", logUser);
			return "user/sys";
		}else{
			return null;
		}
		
	}
	/**
	 * 通过用户id查询用户信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/upQuery")
	public @ResponseBody TzMusicUser getUpQuery(Integer id){
		return userDao.userById(id);
	}
	/**
	 * 查询用户信息去往修改页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/upUser/{id}")
	public String upUser(@PathVariable("id")Integer id,Model model){
		TzMusicUser user = userDao.userById(id);
		model.addAttribute("userM", user);
		return "/user/upUser";
	}
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	@RequestMapping("/userUpdate")
	public @ResponseBody String  updates(TzMusicUser user){
			try {
				System.out.println("id:"+user.getId());
				userDao.update(user);
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				return "fail";
			}
	}
	/**
	 * 返回密码修改页面
	 */
	@RequestMapping("/PsdUpdate")
	public String PsdUpdate(){
		return "user/psdUp";
	}
	/**
	 * 真实修改密码
	 * @param odlPsd
	 * @param newPsd
	 * @return
	 */
	@RequestMapping("/updatePsd")
	public @ResponseBody Map<String, Object> updatePsd(String odlPsd,String newPsd,HttpServletRequest request){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//重session中拿到用户信息
		TzMusicUser mUser = (TzMusicUser)request.getSession().getAttribute("user");
		TzMusicUser qUser = userDao.userById(mUser.getId());
		//旧密码与查询出来的密码对比
		if(TmStringUtils.md5Base64(odlPsd).equals(qUser.getPassword())){
			TzMusicUser musicUsers = new TzMusicUser();
			musicUsers.setPassword(TmStringUtils.md5Base64(newPsd));
			musicUsers.setId(mUser.getId());
			userDao.update(musicUsers);
			resultMap.put("result", 1);
			return resultMap;
		}else{
			resultMap.put("result", 0);
			return resultMap;
		}
	}
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteU")
	public @ResponseBody String deleteUser (Integer id){
		try {
			TzMusicUser userMusicUser = new TzMusicUser(id);
			userMusicUser.setIsDelete(1);
			userDao.update(userMusicUser);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}
}
