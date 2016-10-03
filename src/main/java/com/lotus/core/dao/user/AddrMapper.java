package com.lotus.core.dao.user;

import com.lotus.core.bean.user.Addr;
import com.lotus.core.query.user.AddrQuery;

import java.util.List;

public interface AddrMapper {

	/**
	 * 添加
	 * @param addr
	 */
	Integer addAddr(Addr addr);

	/**
	 * 根据主键查找
	 * @param id
	 */
	Addr getAddrByKey(Integer id);

	/**
	 * 根据主键批量查找
	 * @param idList
	 */
	List<Addr> getAddrsByKeys(List<Integer> idList);

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
	 * @param addr
	 */
	Integer updateAddrByKey(Addr addr);

	/**
	 * 分页查询
	 * @param addrQuery
	 */
	List<Addr> getAddrListWithPage(AddrQuery addrQuery);

	/**
	 * 集合查询
	 * @param addrQuery
	 */
	List<Addr> getAddrList(AddrQuery addrQuery);
	
	/**
	 * 总条数
	 * @param addrQuery
	 */
	int getAddrListCount(AddrQuery addrQuery);
}
