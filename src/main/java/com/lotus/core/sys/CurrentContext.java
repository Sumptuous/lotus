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
