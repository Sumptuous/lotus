package com.lotus.common.page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wyy
 */
public class Pagination extends SimplePage {
    private List<?> list;
    private List<String> pageView;

    public Pagination() {
    }

    public Pagination(int pageNo, int pageSize, int totalCount) {
        super(pageNo, pageSize, totalCount);
    }

    public Pagination(int pageNo, int pageSize, int totalCount, List<?> list) {
        super(pageNo, pageSize, totalCount);
        this.list = list;
    }

    public int getFirstResult() {
        return (this.pageNo - 1) * this.pageSize;
    }

    public List<?> getList() {
        return this.list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public List<String> getPageView() {
        return this.pageView;
    }

    public void setPageView(List<String> pageView) {
        this.pageView = pageView;
    }

    public void pageView(String url, String params) {
        this.pageView = new ArrayList();
        if(this.pageNo != 1) {
            this.pageView.add("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href=\'" + url + "?" + params + "&pageNo=1\'\"><font size=2>首页</font></a>");
            this.pageView.add("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href=\'" + url + "?" + params + "&pageNo=" + (this.pageNo - 1) + "\'\"><font size=2>上一页</font></a>");
        } else {
            this.pageView.add("<font size=2>首页</font>");
            this.pageView.add("<font size=2>上一页</font>");
        }

        int i;
        if(this.getTotalPage() <= 10) {
            for(i = 0; i < this.getTotalPage(); ++i) {
                if(i + 1 == this.pageNo) {
                    this.pageView.add("<strong>" + this.pageNo + "</strong>");
                    ++i;
                    if(this.pageNo == this.getTotalPage()) {
                        break;
                    }
                }

                this.pageView.add("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href=\'" + url + "?" + params + "&pageNo=" + (i + 1) + "\'\">" + (i + 1) + "</a>");
            }
        } else if(this.getTotalPage() <= 20) {
            boolean var7 = false;
            boolean r = false;
            int var8;
            if(this.pageNo < 5) {
                i = this.pageNo - 1;
                var8 = 10 - i - 1;
            } else if(this.getTotalPage() - this.pageNo < 5) {
                var8 = this.getTotalPage() - this.pageNo;
                i = 9 - var8;
            } else {
                i = 4;
                r = true;
            }

            int tmp = this.pageNo - i;

            for(int i1 = tmp; i1 < tmp + 10; ++i1) {
                if(i1 == this.pageNo) {
                    this.pageView.add("<strong>" + this.pageNo + "</strong>");
                    ++i1;
                    if(this.pageNo == this.getTotalPage()) {
                        break;
                    }
                }

                this.pageView.add("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href=\'" + url + "?" + params + "&pageNo=" + i1 + "\'\">" + i1 + "</a>");
            }
        } else if(this.pageNo < 7) {
            for(i = 0; i < 8; ++i) {
                if(i + 1 == this.pageNo) {
                    this.pageView.add("<strong>" + this.pageNo + "</strong>");
                    ++i;
                }

                this.pageView.add("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href=\'" + url + "?" + params + "&pageNo=" + (i + 1) + "\'\">" + (i + 1) + "</a>");
            }

            this.pageView.add("...");
            this.pageView.add("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href=\'" + url + "?" + params + "&pageNo=" + (this.getTotalPage() - 1) + "\'\">" + (this.getTotalPage() - 1) + "</a>");
            this.pageView.add("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href=\'" + url + "?" + params + "&pageNo=" + this.getTotalPage() + "\'\">" + this.getTotalPage() + "</a>");
        } else if(this.pageNo > this.getTotalPage() - 6) {
            this.pageView.add("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href=\'" + url + "?" + params + "&pageNo=" + 1 + "\'\">" + 1 + "</a>");
            this.pageView.add("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href=\'" + url + "?" + params + "&pageNo=" + 2 + "\'\">" + 2 + "</a>");
            this.pageView.add("...");

            for(i = this.getTotalPage() - 8; i < this.getTotalPage(); ++i) {
                if(i + 1 == this.pageNo) {
                    this.pageView.add("<strong>" + this.pageNo + "</strong>");
                    ++i;
                    if(this.pageNo == this.getTotalPage()) {
                        break;
                    }
                }

                this.pageView.add("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href=\'" + url + "?" + params + "&pageNo=" + (i + 1) + "\'\">" + (i + 1) + "</a>");
            }
        } else {
            this.pageView.add("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href=\'" + url + "?" + params + "&pageNo=" + 1 + "\'\">" + 1 + "</a>");
            this.pageView.add("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href=\'" + url + "?" + params + "&pageNo=" + 2 + "\'\">" + 2 + "</a>");
            this.pageView.add("...");
            this.pageView.add("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href=\'" + url + "?" + params + "&pageNo=" + (this.pageNo - 2) + "\'\">" + (this.pageNo - 2) + "</a>");
            this.pageView.add("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href=\'" + url + "?" + params + "&pageNo=" + (this.pageNo - 1) + "\'\">" + (this.pageNo - 1) + "</a>");
            this.pageView.add("<strong>" + this.pageNo + "</strong>");
            this.pageView.add("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href=\'" + url + "?" + params + "&pageNo=" + (this.pageNo + 1) + "\'\">" + (this.pageNo + 1) + "</a>");
            this.pageView.add("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href=\'" + url + "?" + params + "&pageNo=" + (this.pageNo + 2) + "\'\">" + (this.pageNo + 2) + "</a>");
            this.pageView.add("...");
            this.pageView.add("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href=\'" + url + "?" + params + "&pageNo=" + (this.getTotalPage() - 1) + "\'\">" + (this.getTotalPage() - 1) + "</a>");
            this.pageView.add("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href=\'" + url + "?" + params + "&pageNo=" + this.getTotalPage() + "\'\">" + this.getTotalPage() + "</a>");
        }

        if(this.pageNo != this.getTotalPage()) {
            this.pageView.add("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href=\'" + url + "?" + params + "&pageNo=" + (this.pageNo + 1) + "\'\"><font size=2>下一页</font></a>");
            this.pageView.add("<a href=\"javascript:void(0);\" onclick=\"javascript:window.location.href=\'" + url + "?" + params + "&pageNo=" + this.getTotalPage() + "\'\"><font size=2>尾页</font></a>");
        } else {
            this.pageView.add("<font size=2>下一页</font>");
            this.pageView.add("<font size=2>尾页</font>");
        }

        this.pageView.add("共<var>" + this.getTotalPage() + "</var>页 到第<input type=\'text\' id=\'PAGENO\'  size=\'3\' />页 <input type=\'button\' id=\'skip\' class=\'hand btn60x20\' value=\'确定\' onclick=\"javascript:window.location.href = \'" + url + "?" + params + "&pageNo=\' + $(\'#PAGENO\').val() \"/>");
    }
}
