package com.lotus.core.bean.product;

import com.lotus.core.web.Constants;

import java.util.Date;

public class Brand {
    private Integer id;

    private String name;

    private String description;

    private String imgUrl;

    private String webSite;

    private Integer sort;

    private Integer isDisplay;

    private Date createTime;

    private Date modifyTime;

    //获取全路径
    public String getAllUrl(){
        return Constants.IMAGE_URL + imgUrl;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite == null ? null : webSite.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(Integer isDisplay) {
        this.isDisplay = isDisplay;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    //页号
    private Integer pageNo = 1;
    //开始行
    private Integer startRow;
    //每页数
    private Integer pageSize = 10;


    public Integer getStartRow() {
        return startRow;
    }
    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }
    public Integer getPageSize() {
        return pageSize;
    }
    public void setPageSize(Integer pageSize) {
        //计算一次开始行
        this.startRow = (pageNo - 1)*pageSize;
        this.pageSize = pageSize;
    }
    public Integer getPageNo() {
        return pageNo;
    }
    public void setPageNo(Integer pageNo) {
        //计算一次开始行
        this.startRow = (pageNo - 1)*pageSize;
        this.pageNo = pageNo;
    }
}