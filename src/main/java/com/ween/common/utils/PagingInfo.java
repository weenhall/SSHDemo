package com.ween.common.utils;

/**
 * Created by wen on 2017/7/13.
 */
public class PagingInfo {
    private int page;
    private int rows;

    public PagingInfo() {
    }

    public PagingInfo(int page, int rows) {
        this.page = page;
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
