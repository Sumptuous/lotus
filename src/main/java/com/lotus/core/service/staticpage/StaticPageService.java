package com.lotus.core.service.staticpage;

import java.util.Map;

public interface StaticPageService {

	/**
	 * 静态化方法
	 * @param root
	 * @param id
	 */
	void productIndex(Map<String, Object> root, Integer id);
}
