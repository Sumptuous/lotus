package com.lotus.core.dao.product;

import com.lotus.core.bean.product.Sku;
import com.lotus.core.query.product.SkuQuery;

import java.util.List;

public interface SkuMapper {
    /**
     * 添加
     * @param sku
     */
    Integer addSku(Sku sku);

    /**
     * 根据主键查找
     * @param id
     */
    Sku getSkuByKey(Integer id);

    /**
     * 根据主键批量查找
     * @param idList
     */
    List<Sku> getSkusByKeys(List<Integer> idList);

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
     * @param sku
     */
    Integer updateSkuByKey(Sku sku);

    /**
     * 分页查询
     * @param skuQuery
     */
    List<Sku> getSkuListWithPage(SkuQuery skuQuery);

    /**
     * 集合查询
     * @param skuQuery
     */
    List<Sku> getSkuList(SkuQuery skuQuery);

    /**
     * 总条数
     * @param skuQuery
     */
    int getSkuListCount(SkuQuery skuQuery);
    /**
     * 库存大于>0
     */
    List<Sku> getStock(Integer productId);
}