package com.lotus.core.dao.product;

import com.lotus.core.bean.product.Img;

public interface ImgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Img record);

    int insertSelective(Img record);

    Img selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Img record);

    int updateByPrimaryKey(Img record);
}