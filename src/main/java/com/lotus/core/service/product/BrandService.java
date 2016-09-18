package com.lotus.core.service.product;

import com.lotus.common.page.Pagination;
import com.lotus.common.page.SortLimit;
import com.lotus.core.bean.product.Brand;
import org.springframework.ui.ModelMap;

import java.util.List;

/**
 * Created by wangyangyang on 16/9/14.
 */
public interface BrandService {

    /**
     * 品牌列表
     * @param name
     * @param isDisplay
     * @param sortLimit
     * @param modelMap
     * @return
     */
    Pagination getBrandList(String name, Integer isDisplay, SortLimit sortLimit, ModelMap modelMap);

    /**
     * 添加品牌
     * @param brand
     */
    void addBrand(Brand brand);

    /**
     * 删除
     * @param id
     */
    void deleteBrandByKey(Integer id);

    /**
     * 删除 批量
     * @param ids
     */
    void deleteBrandByKeys(Integer[] ids);//List<Integer>  ids

    /**
     * 修改
     * @param brand
     */
    void updateBrandByKey(Brand brand);

    /**
     * 根据主键获取
     * @param id
     * @return
     */
    Brand getBrandByKey(Integer id);
}
