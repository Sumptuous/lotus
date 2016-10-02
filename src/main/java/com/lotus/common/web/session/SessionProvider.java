package com.lotus.common.web.session;

import javax.servlet.http.HttpServletRequest;
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
	 * @param name
	 * @param value 用户对象
	 */
	void setAttribute(HttpServletRequest request, String name, Serializable value);

	/**
	 * 从Session中取值
	 * @param request
	 * @param name
	 * @return
	 */
	Serializable getAttribute(HttpServletRequest request, String name);

	/**
	 * 退出登陆
	 * @param request
	 */
	void logout(HttpServletRequest request);

	/**
	 * 获取SessionID
	 * @param request
	 * @return
	 */
	String getSessionId(HttpServletRequest request);
}
