package com.service.common.utils;

import java.util.ArrayList;
import java.util.List;

import com.core.commons.page.PageBean;
import com.core.commons.page.PageParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * 分页工具 注意分页是从0开始的
 *
 * @author xiaoMing
 * Create on 2020-08-13.
 */
public class PageUtils {

    public static <T> PageBean<T> of(List<T> resultList, Long total, Integer currentPage,
                                     Integer pageSize) {
        PageBean<T> pageData = new PageBean<T>();
        pageData.setCurrentPage(currentPage + 1);
        pageData.setPageSize(pageSize);
        pageData.setTotal(total);
        List<T> newList = new ArrayList<T>();
        if (null != resultList && resultList.size() > 0) newList.addAll(resultList);
        pageData.setList(newList);
        return pageData;
    }

    public static <T> PageBean<T> of(Page<T> pageList) {
        PageBean<T> pageData = new PageBean<>();
        pageData.setCurrentPage(pageList.getNumber() + 1);
        pageData.setPageSize(pageList.getSize());
        pageData.setTotal(pageList.getTotalElements());
        List<T> newList = new ArrayList<>();
        List<T> contentList = pageList.getContent();
        if (contentList.size() > 0) {
            newList.addAll(pageList.getContent());
        }
        pageData.setList(newList);
        return pageData;
    }

    public static PageRequest of(PageParams pageParams, Sort sort) {
        return PageRequest.of(pageParams.getCurrentPage() - 1, pageParams.getPageSize(), sort);
    }

    public static PageRequest of(PageParams pageParams) {
        return PageRequest.of(pageParams.getCurrentPage() - 1, pageParams.getPageSize());
    }

}
