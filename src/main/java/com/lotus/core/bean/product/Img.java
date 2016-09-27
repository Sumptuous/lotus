package com.lotus.core.bean.product;

import com.lotus.core.web.Constants;

public class Img {
    private Integer id;

    private Integer productId;

    private String url;

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
}