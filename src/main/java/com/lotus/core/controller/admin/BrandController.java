package com.lotus.core.controller.admin;

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

    /**
     * 删除一个品牌
     * @param id
     * @param name
     * @param isDisplay
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete.do")
    public String delete(Integer id,String name,Integer isDisplay,ModelMap model){
        //TODO 删除

        brandService.deleteBrandByKey(id);
        if(StringUtils.isNotBlank(name)){
            model.addAttribute("name", name);
        }
        if(null != isDisplay){
            model.addAttribute("isDisplay", isDisplay);
        }

        return "redirect:/brand/list.do";
    }

    /**
     * 删除多个品牌
     * @param ids
     * @param name
     * @param isDisplay
     * @param model
     * @return
     */
    @RequestMapping(value = "/deletes.do")
    public String deletes(Integer[] ids,String name,Integer isDisplay,ModelMap model){
        //TODO 删除
        brandService.deleteBrandByKeys(ids);
//        if(StringUtils.isNotBlank(name)){
//            model.addAttribute("name", name);
//        }
//        if(null != isDisplay){
//            model.addAttribute("isDisplay", isDisplay);
//        }

        return "redirect:/brand/list.do";
    }

    /**
     * 跳转到修改页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/toEdit.do")
    public String toEdit(Integer id,ModelMap model){

        Brand brand = brandService.getBrandByKey(id);

        model.addAttribute("brand", brand);

        return "brand/edit";
    }

    /**
     * 修改页面
     * @param brand
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit.do")
    public String edit(Brand brand,ModelMap model){

        brandService.updateBrandByKey(brand);

        return "redirect:/brand/list.do";
    }
}
