package com.lotus.core.bean.product;

import java.io.Serializable;

public class Color implements Serializable{
    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1L;

    /**主键id*/
    private Integer id;

    /**颜色名称*/
    private String name;

    /**父ID为色系*/
    private Integer parentId;

    /**颜色对应的衣服小图*/
    private String imgUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    @Override
    public String toString() {
        return "Color{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}