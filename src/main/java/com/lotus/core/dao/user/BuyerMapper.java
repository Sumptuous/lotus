package com.lotus.core.dao.user;

import com.lotus.core.bean.user.Buyer;
import com.lotus.core.query.user.BuyerQuery;
import com.lotus.core.sys.model.CurrentBuyer;

import java.util.List;

public interface BuyerMapper {

	/**
	 * 添加
	 * @param buyer
	 */
	Integer addBuyer(Buyer buyer);

	/**
	 * 根据主键查找
	 * @param id
	 */
	CurrentBuyer getBuyerByKey(String id);

	/**
	 * 根据主键批量查找
	 * @param idList
	 */
	List<Buyer> getBuyersByKeys(List<String> idList);

	/**
	 * 根据主键删除
	 * @param id
	 */
	Integer deleteByKey(String id);

	/**
	 * 根据主键批量删除
	 * @param idList
	 */
	Integer deleteByKeys(List<String> idList);

	/**
	 * 根据主键更新
	 * @param buyer
	 */
	Integer updateBuyerByKey(Buyer buyer);

	/**
	 * 分页查询
	 * @param buyerQuery
	 */
	List<Buyer> getBuyerListWithPage(BuyerQuery buyerQuery);

	/**
	 * 集合查询
	 * @param buyerQuery
	 */
	List<Buyer> getBuyerList(BuyerQuery buyerQuery);
	
	/**
	 * 总条数
	 * @param buyerQuery
	 */
	int getBuyerListCount(BuyerQuery buyerQuery);
}
