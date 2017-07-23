package com.tz.core.exception;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class MyExceptionHandler extends SimpleMappingExceptionResolver {

	@Override
	protected ModelAndView doResolveException(
			javax.servlet.http.HttpServletRequest httpServletRequest,
			javax.servlet.http.HttpServletResponse httpServletResponse,
			java.lang.Object o, java.lang.Exception e) {
		HandlerMethod handlerMethod = (HandlerMethod) o;
		System.out.println(handlerMethod.getBean().getClass().getName());
		System.out.println(handlerMethod.getMethod().getName());
		httpServletRequest.setAttribute("ex", e);
		System.out.println("出错的异常了类是：======" + o);
		e.printStackTrace();
		return super.doResolveException(httpServletRequest,
				httpServletResponse, o, e);
	}

}
