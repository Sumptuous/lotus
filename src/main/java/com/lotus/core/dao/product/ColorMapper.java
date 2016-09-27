package com.lotus.core.dao.product;

import com.lotus.core.bean.product.Color;
import com.lotus.core.query.product.ColorQuery;

import java.util.List;

public interface ColorMapper {
    /**
     * 添加
     * @param color
     */
    Integer addColor(Color color);

    /**
     * 根据主键查找
     * @param id
     */
    Color getColorByKey(Integer id);

    /**
     * 根据主键批量查找
     * @param idList
     */
    List<Color> getColorsByKeys(List<Integer> idList);

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
     * @param color
     */
    Integer updateColorByKey(Color color);

    /**
     * 分页查询
     * @param colorQuery
     */
    List<Color> getColorListWithPage(ColorQuery colorQuery);

    /**
     * 集合查询
     * @param colorQuery
     */
    List<Color> getColorList(ColorQuery colorQuery);

    /**
     * 总条数
     * @param colorQuery
     */
    int getColorListCount(ColorQuery colorQuery);
}