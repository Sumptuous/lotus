package com.lotus.core.dao.order;

import com.lotus.core.bean.order.Order;
import com.lotus.core.query.order.OrderQuery;

import java.util.List;

public interface OrderMapper {

	/**
	 * 添加
	 * @param order
	 */
	Integer addOrder(Order order);

	/**
	 * 根据主键查找
	 * @param id
	 */
	Order getOrderByKey(Integer id);

	/**
	 * 根据主键批量查找
	 * @param idList
	 */
	List<Order> getOrdersByKeys(List<Integer> idList);

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
	 * @param order
	 */
	Integer updateOrderByKey(Order order);

	/**
	 * 分页查询
	 * @param orderQuery
	 */
	List<Order> getOrderListWithPage(OrderQuery orderQuery);

	/**
	 * 集合查询
	 * @param orderQuery
	 */
	List<Order> getOrderList(OrderQuery orderQuery);
	
	/**
	 * 总条数
	 * @param orderQuery
	 */
	int getOrderListCount(OrderQuery orderQuery);
}
