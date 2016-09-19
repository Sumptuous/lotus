package com.lotus.core.service.product;

import com.lotus.common.page.Pagination;
import com.lotus.common.page.SortLimit;
import com.lotus.core.bean.product.Brand;
import com.lotus.core.dao.product.BrandMapper;
import com.lotus.core.query.product.BrandQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by wangyangyang on 16/9/14.
 */
@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    @Resource
    private BrandMapper brandMapper;

    @Transactional(readOnly = true)
    public Pagination getBrandList(String name, Integer isDisplay, Integer pageNo, ModelMap modelMap) {

        StringBuilder params = new StringBuilder();
        BrandQuery brand = new BrandQuery();
        if (StringUtils.isNotBlank(name)){
            brand.setName(name);
            params.append("name=").append(name);
        }
        brand.setIsDisplay(isDisplay);
        params.append("&").append("isDisplay=").append(isDisplay);

        //如果页号为null 或小于1  置为1
        brand.setPageNo(Pagination.cpn(pageNo));
        int brandCount = brandMapper.getBrandCount(brand);
        List<Brand> getBrandList = brandMapper.getBrandListWithPage(brand);
        Pagination pagination = new Pagination(brand.getPageNo(), brand.getPageSize(), brandCount);
        pagination.setList(getBrandList);

        String url = "/brand/list.do";
        pagination.pageView(url, params.toString());
        modelMap.addAttribute("pagination", pagination);
        modelMap.addAttribute("name", name);
        modelMap.addAttribute("isDisplay", isDisplay);
        return pagination;
    }

    public void addBrand(Brand brand) {
        brand.setCreateTime(new Date());
        brand.setModifyTime(new Date());
        brandMapper.addBrand(brand);
    }

    public void deleteBrandByKey(Integer id) {
        brandMapper.deleteBrandByKey(id);

    }

    public void deleteBrandByKeys(Integer[] ids) {
        brandMapper.deleteBrandByKeys(ids);

    }

    public void updateBrandByKey(Brand brand) {
        brand.setModifyTime(new Date());
        brandMapper.updateBrandByKey(brand);

    }

    public Brand getBrandByKey(Integer id) {
        // TODO Auto-generated method stub
        return brandMapper.getBrandByKey(id);
    }

    public List<Brand> getBrandList(BrandQuery brandQuery) {
        // TODO Auto-generated method stub
        return brandMapper.getBrandList(brandQuery);
    }
}
