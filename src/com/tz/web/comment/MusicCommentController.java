package com.tz.web.comment;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tz.dao.CommentDao;
import com.tz.model.TzMusicComment;
import com.tz.model.TzMusicPeriod;
import com.tz.model.TzMusicUser;
import com.tz.util.ip.TmIpUtil;

@Controller
public class MusicCommentController {
	@Autowired
	private CommentDao commentDao;
	
	/**
	 * 保存评论  需要登录后才能操作
	 * @param periodId
	 * @param comment
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/comment/save/{pid}",method=RequestMethod.POST)
	public @ResponseBody String saveComment(@PathVariable("pid")Integer periodId,TzMusicComment comment,HttpServletRequest request){
		TzMusicUser user = (TzMusicUser)request.getSession().getAttribute("user");
		if(user != null && comment != null){
			comment.setPeriod(new TzMusicPeriod(periodId));
			comment.setUser(user);
			comment.setIp(TmIpUtil.getIpAddr(request));
			comment.setStatus(1);//已发布
			comment.setIsDelete(0);//未删除
			commentDao.save(comment);
			return "success";
		}else{
			return "fail";
		}
	}
	/**
	 * 查询评论信息   不需要登录
	 * @param periodId
	 * @return
	 */
	@RequestMapping("/nocomment/template/{pid}/{pno}/{psize}")
	public String templates(@PathVariable("pid")Integer periodId,@PathVariable("pno")Integer pageNo,@PathVariable("psize")Integer pageSize,Model model){
		Integer totalCount = commentDao.countComments(periodId);//总数
		List<TzMusicComment> comments = commentDao.findComments(periodId, pageNo, pageSize);
		model.addAttribute("comments", comments);
		model.addAttribute("totalCount", totalCount);
		return "template/comment/template";
	}
}
