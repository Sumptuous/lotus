/**
 * Copyright 2012 Alibaba.com All right reserved. This software is the
 * confidential and proprietary information of Alibaba.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Alibaba.com.
 *
 * File generated at: 2015年7月29日上午10:35:50
 */
package com.lotus.core.sys;


import com.lotus.core.sys.model.CurrentBuyer;

/**
 *
 * 用户信息线程变量
 *
 * @author wyy
 */
public class CurrentContext {

	private static ThreadLocal<CurrentBuyer> currentUser = new ThreadLocal<CurrentBuyer>();

	public static void remove() {
		currentUser.remove();
	}

	public static void set(CurrentBuyer buyer) {
		currentUser.set(buyer);
	}

	public static CurrentBuyer get() {
		return currentUser.get();
	}


}
