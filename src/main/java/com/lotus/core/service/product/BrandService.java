package com.lotus.core.service.product;

import com.lotus.common.page.Pagination;
import com.lotus.common.page.SortLimit;
import com.lotus.core.bean.product.Brand;
import com.lotus.core.query.product.BrandQuery;
import org.springframework.ui.ModelMap;

import java.util.List;

/**
 * @author wyy
 */
public interface BrandService {

    /**
     * 品牌列表
     * @param brandQuery
     * @return
     */
    Pagination getBrandListWithPage(BrandQuery brandQuery);

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

    /**
     * 品牌集合
     * @param brandQuery
     * @return
     */
    List<Brand> getBrandList(BrandQuery brandQuery);
}
