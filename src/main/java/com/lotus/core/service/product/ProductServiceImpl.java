package com.lotus.core.service.product;

import com.lotus.common.page.Pagination;
import com.lotus.core.bean.product.Brand;
import com.lotus.core.bean.product.Product;
import com.lotus.core.dao.product.ProductMapper;
import com.lotus.core.query.product.BrandQuery;
import com.lotus.core.query.product.ProductQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wyy
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Resource
    private BrandService brandService;

    @Resource
    private ProductMapper productMapper;

    @Transactional(readOnly = true)
    public Pagination getProductListWithPage(Integer pageNo,String name,Integer brandId,Integer isShow,ModelMap model) {
        BrandQuery brandQuery = new BrandQuery();

        brandQuery.setFields("id,name");
        brandQuery.setIsDisplay(1);
        //加载品牌
        List<Brand> brands = brandService.getBrandList(brandQuery);

        model.addAttribute("brands", brands);


        //分页参数
        StringBuilder params = new StringBuilder();

        //商品条件对象
        ProductQuery productQuery = new ProductQuery();
        //1:判断条件是为Null
        if(StringUtils.isNotBlank(name)){
            productQuery.setName(name);
            //要求模糊查询
            productQuery.setNameLike(true);

            params.append("&name=").append(name);

            //回显查询条件
            model.addAttribute("name", name);
        }
        //判断品牌ID
        if(null != brandId){
            productQuery.setBrandId(brandId);
            params.append("&").append("brandId=").append(brandId);

            //回显查询条件
            model.addAttribute("brandId", brandId);
        }
        //判断上下架状态
        if(null != isShow){
            productQuery.setIsShow(isShow);
            params.append("&").append("isShow=").append(isShow);

            //回显查询条件
            model.addAttribute("isShow", isShow);
        }else{
            productQuery.setIsShow(0);
            params.append("&").append("isShow=").append(0);
            //回显查询条件
            model.addAttribute("isShow", 0);
        }
        //设置页号
        productQuery.setPageNo(Pagination.cpn(pageNo));
        //设置每页数
        productQuery.setPageSize(5);

        List<Product> productList = productMapper.getProductListWithPage(productQuery);
        int countProduct = productMapper.getProductListCount(productQuery);

        Pagination pagination = new Pagination(productQuery.getPageNo(), productQuery.getPageSize(), countProduct);
        pagination.setList(productList);

        //分页页面展示
        String url = "/product/list.do";
        pagination.pageView(url, params.toString());

        model.addAttribute("pagination", pagination);

        return pagination;
    }
}
