package com.lotus.core.dao.product;

import com.lotus.core.bean.product.Sku;
import com.lotus.core.query.product.SkuQuery;

import java.util.List;

public interface SkuMapper {
    /**
     * 添加
     * @param sku
     */
    public Integer addSku(Sku sku);

    /**
     * 根据主键查找
     * @param id
     */
    public Sku getSkuByKey(Integer id);

    /**
     * 根据主键批量查找
     * @param idList
     */
    public List<Sku> getSkusByKeys(List<Integer> idList);

    /**
     * 根据主键删除
     * @param id
     */
    public Integer deleteByKey(Integer id);

    /**
     * 根据主键批量删除
     * @param idList
     */
    public Integer deleteByKeys(List<Integer> idList);

    /**
     * 根据主键更新
     * @param sku
     */
    public Integer updateSkuByKey(Sku sku);

    /**
     * 分页查询
     * @param skuQuery
     */
    public List<Sku> getSkuListWithPage(SkuQuery skuQuery);

    /**
     * 集合查询
     * @param skuQuery
     */
    public List<Sku> getSkuList(SkuQuery skuQuery);

    /**
     * 总条数
     * @param skuQuery
     */
    public int getSkuListCount(SkuQuery skuQuery);
    /**
     * 库存大于>0
     */
    public List<Sku> getStock(Integer productId);
}