package com.gec.auction.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CheckUserInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		//路径进行判断
		String url = req.getRequestURI();
		System.out.println(url);
		//不拦截登录和注册功能
		if(url.indexOf("doLogin")!=-1 || url.indexOf("register")!=-1){
			return true;
		}
		
		
		HttpSession session = req.getSession();
		//用户正常登录
		if(session.getAttribute("user")!=null){
			return true;
		}else{
			resp.sendRedirect(req.getContextPath()+"/login.jsp");
			return false;
		}
	}
	
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		

	}
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		

	}


	

}
