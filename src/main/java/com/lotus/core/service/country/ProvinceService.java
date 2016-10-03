package com.lotus.core.service.country;

import com.lotus.common.page.Pagination;
import com.lotus.core.bean.country.Province;
import com.lotus.core.query.country.ProvinceQuery;

import java.util.List;

/**
 * 
 * @author wyy
 */
public interface ProvinceService {
	/**
	 * 基本插入
	 * 
	 * @return
	 */
	Integer addProvince(Province province);

	/**
	 * 根据主键查询
	 */
	Province getProvinceByKey(Integer id);

	/**
	 * 根据主键批量查询
	 */
	List<Province> getProvincesByKeys(List<Integer> idList);

	/**
	 * 根据主键删除
	 * 
	 * @return
	 */
	Integer deleteByKey(Integer id);

	/**
	 * 根据主键批量删除
	 * 
	 * @return
	 */
	Integer deleteByKeys(List<Integer> idList);

	/**
	 * 根据主键更新
	 * 
	 * @return
	 */
	Integer updateProvinceByKey(Province province);

	/**
	 * 根据条件查询分页查询
	 * 
	 * @param provinceQuery
	 *            查询条件
	 * @return
	 */
	Pagination getProvinceListWithPage(ProvinceQuery provinceQuery);

	/**
	 * 根据条件查询
	 * 
	 * @param provinceQuery
	 *            查询条件
	 * @return
	 */
	List<Province> getProvinceList(ProvinceQuery provinceQuery);
}
