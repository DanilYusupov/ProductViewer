package com.gdc.apps.model;

import java.util.List;

/**
 * Describes page content
 */
public class Page<T> {
    private List<T> items;
    private int totalCount;
    private int page;
    private int pageSize;

    public Page(List<T> items, int totalCount, int page, int pageSize) {
        this.items = items;
        this.totalCount = totalCount;
        this.page = page;
        this.pageSize = pageSize;
    }

    public List<T> getItems() {
        return items;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }
}
