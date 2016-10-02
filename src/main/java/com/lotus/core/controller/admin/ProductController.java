package com.lotus.core.controller.admin;

import com.lotus.common.page.Pagination;
import com.lotus.core.bean.product.*;
import com.lotus.core.query.product.*;
import com.lotus.core.service.product.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * @author wyy
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private BrandService brandService;

    @Autowired
    private ProductService productService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private FeatureService featureService;

    @Autowired
    private ColorService colorService;

    @Autowired
    private SkuService skuService;

    @Autowired
    private StaticPageService staticPageService;

    /**
     * 商品列表
     * @param pageNo
     * @param name
     * @param brandId
     * @param isShow
     * @param model
     * @return
     */
    @RequestMapping(value = "/list.do")
    public String list(Integer pageNo,String name,Integer brandId,Integer isShow,ModelMap model){

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
        productQuery.orderbyId(false);
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
        }
        //设置页号
        productQuery.setPageNo(Pagination.cpn(pageNo));
        Pagination pagination = productService.getProductListWithPage(productQuery);

        //分页页面展示
        String url = "/product/list.do";
        pagination.pageView(url, params.toString());

        model.addAttribute("pagination", pagination);

        return "product/list";
    }

    /**
     * 跳转到添加页面
     * @return
     */
    @RequestMapping("toAdd.do")
    public String toAdd(ModelMap model){

        //加载商品类型
        TypeQuery typeQuery = new TypeQuery();
        //指定查询哪些字段
        typeQuery.setFields("id,name");
        typeQuery.setIsDisplay(1);
        typeQuery.setParentId(0);
        List<Type> types = typeService.getTypeList(typeQuery);
        //显示在页面
        model.addAttribute("types", types);
        //加载商品品牌
        //品牌条件对象
        BrandQuery brandQuery = new BrandQuery();
        //设置指定字段
        brandQuery.setFields("id,name");
        //设置可见
        brandQuery.setIsDisplay(1);
        //加载品牌
        List<Brand> brands = brandService.getBrandList(brandQuery);
        //显示在页面
        model.addAttribute("brands", brands);
        //加载商品属性
        FeatureQuery featureQuery = new FeatureQuery();

        List<Feature> features = featureService.getFeatureList(featureQuery);
        //显示在页面
        model.addAttribute("features", features);
        //加载颜色
        ColorQuery colorQuery = new ColorQuery();
        colorQuery.setParentId(0);
        List<Color> colors = colorService.getColorList(colorQuery);
        //显示在页面
        model.addAttribute("colors", colors);
        return "product/add";
    }

    /**
     * 商品添加
     * @param product
     * @param img
     * @return
     */
    @RequestMapping(value = "/add.do")
    public String add(Product product, Img img){
        product.setImg(img);
        productService.addProduct(product);

        return "redirect:/product/list.do";
    }

    /**
     * 上架
     * @param ids
     * @param pageNo
     * @param name
     * @param brandId
     * @param isShow
     * @param model
     * @return
     */
    @RequestMapping(value = "/isShow.do")
    public String isShow(Integer[] ids,Integer pageNo,String name,Integer brandId,Integer isShow,ModelMap model){
        //实例化商品
        Product product = new Product();
        product.setIsShow(1);
        //上架
        if(null != ids && ids.length >0){
            for(Integer id : ids){
                product.setId(id);
                //修改上架状态
                productService.updateProductByKey(product);

                //页面静态化
                Map<String,Object> root = new HashMap<String,Object>();
                //设置值
                //商品加载
                Product p = productService.getProductByKey(id);

                root.put("product", p);

                //skus
                List<Sku> skus = skuService.getStock(id);
                root.put("skus", skus);
                //去重复
                List<Color>  colors = new ArrayList<Color>();
                //遍历SKu
                for(Sku sku : skus){
                    //判断集合中是否已经有此颜色对象了
                    if(!colors.contains(sku.getColor())){
                        colors.add(sku.getColor());
                    }
                }
                root.put("colors", colors);
                staticPageService.productIndex(root, id);
            }
        }

        //判断
        if(null != pageNo){
            model.addAttribute("pageNo", pageNo);
        }
        if(StringUtils.isNotBlank(name)){
            model.addAttribute("name", name);
        }
        if(null != brandId){
            model.addAttribute("brandId", brandId);
        }
        if(null != isShow){
            model.addAttribute("isShow", isShow);
        }

        return "redirect:/product/list.do";
    }
}
