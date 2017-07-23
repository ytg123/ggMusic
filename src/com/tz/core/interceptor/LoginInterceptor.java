package com.tz.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tz.model.TzMusicUser;
/**
 * 登录拦截器
 * @author Administrator
 *
 */
public class LoginInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {
		//冲session中拿到用户对象信息
		TzMusicUser user = (TzMusicUser)request.getSession().getAttribute("user");
		//是否是一个ajax请求
		String requestType = request.getHeader("X-Requested-With");
		if(user == null){
			if(requestType != null && requestType.equals("XMLHttpRequest")){
				response.getWriter().print("logout");
			}else{
				//如果为空,重定向到登录页面
				response.sendRedirect("/login");
			}
			return false;
		}else{
			return true;
		}
		
	}
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	

	

}
