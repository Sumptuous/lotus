package com.lotus.core.dao.product;

import com.lotus.core.bean.product.Img;
import com.lotus.core.query.product.ImgQuery;

import java.util.List;

public interface ImgMapper {
    /**
     * 添加
     * @param img
     */
    Integer addImg(Img img);

    /**
     * 根据主键查找
     * @param id
     */
    Img getImgByKey(Integer id);

    /**
     * 根据主键批量查找
     * @param idList
     */
    List<Img> getImgsByKeys(List<Integer> idList);

    /**
     * 根据主键删除
     * @param id
     */
    Integer deleteByKey(Integer id);

    /**
     * 根据主键批量删除
     * @param idList
     */
    Integer deleteByKeys(List<Integer> idList);

    /**
     * 根据主键更新
     * @param img
     */
    Integer updateImgByKey(Img img);

    /**
     * 分页查询
     * @param imgQuery
     */
    List<Img> getImgListWithPage(ImgQuery imgQuery);

    /**
     * 集合查询
     * @param imgQuery
     */
    List<Img> getImgList(ImgQuery imgQuery);

    /**
     * 总条数
     * @param imgQuery
     */
    int getImgListCount(ImgQuery imgQuery);
}