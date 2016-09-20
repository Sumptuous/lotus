package com.lotus.core.service.product;

import com.lotus.common.page.Pagination;
import com.lotus.core.bean.product.Product;
import com.lotus.core.dao.product.ProductMapper;
import com.lotus.core.query.product.ProductQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wyy
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Resource
    private BrandService brandService;

    @Resource
    private ProductMapper productMapper;

    @Transactional(readOnly = true)
    public Pagination getProductListWithPage(ProductQuery productQuery) {

        List<Product> productList = productMapper.getProductListWithPage(productQuery);
        int countProduct = productMapper.getProductListCount(productQuery);

        Pagination pagination = new Pagination(productQuery.getPageNo(), productQuery.getPageSize(), countProduct);
        pagination.setList(productList);

        return pagination;
    }
}
