package com.lotus.core.dao.product;

import com.lotus.core.bean.product.Feature;
import com.lotus.core.query.product.FeatureQuery;

import java.util.List;

public interface FeatureMapper {
    /**
     * 添加
     * @param feature
     */
    Integer addFeature(Feature feature);

    /**
     * 根据主键查找
     * @param id
     */
    Feature getFeatureByKey(Integer id);

    /**
     * 根据主键批量查找
     * @param idList
     */
    List<Feature> getFeaturesByKeys(List<Integer> idList);

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
     * @param feature
     */
    Integer updateFeatureByKey(Feature feature);

    /**
     * 分页查询
     * @param featureQuery
     */
    List<Feature> getFeatureListWithPage(FeatureQuery featureQuery);

    /**
     * 集合查询
     * @param featureQuery
     */
    List<Feature> getFeatureList(FeatureQuery featureQuery);

    /**
     * 总条数
     * @param featureQuery
     */
    int getFeatureListCount(FeatureQuery featureQuery);
}