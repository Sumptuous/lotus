package com.lotus.core.service.product;

import com.lotus.core.bean.product.Sku;
import com.lotus.core.dao.product.SkuMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wyy
 */
@Service
public class SkuServiceImpl implements SkuService{

    @Resource
    private SkuMapper skuMapper;

    public Integer addSku(Sku sku) {
        return skuMapper.addSku(sku);
    }
}
