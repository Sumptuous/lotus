package com.lotus.core.dao.product;

import com.lotus.core.bean.product.Type;
import com.lotus.core.query.product.TypeQuery;

import java.util.List;

public interface TypeMapper {
    /**
     * 添加
     * @param type
     */
    Integer addType(Type type);

    /**
     * 根据主键查找
     * @param id
     */
    Type getTypeByKey(Integer id);

    /**
     * 根据主键批量查找
     * @param idList
     */
    List<Type> getTypesByKeys(List<Integer> idList);

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
     * @param type
     */
    Integer updateTypeByKey(Type type);

    /**
     * 分页查询
     * @param typeQuery
     */
    List<Type> getTypeListWithPage(TypeQuery typeQuery);

    /**
     * 集合查询
     * @param typeQuery
     */
    List<Type> getTypeList(TypeQuery typeQuery);

    /**
     * 总条数
     * @param typeQuery
     */
    int getTypeListCount(TypeQuery typeQuery);
}