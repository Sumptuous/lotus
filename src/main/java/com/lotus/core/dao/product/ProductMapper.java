package com.lotus.core.dao.product;

import com.lotus.core.bean.product.Product;
import com.lotus.core.query.product.ProductQuery;

import java.util.List;

public interface ProductMapper {

    /**
     * 添加
     * @param product
     */
    Integer addProduct(Product product);

    /**
     * 根据主键查找
     * @param id
     */
    Product getProductByKey(Integer id);

    /**
     * 根据主键批量查找
     * @param idList
     */
    List<Product> getProductsByKeys(List<Integer> idList);

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
     * @param product
     */
    Integer updateProductByKey(Product product);

    /**
     * 分页查询
     * @param productQuery
     */
    List<Product> getProductListWithPage(ProductQuery productQuery);

    /**
     * 集合查询
     * @param productQuery
     */
    List<Product> getProductList(ProductQuery productQuery);

    /**
     * 总条数
     * @param productQuery
     */
    int getProductListCount(ProductQuery productQuery);
}