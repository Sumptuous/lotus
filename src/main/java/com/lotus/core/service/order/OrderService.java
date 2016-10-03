package com.lotus.core.service.order;

import com.lotus.common.page.Pagination;
import com.lotus.core.bean.BuyCart;
import com.lotus.core.bean.order.Order;
import com.lotus.core.query.order.OrderQuery;

import java.util.List;

/**
 * 订单
 * @author wyy
 */
public interface OrderService {
	/**
	 * 基本插入
	 * 
	 * @return
	 */
	Integer addOrder(Order order, BuyCart buyCart);

	/**
	 * 根据主键查询
	 */
	Order getOrderByKey(Integer id);

	/**
	 * 根据主键批量查询
	 */
	List<Order> getOrdersByKeys(List<Integer> idList);

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
	Integer updateOrderByKey(Order order);

	/**
	 * 根据条件查询分页查询
	 * 
	 * @param orderQuery
	 *            查询条件
	 * @return
	 */
	Pagination getOrderListWithPage(OrderQuery orderQuery);

	/**
	 * 根据条件查询
	 * 
	 * @param orderQuery
	 *            查询条件
	 * @return
	 */
	List<Order> getOrderList(OrderQuery orderQuery);
}
