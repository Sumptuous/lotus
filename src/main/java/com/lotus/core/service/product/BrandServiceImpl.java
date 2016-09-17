package com.lotus.core.service.product;

import com.lotus.common.page.Pagination;
import com.lotus.common.page.SortLimit;
import com.lotus.core.bean.product.Brand;
import com.lotus.core.dao.product.BrandMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import javax.annotation.Resource;
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
    public Pagination getBrandList(String name, Integer isDisplay, SortLimit sortLimit, ModelMap modelMap) {

        StringBuilder params = new StringBuilder();
        Brand brand = new Brand();
        if (StringUtils.isNotBlank(name)){
            brand.setName(name);
            params.append("name=").append(name);
        }
        brand.setIsDisplay(isDisplay);
        params.append("&").append("isDisplay=").append(isDisplay);

        //如果页号为null 或小于1  置为1
        sortLimit.setOffset(Pagination.cpn(sortLimit.getOffset()));
        int brandCount = brandMapper.getBrandCount(brand);
        List<Brand> getBrandList = brandMapper.getBrandListWithPage(brand, sortLimit);
        Pagination pagination = new Pagination(sortLimit.getOffset(), sortLimit.getMax(), brandCount);
        pagination.setList(getBrandList);

        String url = "/brand/list.do";
        pagination.pageView(url, params.toString());
        modelMap.addAttribute("pagination", pagination);
        modelMap.addAttribute("name", name);
        modelMap.addAttribute("isDisplay", isDisplay);
        return pagination;
    }

    public void addBrand(Brand brand) {
        brandMapper.addBrand(brand);
    }
}
