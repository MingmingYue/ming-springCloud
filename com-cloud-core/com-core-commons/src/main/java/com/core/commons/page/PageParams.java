package com.core.commons.page;

import com.core.commons.constants.GlobalConstant;

/**
 * @author xiaoMing
 * Create on 2020-08-13.
 */
public class PageParams {

    /**
     * 当前页码
     */
    private Integer currentPage = 1;

    /**
     * 每页多少条 limit
     */
    private Integer pageSize = GlobalConstant.PAGE_NUM;

    public Integer getCurrentPage() {
        currentPage = (null == currentPage || currentPage <= 0) ? 1 : currentPage;
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return (null == pageSize || pageSize < 0) ? GlobalConstant.PAGE_NUM : pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
