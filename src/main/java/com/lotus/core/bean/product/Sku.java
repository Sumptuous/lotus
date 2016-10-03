package com.lotus.core.bean.product;

import java.io.Serializable;
import java.util.Date;

public class Sku implements Serializable {
    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1L;

    /**主键id*/
    private Integer id;

    /**商品id*/
    private Integer productId;

    /**颜色id*/
    private Integer colorId;

    /**尺码*/
    private String size;

    /**运费 默认10元*/
    private Double deliveFee;

    /**售价*/
    private Double skuPrice;

    /**库存*/
    private Integer stockInventory;

    /**购买限制*/
    private Integer skuUpperLimit;

    /**仓库位置:货架号*/
    private String location;

    /**SKU图片  精确到颜色及尺码对应的图片*/
    private String skuImg;

    /**前台显示排序*/
    private Integer skuSort;

    /**SKU名称*/
    private String skuName;

    /**市场价*/
    private Double marketPrice;

    /***/
    private Date createTime;

    /***/
    private Date updateTime;

    /***/
    private String createUserId;

    /***/
    private String updateUserId;

    /**0,历史 1最新*/
    private Integer lastStatus;

    /**0:赠品,1普通*/
    private Integer skuType;

    /**销量*/
    private Integer sales;

    /**添加颜色对象*/
    private Color color;

    private Product product;

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

    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }

    public Double getDeliveFee() {
        return deliveFee;
    }

    public void setDeliveFee(Double deliveFee) {
        this.deliveFee = deliveFee;
    }

    public Double getSkuPrice() {
        return skuPrice;
    }

    public void setSkuPrice(Double skuPrice) {
        this.skuPrice = skuPrice;
    }

    public Integer getStockInventory() {
        return stockInventory;
    }

    public void setStockInventory(Integer stockInventory) {
        this.stockInventory = stockInventory;
    }

    public Integer getSkuUpperLimit() {
        return skuUpperLimit;
    }

    public void setSkuUpperLimit(Integer skuUpperLimit) {
        this.skuUpperLimit = skuUpperLimit;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getSkuImg() {
        return skuImg;
    }

    public void setSkuImg(String skuImg) {
        this.skuImg = skuImg == null ? null : skuImg.trim();
    }

    public Integer getSkuSort() {
        return skuSort;
    }

    public void setSkuSort(Integer skuSort) {
        this.skuSort = skuSort;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName == null ? null : skuName.trim();
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId == null ? null : updateUserId.trim();
    }

    public Integer getLastStatus() {
        return lastStatus;
    }

    public void setLastStatus(Integer lastStatus) {
        this.lastStatus = lastStatus;
    }

    public Integer getSkuType() {
        return skuType;
    }

    public void setSkuType(Integer skuType) {
        this.skuType = skuType;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Sku{" +
                "id=" + id +
                ", productId=" + productId +
                ", colorId=" + colorId +
                ", size='" + size + '\'' +
                ", deliveFee=" + deliveFee +
                ", skuPrice=" + skuPrice +
                ", stockInventory=" + stockInventory +
                ", skuUpperLimit=" + skuUpperLimit +
                ", location='" + location + '\'' +
                ", skuImg='" + skuImg + '\'' +
                ", skuSort=" + skuSort +
                ", skuName='" + skuName + '\'' +
                ", marketPrice=" + marketPrice +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createUserId='" + createUserId + '\'' +
                ", updateUserId='" + updateUserId + '\'' +
                ", lastStatus=" + lastStatus +
                ", skuType=" + skuType +
                ", sales=" + sales +
                ", color=" + color +
                ", product=" + product +
                '}';
    }
}