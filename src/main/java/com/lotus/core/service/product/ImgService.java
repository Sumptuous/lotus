package com.lotus.core.service.product;

import com.lotus.common.page.Pagination;
import com.lotus.core.bean.product.Img;
import com.lotus.core.query.product.ImgQuery;

import java.util.List;

/**
 * @author wyy
 */
public interface ImgService {

    /**
     * 基本插入
     *
     * @return
     */
    Integer addImg(Img img);

    /**
     * 根据主键查询
     */
    Img getImgByKey(Integer id);

    /**
     * 根据主键批量查询
     */
    List<Img> getImgsByKeys(List<Integer> idList);

    /**
     * 根据主键删除
     *
     * @return
     */
    Integer deleteByKey(Integer id);

    /**
     * 根据主键批量删除
     *
     * @return
     */
    Integer deleteByKeys(List<Integer> idList);

    /**
     * 根据主键更新
     *
     * @return
     */
    Integer updateImgByKey(Img img);

    /**
     * 根据条件查询分页查询
     *
     * @param imgQuery
     *            查询条件
     * @return
     */
    Pagination getImgListWithPage(ImgQuery imgQuery);

    /**
     * 根据条件查询
     *
     * @param imgQuery
     *            查询条件
     * @return
     */
    List<Img> getImgList(ImgQuery imgQuery);
}
