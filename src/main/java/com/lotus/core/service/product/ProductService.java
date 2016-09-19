package com.lotus.core.service.product;

import com.lotus.common.page.Pagination;
import org.springframework.ui.ModelMap;

/**
 * @author wyy
 */
public interface ProductService {

    Pagination getProductListWithPage(Integer pageNo,String name,Integer brandId,Integer isShow,ModelMap model);
}
