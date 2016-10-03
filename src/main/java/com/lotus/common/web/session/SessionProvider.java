package com.lotus.common.web.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * Session 供应类
 * @author wyy
 *
 */
public interface SessionProvider {

	/**
	 * 往Session设置值
	 * @param request
	 * @param response
	 * @param name Constants  buyer_session
     * @param value 用户对象
     */
	void setAttribute(HttpServletRequest request, HttpServletResponse response, String name, Serializable value);

	/**
	 * 从Session中取值
	 * @param request
	 * @param response
	 * @param name
     * @return
     */
	Serializable getAttribute(HttpServletRequest request, HttpServletResponse response, String name);

	/**
	 * 退出登陆
	 * @param request
	 * @param response
     */
	void logout(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 获取SessionID
	 * @param request
	 * @param response
     * @return
     */
	String getSessionId(HttpServletRequest request, HttpServletResponse response);
}
