package com.lotus.common.page;

import com.lotus.common.base.context.BaseModel;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author wyy
 */
public class SortLimit extends BaseModel implements Cloneable {

    private String sort;
    private String order;
    private Integer offset = Integer.valueOf(0);
    private Integer max = Integer.valueOf(10);
    private Integer pageNum;
    private Integer pageSize;

    public SortLimit() {
        this.pageSize = this.max;
    }

    public static SortLimit empty() {
        return new SortLimit();
    }

    public String getSort() {
        return this.sort != null && this.sort.matches("^[a-zA-Z\\d_\\.]+$")?this.sort:null;
    }

    public void setSort(String sort) {
        this.sort = StringUtils.left(sort, 64);
    }

    public String getOrder() {
        return this.order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getOffset() {
        if(this.offset == null && this.pageNum == null) {
            this.pageNum = Integer.valueOf(1);
        }

        if(this.pageNum != null && this.pageSize != null) {
            this.offset = Integer.valueOf(this.pageNum.intValue() * this.pageSize.intValue() - this.pageSize.intValue());
        }

        return this.offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getMax() {
        return (Integer) ObjectUtils.defaultIfNull(this.pageSize, this.max);
    }

    public void setMax(Integer max) {
        this.max = max;
        this.pageSize = max;
    }

    public Integer getPageNum() {
        return this.pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public SortLimit clone() {
        SortLimit clone = null;

        try {
            clone = (SortLimit)super.clone();
        } catch (CloneNotSupportedException var3) {
            var3.printStackTrace();
        }

        return clone;
    }
}
