package com.lotus.core.service.country;

import com.lotus.common.page.Pagination;
import com.lotus.core.bean.country.City;
import com.lotus.core.query.country.CityQuery;

import java.util.List;

/**
 * 
 * @author wyy
 */
public interface CityService {
	/**
	 * 基本插入
	 * 
	 * @return
	 */
	Integer addCity(City city);

	/**
	 * 根据主键查询
	 */
	City getCityByKey(Integer id);

	/**
	 * 根据主键批量查询
	 */
	List<City> getCitysByKeys(List<Integer> idList);

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
	Integer updateCityByKey(City city);

	/**
	 * 根据条件查询分页查询
	 * 
	 * @param cityQuery
	 *            查询条件
	 * @return
	 */
	Pagination getCityListWithPage(CityQuery cityQuery);

	/**
	 * 根据条件查询
	 * 
	 * @param cityQuery
	 *            查询条件
	 * @return
	 */
	List<City> getCityList(CityQuery cityQuery);
}
