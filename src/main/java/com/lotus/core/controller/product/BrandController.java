package com.lotus.core.controller.product;

import com.lotus.common.page.Pagination;
import com.lotus.common.page.SortLimit;
import com.lotus.core.bean.product.Brand;
import com.lotus.core.service.product.BrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 品牌
 * @author wyy
 */
@Controller
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * 获取品牌列表
     * @param name
     * @param isDisplay
     * @param sortLimit
     * @param modelMap
     * @return
     */
    @RequestMapping("/list.do")
    public String list(String name, Integer isDisplay, SortLimit sortLimit, ModelMap modelMap){
        brandService.getBrandList(name, isDisplay, sortLimit, modelMap);
        return "brand/list";
    }

    /**
     * 跳转到品牌添加页面
     * @return
     */
    @RequestMapping("/toAdd.do")
    public String toAdd(){
        return "brand/add";
    }

    /**
     * 添加品牌
     * @param brand
     * @return
     */
    @RequestMapping(value = "/add.do")
    public String add(Brand brand){
        //添加开始
        brandService.addBrand(brand);
        return "redirect:/brand/list.do";
    }
}
