package com.lotus.core.service.product;

import com.lotus.common.page.Pagination;
import com.lotus.core.bean.product.Product;
import com.lotus.core.bean.product.Sku;
import com.lotus.core.dao.product.SkuMapper;
import com.lotus.core.query.product.SkuQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wyy
 */
@Service
@Transactional
public class SkuServiceImpl implements SkuService{

    @Resource
    private SkuMapper skuMapper;
    @Resource
    private ColorService colorService;
    @Resource
    private ProductService productService;

    /**
     * 插入数据库
     *
     * @return
     */
    public Integer addSku(Sku sku) {
        return skuMapper.addSku(sku);
    }

    /**
     * 根据主键查找
     */
    @Transactional(readOnly = true)
    public Sku getSkuByKey(Integer id) {
        Sku sku = skuMapper.getSkuByKey(id);

        //通过商品ID
        Product product = productService.getProductByKey(sku.getProductId());

        sku.setProduct(product);
        //颜色加载
        sku.setColor(colorService.getColorByKey(sku.getColorId()));
        return skuMapper.getSkuByKey(id);
    }

    @Transactional(readOnly = true)
    public List<Sku> getSkusByKeys(List<Integer> idList) {
        return skuMapper.getSkusByKeys(idList);
    }

    /**
     * 根据主键删除
     *
     * @return
     */
    public Integer deleteByKey(Integer id) {
        return skuMapper.deleteByKey(id);
    }

    public Integer deleteByKeys(List<Integer> idList) {
        return skuMapper.deleteByKeys(idList);
    }

    /**
     * 根据主键更新
     *
     * @return
     */
    public Integer updateSkuByKey(Sku sku) {
        return skuMapper.updateSkuByKey(sku);
    }

    @Transactional(readOnly = true)
    public Pagination getSkuListWithPage(SkuQuery skuQuery) {
        Pagination p = new Pagination(skuQuery.getPageNo(),skuQuery.getPageSize(),skuMapper.getSkuListCount(skuQuery));
        p.setList(skuMapper.getSkuListWithPage(skuQuery));
        return p;
    }

    @Transactional(readOnly = true)
    public List<Sku> getSkuList(SkuQuery skuQuery) {
        List<Sku> skus = skuMapper.getSkuList(skuQuery);
        //颜色加载完结
        for(Sku sku : skus){
            sku.setColor(colorService.getColorByKey(sku.getColorId()));
        }
        return skus;
    }

    public List<Sku> getStock(Integer productId) {
        // TODO Auto-generated method stub
        List<Sku> skus = skuMapper.getStock(productId);
        //颜色加载完结
        for(Sku sku : skus){
            sku.setColor(colorService.getColorByKey(sku.getColorId()));
        }
        return skus;
    }
}
