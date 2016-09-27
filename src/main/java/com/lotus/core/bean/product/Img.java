package com.lotus.core.bean.product;

import com.lotus.core.web.Constants;

import java.io.Serializable;

public class Img implements Serializable {
    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1L;

    /**主键id*/
    private Integer id;

    /**商品id*/
    private Integer productId;

    /**图片路径*/
    private String url;

    /**是否默认:0否 1:是*/
    private Integer isDef;

    //获取全Url
    public String getAllUrl(){
        return Constants.IMAGE_URL + url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getIsDef() {
        return isDef;
    }

    public void setIsDef(Integer isDef) {
        this.isDef = isDef;
    }

    @Override
    public String toString() {
        return "Img{" +
                "id=" + id +
                ", productId=" + productId +
                ", url='" + url + '\'' +
                ", isDef=" + isDef +
                '}';
    }
}