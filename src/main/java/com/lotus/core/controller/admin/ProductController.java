package com.lotus.core.controller.admin;

import com.lotus.core.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * @author wyy
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //商品列表
    @RequestMapping(value = "/list.do")
    public String list(Integer pageNo,String name,Integer brandId,Integer isShow,ModelMap model){
        productService.getProductListWithPage(pageNo, name, brandId, isShow, model);

        return "product/list";
    }
}
