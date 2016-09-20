package com.lotus.core.dao.product;

import com.lotus.common.page.SortLimit;
import com.lotus.core.bean.product.Brand;
import com.lotus.core.query.product.BrandQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 数据交互层
 * @author wyy
 *
 */
public interface BrandMapper {
    /**
     * 品牌列表
     * @param brand
     * @return
     */
    List<Brand> getBrandListWithPage(BrandQuery brand);

    List<Brand> getBrandListWith(Brand brand);

    int getBrand(Brand brand);

    /**
     * 品牌总记录数
     * @param brand
     * @return
     */
    int getBrandCount(BrandQuery brand);

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
     * 查询集合
     * @param brandQuery
     * @return
     */
    public List<Brand> getBrandList(BrandQuery brandQuery);
}