package com.lotus.core.service.product;

import com.lotus.common.page.Pagination;
import com.lotus.core.bean.product.Img;
import com.lotus.core.bean.product.Product;
import com.lotus.core.bean.product.Sku;
import com.lotus.core.dao.product.ProductMapper;
import com.lotus.core.query.product.ImgQuery;
import com.lotus.core.query.product.ProductQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Resource
    private ImgService imgService;

    @Resource
    private SkuService skuService;

    public Integer addProduct(Product product) {
        //商品编号
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String no = df.format(new Date());
        product.setNo(no);
        //添加时间
        product.setCreateTime(new Date());
        //影响到行数   返回商品ID
        //商品保存
        Integer i = productMapper.addProduct(product);
        //1:商品   图片   sku
        //2:图片
        //1)设置图片商品ID
        product.getImg().setProductId(product.getId());
        //2)
        product.getImg().setIsDef(1);
        imgService.addImg(product.getImg());
        //3:保存Sku    9,13,...
        //  S M  ...
        Sku sku = new Sku();
        //商品ID
        sku.setProductId(product.getId());
        //运费
        sku.setDeliveFee(10.00);
        //售价
        sku.setSkuPrice(0.00);
        //市场价
        sku.setMarketPrice(0.00);
        //库存
        sku.setStockInventory(0);
        //购买限制
        sku.setSkuUpperLimit(0);
        //添加时间
        sku.setCreateTime(new Date());
        //是否最新
        sku.setLastStatus(1);
        //商品
        sku.setSkuType(1);
        //销量
        sku.setSales(0);
        for(String color : product.getColor().split(",")){
            //颜色ID
            sku.setColorId(Integer.parseInt(color));

            for(String size : product.getSize().split(",")){
                //尺码
                sku.setSize(size);
                //保存SKu
                skuService.addSku(sku);
            }

        }
        return i;
    }

    @Transactional(readOnly = true)
    public Pagination getProductListWithPage(ProductQuery productQuery) {

        List<Product> productList = productMapper.getProductListWithPage(productQuery);
        int countProduct = productMapper.getProductListCount(productQuery);

        Pagination pagination = new Pagination(productQuery.getPageNo(), productQuery.getPageSize(), countProduct);
        for(Product product : productList){
            ImgQuery imgQuery = new ImgQuery();
            imgQuery.setProductId(product.getId());
            imgQuery.setIsDef(1);
            List<Img> imgs = imgService.getImgList(imgQuery);
            product.setImg(imgs.get(0));
        }
        pagination.setList(productList);

        return pagination;
    }
}
