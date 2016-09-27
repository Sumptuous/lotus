package com.lotus.core.service.product;

import com.lotus.common.page.Pagination;
import com.lotus.core.bean.product.Type;
import com.lotus.core.dao.product.TypeMapper;
import com.lotus.core.query.product.TypeQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品类型
 * @author wyy
 */
@Service
@Transactional
public class TypeServiceImpl implements TypeService {

	@Resource
	TypeMapper typeMapper;

	/**
	 * 插入数据库
	 * 
	 * @return
	 */
	public Integer addType(Type type) {
		return typeMapper.addType(type);
	}

	/**
	 * 根据主键查找
	 */
	@Transactional(readOnly = true)
	public Type getTypeByKey(Integer id) {
		return typeMapper.getTypeByKey(id);
	}
	
	@Transactional(readOnly = true)
	public List<Type> getTypesByKeys(List<Integer> idList) {
		return typeMapper.getTypesByKeys(idList);
	}

	/**
	 * 根据主键删除
	 * 
	 * @return
	 */
	public Integer deleteByKey(Integer id) {
		return typeMapper.deleteByKey(id);
	}

	public Integer deleteByKeys(List<Integer> idList) {
		return typeMapper.deleteByKeys(idList);
	}

	/**
	 * 根据主键更新
	 * 
	 * @return
	 */
	public Integer updateTypeByKey(Type type) {
		return typeMapper.updateTypeByKey(type);
	}
	
	@Transactional(readOnly = true)
	public Pagination getTypeListWithPage(TypeQuery typeQuery) {
		Pagination p = new Pagination(typeQuery.getPageNo(),typeQuery.getPageSize(),typeMapper.getTypeListCount(typeQuery));
		p.setList(typeMapper.getTypeListWithPage(typeQuery));
		return p;
	}
	
	@Transactional(readOnly = true)
	public List<Type> getTypeList(TypeQuery typeQuery) {
		return typeMapper.getTypeList(typeQuery);
	}
}
