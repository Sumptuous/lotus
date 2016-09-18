package com.lotus.core.dao.product;

import com.lotus.core.bean.product.Feature;

public interface FeatureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Feature record);

    int insertSelective(Feature record);

    Feature selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Feature record);

    int updateByPrimaryKey(Feature record);
}