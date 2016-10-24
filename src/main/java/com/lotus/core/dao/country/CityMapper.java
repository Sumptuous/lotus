package com.lotus.core.dao.country;

import com.lotus.core.bean.country.City;
import com.lotus.core.query.country.CityQuery;

import java.util.List;

public interface CityMapper {

	/**
	 * 添加
	 * @param city
	 */
	Integer addCity(City city);

	/**
	 * 根据主键查找
	 * @param id
	 */
	City getCityByKey(Integer id);

	/**
	 * 根据主键批量查找
	 * @param idList
	 */
	List<City> getCitysByKeys(List<Integer> idList);

	/**
	 * 根据主键删除
	 * @param id
	 */
	Integer deleteByKey(Integer id);

	/**
	 * 根据主键批量删除
	 * @param idList
	 */
	Integer deleteByKeys(List<Integer> idList);

	/**
	 * 根据主键更新
	 * @param city
	 */
	Integer updateCityByKey(City city);

	/**
	 * 分页查询
	 * @param cityQuery
	 */
	List<City> getCityListWithPage(CityQuery cityQuery);

	/**
	 * 集合查询
	 * @param cityQuery
	 */
	List<City> getCityList(CityQuery cityQuery);
	
	/**
	 * 总条数
	 * @param cityQuery
	 */
	int getCityListCount(CityQuery cityQuery);
}
