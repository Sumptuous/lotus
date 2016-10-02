package com.lotus.core.service.product;

import com.lotus.common.page.Pagination;
import com.lotus.core.bean.product.Sku;
import com.lotus.core.query.product.SkuQuery;

import java.util.List;

/**
 * @author wyy
 */
public interface SkuService {

    /**
     * 基本插入
     *
     * @return
     */
    public Integer addSku(Sku sku);

    /**
     * 根据主键查询
     */
    public Sku getSkuByKey(Integer id);

    /**
     * 根据主键批量查询
     */
    public List<Sku> getSkusByKeys(List<Integer> idList);

    /**
     * 根据主键删除
     *
     * @return
     */
    public Integer deleteByKey(Integer id);

    /**
     * 根据主键批量删除
     *
     * @return
     */
    public Integer deleteByKeys(List<Integer> idList);

    /**
     * 根据主键更新
     *
     * @return
     */
    public Integer updateSkuByKey(Sku sku);

    /**
     * 根据条件查询分页查询
     *
     * @param skuQuery
     *            查询条件
     * @return
     */
    public Pagination getSkuListWithPage(SkuQuery skuQuery);

    /**
     * 根据条件查询
     *
     * @param skuQuery
     *            查询条件
     * @return
     */
    public List<Sku> getSkuList(SkuQuery skuQuery);

    /**
     * 库存大于>0
     */
    public List<Sku> getStock(Integer productId);
}
