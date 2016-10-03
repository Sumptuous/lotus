package com.lotus.core.dao.user;

import com.lotus.core.bean.user.Buyer;
import com.lotus.core.query.user.BuyerQuery;

import java.util.List;

public interface BuyerMapper {

	/**
	 * 添加
	 * @param buyer
	 */
	public Integer addBuyer(Buyer buyer);

	/**
	 * 根据主键查找
	 * @param id
	 */
	public Buyer getBuyerByKey(String id);

	/**
	 * 根据主键批量查找
	 * @param idList
	 */
	public List<Buyer> getBuyersByKeys(List<String> idList);

	/**
	 * 根据主键删除
	 * @param id
	 */
	public Integer deleteByKey(String id);

	/**
	 * 根据主键批量删除
	 * @param idList
	 */
	public Integer deleteByKeys(List<String> idList);

	/**
	 * 根据主键更新
	 * @param buyer
	 */
	public Integer updateBuyerByKey(Buyer buyer);

	/**
	 * 分页查询
	 * @param buyerQuery
	 */
	public List<Buyer> getBuyerListWithPage(BuyerQuery buyerQuery);

	/**
	 * 集合查询
	 * @param buyerQuery
	 */
	public List<Buyer> getBuyerList(BuyerQuery buyerQuery);
	
	/**
	 * 总条数
	 * @param buyerQuery
	 */
	public int getBuyerListCount(BuyerQuery buyerQuery);
}
