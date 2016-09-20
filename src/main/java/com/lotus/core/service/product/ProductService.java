package com.lotus.core.service.product;

import com.lotus.common.page.Pagination;
import com.lotus.core.query.product.BrandQuery;
import com.lotus.core.query.product.ProductQuery;
import org.springframework.ui.ModelMap;

/**
 * @author wyy
 */
public interface ProductService {

    Pagination getProductListWithPage(ProductQuery productQuery);
}
