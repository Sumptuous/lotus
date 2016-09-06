package com.lotus.common.page;

/**
 * @author wyy
 */
public interface PageInfo {
    int getTotalCount();

    int getTotalPage();

    int getPageSize();

    int getPageNo();

    boolean isFirstPage();

    boolean isLastPage();

    int getNextPage();

    int getPrePage();
}
