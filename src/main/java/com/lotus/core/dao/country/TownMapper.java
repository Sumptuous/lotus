package com.lotus.core.dao.country;

import com.lotus.core.bean.country.Town;
import com.lotus.core.query.country.TownQuery;

import java.util.List;

public interface TownMapper {

	/**
	 * 添加
	 * @param town
	 */
	Integer addTown(Town town);

	/**
	 * 根据主键查找
	 * @param id
	 */
	Town getTownByKey(Integer id);

	/**
	 * 根据主键批量查找
	 * @param idList
	 */
	List<Town> getTownsByKeys(List<Integer> idList);

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
	 * @param town
	 */
	Integer updateTownByKey(Town town);

	/**
	 * 分页查询
	 * @param townQuery
	 */
	List<Town> getTownListWithPage(TownQuery townQuery);

	/**
	 * 集合查询
	 * @param townQuery
	 */
	List<Town> getTownList(TownQuery townQuery);
	
	/**
	 * 总条数
	 * @param townQuery
	 */
	int getTownListCount(TownQuery townQuery);
}
