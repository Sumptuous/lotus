package com.lotus.core.service.user;

import com.lotus.common.page.Pagination;
import com.lotus.core.bean.user.Addr;
import com.lotus.core.query.user.AddrQuery;

import java.util.List;

/**
 * 地址
 * @author wyy
 */
public interface AddrService {
	/**
	 * 基本插入
	 * 
	 * @return
	 */
	Integer addAddr(Addr addr);

	/**
	 * 根据主键查询
	 */
	Addr getAddrByKey(Integer id);

	/**
	 * 根据主键批量查询
	 */
	List<Addr> getAddrsByKeys(List<Integer> idList);

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
	Integer updateAddrByKey(Addr addr);

	/**
	 * 根据条件查询分页查询
	 * 
	 * @param addrQuery
	 *            查询条件
	 * @return
	 */
	Pagination getAddrListWithPage(AddrQuery addrQuery);

	/**
	 * 根据条件查询
	 * 
	 * @param addrQuery
	 *            查询条件
	 * @return
	 */
	List<Addr> getAddrList(AddrQuery addrQuery);
}
