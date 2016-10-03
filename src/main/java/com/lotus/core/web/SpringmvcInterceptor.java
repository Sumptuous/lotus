package com.lotus.core.web;

import com.lotus.common.web.session.SessionProvider;
import com.lotus.core.bean.user.Buyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理上下文
 * 处理Session
 * 全局
 * @author wyy
 *
 */
public class SpringmvcInterceptor implements HandlerInterceptor{

	@Autowired
	private SessionProvider sessionProvider;
	
	private Integer adminId;
	//常量
	private static final String INTERCEPTOR_URL = "/buyer/";

	//方法前   /buyer/
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		if(adminId != null){
			Buyer buyer = new Buyer();
			buyer.setUsername("fbb2014");
			sessionProvider.setAttribute(request,response, Constants.BUYER_SESSION, buyer);
			request.setAttribute("isLogin", true);
		}else{
			Buyer buyer = (Buyer) sessionProvider.getAttribute(request,response, Constants.BUYER_SESSION);
			boolean flag = false;
			if(null != buyer){
				flag = true;
			}
			request.setAttribute("isLogin", flag);
			
			//是否拦截
			String requestURI = request.getRequestURI();
			if(requestURI.startsWith(INTERCEPTOR_URL)){
				//必须登陆
				if(null == buyer){
					response.sendRedirect("/shopping/login.shtml?returnUrl=" + request.getParameter("returnUrl"));
					return false;
				}
			}
		}
		return true;
	}

	//方法后
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	//页面渲染后
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	

}
