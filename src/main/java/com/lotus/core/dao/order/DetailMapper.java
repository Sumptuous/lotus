package com.lotus.core.dao.order;

import com.lotus.core.bean.order.Detail;
import com.lotus.core.query.order.DetailQuery;

import java.util.List;

public interface DetailMapper {

	/**
	 * 添加
	 * @param detail
	 */
	Integer addDetail(Detail detail);

	/**
	 * 根据主键查找
	 * @param id
	 */
	Detail getDetailByKey(Integer id);

	/**
	 * 根据主键批量查找
	 * @param idList
	 */
	List<Detail> getDetailsByKeys(List<Integer> idList);

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
	 * @param detail
	 */
	Integer updateDetailByKey(Detail detail);

	/**
	 * 分页查询
	 * @param detailQuery
	 */
	List<Detail> getDetailListWithPage(DetailQuery detailQuery);

	/**
	 * 集合查询
	 * @param detailQuery
	 */
	List<Detail> getDetailList(DetailQuery detailQuery);
	
	/**
	 * 总条数
	 * @param detailQuery
	 */
	int getDetailListCount(DetailQuery detailQuery);
}
