package com.lotus.core.query.product;

import com.lotus.core.query.BaseQuery;
import com.lotus.core.query.OrderField;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductQuery extends BaseQuery{

    /**主键id*/
    private Integer id;

    /**商品编号*/
    private String no;

    /**商品名称*/
    private String name;

    /**重量 单位:克*/
    private Double weight;

    /**是否新品:0:旧品,1:新品*/
    private Integer isNew;

    /**是否热销:0,否 1:是*/
    private Integer isHot;

    /**推荐 1推荐 0 不推荐*/
    private Integer isCommend;

    /**添加时间*/
    private Date createTime;

    /**添加人ID*/
    private String createUserId;

    /**审核时间*/
    private Date checkTime;

    /**审核人ID*/
    private String checkUserId;

    /**上下架:0否 1是*/
    private Integer isShow;

    /**是否删除:0删除,1,没删除*/
    private Integer isDel;

    /**类型ID*/
    private Integer typeId;

    /**品牌ID*/
    private Integer brandId;

    /**检索关键词*/
    private String keywords;

    /**销量*/
    private Integer sales;

    /**颜色集*/
    private String feature;

    /**尺寸集*/
    private String color;

    /**商品属性集*/
    private String size;

    /**商品描述*/
    private String description;

    /**包装清单*/
    private String packageList;

    public String getDescription() {
        return description;
    }

    public ProductQuery setDescription(String description) {
        this.description = description == null ? null : description.trim();
        return this;
    }

    public String getPackageList() {
        return packageList;
    }

    public ProductQuery setPackageList(String packageList) {
        this.packageList = packageList == null ? null : packageList.trim();
        return this;
    }

    public Integer getId() {
        return id;
    }

    public ProductQuery setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getNo() {
        return no;
    }

    public ProductQuery setNo(String no) {
        this.no = no == null ? null : no.trim();
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductQuery setName(String name) {
        this.name = name == null ? null : name.trim();
        return this;
    }

    public Double getWeight() {
        return weight;
    }

    public ProductQuery setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    public Integer getIsNew() {
        return isNew;
    }

    public ProductQuery setIsNew(Integer isNew) {
        this.isNew = isNew;
        return this;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public ProductQuery setIsHot(Integer isHot) {
        this.isHot = isHot;
        return this;
    }

    public Integer getIsCommend() {
        return isCommend;
    }

    public ProductQuery setIsCommend(Integer isCommend) {
        this.isCommend = isCommend;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public ProductQuery setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public ProductQuery setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
        return this;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public ProductQuery setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
        return this;
    }

    public String getCheckUserId() {
        return checkUserId;
    }

    public ProductQuery setCheckUserId(String checkUserId) {
        this.checkUserId = checkUserId == null ? null : checkUserId.trim();
        return this;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public ProductQuery setIsShow(Integer isShow) {
        this.isShow = isShow;
        return this;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public ProductQuery setIsDel(Integer isDel) {
        this.isDel = isDel;
        return this;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public ProductQuery setTypeId(Integer typeId) {
        this.typeId = typeId;
        return this;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public ProductQuery setBrandId(Integer brandId) {
        this.brandId = brandId;
        return this;
    }

    public String getKeywords() {
        return keywords;
    }

    public ProductQuery setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
        return this;
    }

    public Integer getSales() {
        return sales;
    }

    public ProductQuery setSales(Integer sales) {
        this.sales = sales;
        return this;
    }

    public String getFeature() {
        return feature;
    }

    public ProductQuery setFeature(String feature) {
        this.feature = feature == null ? null : feature.trim();
        return this;
    }

    public String getColor() {
        return color;
    }

    public ProductQuery setColor(String color) {
        this.color = color == null ? null : color.trim();
        return this;
    }

    public String getSize() {
        return size;
    }

    public ProductQuery setSize(String size) {
        this.size = size == null ? null : size.trim();
        return this;
    }

    /**======================================是否模糊查询====================================*/
    private boolean noLike;

    private boolean nameLike;

    private boolean createUserIdLike;

    private boolean checkUserIdLike;

    private boolean keywordsLike;

    private boolean descriptionLike;

    private boolean packageListLike;

    private boolean featureLike;

    private boolean colorLike;

    private boolean sizeLike;

    public boolean isNoLike() {
        return noLike;
    }

    public ProductQuery setNoLike(boolean noLike) {
        this.noLike = noLike;
        return this;
    }

    public boolean isNameLike() {
        return nameLike;
    }

    public ProductQuery setNameLike(boolean nameLike) {
        this.nameLike = nameLike;
        return this;
    }

    public boolean isCreateUserIdLike() {
        return createUserIdLike;
    }

    public ProductQuery setCreateUserIdLike(boolean createUserIdLike) {
        this.createUserIdLike = createUserIdLike;
        return this;
    }

    public boolean isCheckUserIdLike() {
        return checkUserIdLike;
    }

    public ProductQuery setCheckUserIdLike(boolean checkUserIdLike) {
        this.checkUserIdLike = checkUserIdLike;
        return this;
    }

    public boolean isKeywordsLike() {
        return keywordsLike;
    }

    public ProductQuery setKeywordsLike(boolean keywordsLike) {
        this.keywordsLike = keywordsLike;
        return this;
    }

    public boolean isDescriptionLike() {
        return descriptionLike;
    }

    public ProductQuery setDescriptionLike(boolean descriptionLike) {
        this.descriptionLike = descriptionLike;
        return this;
    }

    public boolean isPackageListLike() {
        return packageListLike;
    }

    public ProductQuery setPackageListLike(boolean packageListLike) {
        this.packageListLike = packageListLike;
        return this;
    }

    public boolean isFeatureLike() {
        return featureLike;
    }

    public ProductQuery setFeatureLike(boolean featureLike) {
        this.featureLike = featureLike;
        return this;
    }

    public boolean isColorLike() {
        return colorLike;
    }

    public ProductQuery setColorLike(boolean colorLike) {
        this.colorLike = colorLike;
        return this;
    }

    public boolean isSizeLike() {
        return sizeLike;
    }

    public ProductQuery setSizeLike(boolean sizeLike) {
        this.sizeLike = sizeLike;
        return this;
    }

    /**==============================批量查询时的Order条件顺序设置==========================*/
    /**排序表字段*/
    private List<OrderField> orderFields = new ArrayList<OrderField>();

    /**
     * 设置排序按属性：id
     *
     * @param isAsc
     *            是否升序，否则为降序
     */
    public ProductQuery orderbyId(boolean isAsc) {
        orderFields.add(new OrderField("id", isAsc ? "ASC" : "DESC"));
        return this;
    }
    /**
     * 设置排序按属性：no
     *
     * @param isAsc
     *            是否升序，否则为降序
     */
    public ProductQuery orderbyNo(boolean isAsc) {
        orderFields.add(new OrderField("no", isAsc ? "ASC" : "DESC"));
        return this;
    }
    /**
     * 设置排序按属性：name
     *
     * @param isAsc
     *            是否升序，否则为降序
     */
    public ProductQuery orderbyName(boolean isAsc) {
        orderFields.add(new OrderField("name", isAsc ? "ASC" : "DESC"));
        return this;
    }
    /**
     * 设置排序按属性：weight
     *
     * @param isAsc
     *            是否升序，否则为降序
     */
    public ProductQuery orderbyWeight(boolean isAsc) {
        orderFields.add(new OrderField("weight", isAsc ? "ASC" : "DESC"));
        return this;
    }
    /**
     * 设置排序按属性：is_new
     *
     * @param isAsc
     *            是否升序，否则为降序
     */
    public ProductQuery orderbyIsNew(boolean isAsc) {
        orderFields.add(new OrderField("is_new", isAsc ? "ASC" : "DESC"));
        return this;
    }
    /**
     * 设置排序按属性：is_hot
     *
     * @param isAsc
     *            是否升序，否则为降序
     */
    public ProductQuery orderbyIsHot(boolean isAsc) {
        orderFields.add(new OrderField("is_hot", isAsc ? "ASC" : "DESC"));
        return this;
    }
    /**
     * 设置排序按属性：is_commend
     *
     * @param isAsc
     *            是否升序，否则为降序
     */
    public ProductQuery orderbyIsCommend(boolean isAsc) {
        orderFields.add(new OrderField("is_commend", isAsc ? "ASC" : "DESC"));
        return this;
    }
    /**
     * 设置排序按属性：create_time
     *
     * @param isAsc
     *            是否升序，否则为降序
     */
    public ProductQuery orderbyCreateTime(boolean isAsc) {
        orderFields.add(new OrderField("create_time", isAsc ? "ASC" : "DESC"));
        return this;
    }
    /**
     * 设置排序按属性：create_user_id
     *
     * @param isAsc
     *            是否升序，否则为降序
     */
    public ProductQuery orderbyCreateUserId(boolean isAsc) {
        orderFields.add(new OrderField("create_user_id", isAsc ? "ASC" : "DESC"));
        return this;
    }
    /**
     * 设置排序按属性：check_time
     *
     * @param isAsc
     *            是否升序，否则为降序
     */
    public ProductQuery orderbyCheckTime(boolean isAsc) {
        orderFields.add(new OrderField("check_time", isAsc ? "ASC" : "DESC"));
        return this;
    }
    /**
     * 设置排序按属性：check_user_id
     *
     * @param isAsc
     *            是否升序，否则为降序
     */
    public ProductQuery orderbyCheckUserId(boolean isAsc) {
        orderFields.add(new OrderField("check_user_id", isAsc ? "ASC" : "DESC"));
        return this;
    }
    /**
     * 设置排序按属性：is_show
     *
     * @param isAsc
     *            是否升序，否则为降序
     */
    public ProductQuery orderbyIsShow(boolean isAsc) {
        orderFields.add(new OrderField("is_show", isAsc ? "ASC" : "DESC"));
        return this;
    }
    /**
     * 设置排序按属性：is_del
     *
     * @param isAsc
     *            是否升序，否则为降序
     */
    public ProductQuery orderbyIsDel(boolean isAsc) {
        orderFields.add(new OrderField("is_del", isAsc ? "ASC" : "DESC"));
        return this;
    }
    /**
     * 设置排序按属性：type_id
     *
     * @param isAsc
     *            是否升序，否则为降序
     */
    public ProductQuery orderbyTypeId(boolean isAsc) {
        orderFields.add(new OrderField("type_id", isAsc ? "ASC" : "DESC"));
        return this;
    }
    /**
     * 设置排序按属性：brand_id
     *
     * @param isAsc
     *            是否升序，否则为降序
     */
    public ProductQuery orderbyBrandId(boolean isAsc) {
        orderFields.add(new OrderField("brand_id", isAsc ? "ASC" : "DESC"));
        return this;
    }
    /**
     * 设置排序按属性：keywords
     *
     * @param isAsc
     *            是否升序，否则为降序
     */
    public ProductQuery orderbyKeywords(boolean isAsc) {
        orderFields.add(new OrderField("keywords", isAsc ? "ASC" : "DESC"));
        return this;
    }
    /**
     * 设置排序按属性：sales
     *
     * @param isAsc
     *            是否升序，否则为降序
     */
    public ProductQuery orderbySales(boolean isAsc) {
        orderFields.add(new OrderField("sales", isAsc ? "ASC" : "DESC"));
        return this;
    }
    /**
     * 设置排序按属性：description
     *
     * @param isAsc
     *            是否升序，否则为降序
     */
    public ProductQuery orderbyDescription(boolean isAsc) {
        orderFields.add(new OrderField("description", isAsc ? "ASC" : "DESC"));
        return this;
    }
    /**
     * 设置排序按属性：package_list
     *
     * @param isAsc
     *            是否升序，否则为降序
     */
    public ProductQuery orderbyPackageList(boolean isAsc) {
        orderFields.add(new OrderField("package_list", isAsc ? "ASC" : "DESC"));
        return this;
    }
    /**
     * 设置排序按属性：feature
     *
     * @param isAsc
     *            是否升序，否则为降序
     */
    public ProductQuery orderbyFeature(boolean isAsc) {
        orderFields.add(new OrderField("feature", isAsc ? "ASC" : "DESC"));
        return this;
    }
    /**
     * 设置排序按属性：color
     *
     * @param isAsc
     *            是否升序，否则为降序
     */
    public ProductQuery orderbyColor(boolean isAsc) {
        orderFields.add(new OrderField("color", isAsc ? "ASC" : "DESC"));
        return this;
    }
    /**
     * 设置排序按属性：size
     *
     * @param isAsc
     *            是否升序，否则为降序
     */
    public ProductQuery orderbySize(boolean isAsc) {
        orderFields.add(new OrderField("size", isAsc ? "ASC" : "DESC"));
        return this;
    }
}