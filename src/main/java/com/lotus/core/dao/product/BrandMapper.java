package com.lotus.core.dao.product;

import com.lotus.common.page.SortLimit;
import com.lotus.core.bean.product.Brand;
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
     * @param sortLimit
     * @return
     */
    List<Brand> getBrandListWithPage(@Param("brand") Brand brand, @Param("sl") SortLimit sortLimit);

    /**
     * 品牌总记录数
     * @param brand
     * @return
     */
    int getBrandCount(Brand brand);

    /**
     * 添加品牌
     * @param brand
     */
    void addBrand(Brand brand);
}