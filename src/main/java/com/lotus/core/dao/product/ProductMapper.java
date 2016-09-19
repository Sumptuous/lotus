package com.lotus.core.dao.product;

import com.lotus.core.bean.product.Product;
import com.lotus.core.query.product.ProductQuery;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKeyWithBLOBs(Product record);

    int updateByPrimaryKey(Product record);

    /**
     * 分页查询
     * @param productQuery
     */
    public List<Product> getProductListWithPage(ProductQuery productQuery);

    /**
     * 总条数
     * @param productQuery
     */
    public int getProductListCount(ProductQuery productQuery);
}