package com.lotus.core.service.product;

import com.lotus.common.page.Pagination;
import com.lotus.core.bean.product.Product;
import com.lotus.core.query.product.BrandQuery;
import com.lotus.core.query.product.ProductQuery;
import org.springframework.ui.ModelMap;

/**
 * @author wyy
 */
public interface ProductService {

    /**
     * 基本插入
     *
     * @return
     */
    Integer addProduct(Product product);

    /**
     * 根据条件查询分页查询
     *
     * @param productQuery
     *            查询条件
     * @return
     */
    Pagination getProductListWithPage(ProductQuery productQuery);
}
