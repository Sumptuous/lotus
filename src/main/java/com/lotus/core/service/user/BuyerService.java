package com.lotus.core.service.user;

import com.lotus.common.page.Pagination;
import com.lotus.core.bean.user.Buyer;
import com.lotus.core.query.user.BuyerQuery;
import com.lotus.core.sys.model.CurrentBuyer;

import java.util.List;

/**
 * 购买者
 * @author wyy
 */
public interface BuyerService {
	/**
	 * 基本插入
	 * 
	 * @return
	 */
	Integer addBuyer(Buyer buyer);

	/**
	 * 根据主键查询
	 */
	CurrentBuyer getBuyerByKey(String id);

	/**
	 * 根据主键批量查询
	 */
	List<Buyer> getBuyersByKeys(List<String> idList);

	/**
	 * 根据主键删除
	 * 
	 * @return
	 */
	Integer deleteByKey(String id);

	/**
	 * 根据主键批量删除
	 * 
	 * @return
	 */
	Integer deleteByKeys(List<String> idList);

	/**
	 * 根据主键更新
	 * 
	 * @return
	 */
	Integer updateBuyerByKey(Buyer buyer);

	/**
	 * 根据条件查询分页查询
	 * 
	 * @param buyerQuery
	 *            查询条件
	 * @return
	 */
	Pagination getBuyerListWithPage(BuyerQuery buyerQuery);

	/**
	 * 根据条件查询
	 * 
	 * @param buyerQuery
	 *            查询条件
	 * @return
	 */
	List<Buyer> getBuyerList(BuyerQuery buyerQuery);
}
