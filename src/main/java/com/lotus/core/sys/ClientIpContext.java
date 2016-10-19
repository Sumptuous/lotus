package com.lotus.core.sys;

/**
 * 保存Client IP上下文
 *	
 * @author wyy
 */
public class ClientIpContext {
private static ThreadLocal<String> currentIp = new ThreadLocal<String>();
	
	public static void set(String ip) {
		currentIp.set(ip);
	}
	public static String get() {
		return currentIp.get();
	}
	public static void remove() {
		currentIp.remove();
	}
}
