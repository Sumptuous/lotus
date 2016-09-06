package com.lotus.common.page;

import java.io.Serializable;

/**
 * @author wyy
 */
public class SimplePage implements Serializable, PageInfo {
    private static final long serialVersionUID = 1L;
    public static final int DEF_COUNT = 20;
    protected int totalCount = 0;
    protected int pageSize = 20;
    protected int pageNo = 1;

    public static int cpn(Integer pageNo) {
        return pageNo != null && pageNo.intValue() >= 1?pageNo.intValue():1;
    }

    public SimplePage() {
    }

    public SimplePage(int pageNo, int pageSize, int totalCount) {
        this.setTotalCount(totalCount);
        this.setPageSize(pageSize);
        this.setPageNo(pageNo);
        this.adjustPageNo();
    }

    public void adjustPageNo() {
        if(this.pageNo != 1) {
            int tp = this.getTotalPage();
            if(this.pageNo > tp) {
                this.pageNo = tp;
            }

        }
    }

    public int getPageNo() {
        return this.pageNo;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public int getTotalPage() {
        int totalPage = this.totalCount / this.pageSize;
        if(totalPage == 0 || this.totalCount % this.pageSize != 0) {
            ++totalPage;
        }

        return totalPage;
    }

    public boolean isFirstPage() {
        return this.pageNo <= 1;
    }

    public boolean isLastPage() {
        return this.pageNo >= this.getTotalPage();
    }

    public int getNextPage() {
        return this.isLastPage()?this.pageNo:this.pageNo + 1;
    }

    public int getPrePage() {
        return this.isFirstPage()?this.pageNo:this.pageNo - 1;
    }

    public void setTotalCount(int totalCount) {
        if(totalCount < 0) {
            this.totalCount = 0;
        } else {
            this.totalCount = totalCount;
        }

    }

    public void setPageSize(int pageSize) {
        if(pageSize < 1) {
            this.pageSize = 20;
        } else {
            this.pageSize = pageSize;
        }

    }

    public void setPageNo(int pageNo) {
        if(pageNo < 1) {
            this.pageNo = 1;
        } else {
            this.pageNo = pageNo;
        }

    }
}
