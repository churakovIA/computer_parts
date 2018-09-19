package com.churakov.test.dao;

import java.util.List;

public class PageHelper<T> {
    private long totalPages;
    private List<T> page;

    public PageHelper(long totalPages, List<T> page) {
        this.totalPages = totalPages;
        this.page = page;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public List<T> getPage() {
        return page;
    }
}
