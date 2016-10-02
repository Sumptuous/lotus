package com.lotus.common.web.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * 本地Session
 * @author wyy
 *
 */
public class HttpSessionProvider implements SessionProvider{

	public void setAttribute(HttpServletRequest request, String name,
                             Serializable value) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();//true    Cookie JSESSIONID
		session.setAttribute(name, value);
	}

	public Serializable getAttribute(HttpServletRequest request, String name) {
		HttpSession session = request.getSession(false);
		if(session != null){
			return (Serializable) session.getAttribute(name);
		}
		return null;
	}

	public void logout(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if(session != null){
			session.invalidate();
		}
	}

	public String getSessionId(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return request.getSession().getId();
	}

}
