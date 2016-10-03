package com.lotus.core.dao.country;

import com.lotus.core.bean.country.Province;
import com.lotus.core.query.country.ProvinceQuery;

import java.util.List;

public interface ProvinceMapper {

	/**
	 * 添加
	 * @param province
	 */
	public Integer addProvince(Province province);

	/**
	 * 根据主键查找
	 * @param id
	 */
	public Province getProvinceByKey(Integer id);

	/**
	 * 根据主键批量查找
	 * @param idList
	 */
	public List<Province> getProvincesByKeys(List<Integer> idList);

	/**
	 * 根据主键删除
	 * @param id
	 */
	public Integer deleteByKey(Integer id);

	/**
	 * 根据主键批量删除
	 * @param idList
	 */
	public Integer deleteByKeys(List<Integer> idList);

	/**
	 * 根据主键更新
	 * @param province
	 */
	public Integer updateProvinceByKey(Province province);

	/**
	 * 分页查询
	 * @param provinceQuery
	 */
	public List<Province> getProvinceListWithPage(ProvinceQuery provinceQuery);

	/**
	 * 集合查询
	 * @param provinceQuery
	 */
	public List<Province> getProvinceList(ProvinceQuery provinceQuery);
	
	/**
	 * 总条数
	 * @param provinceQuery
	 */
	public int getProvinceListCount(ProvinceQuery provinceQuery);
}
