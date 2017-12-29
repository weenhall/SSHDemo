package com.ween.common.utils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wen on 2017/5/17.
 */
public class Pager<T> implements Serializable {
    private int pageSize;//每页记录数
    private int currentPage;//当前第几页
    private int totalRecord;//总记录数
    private int totalPage;//总页数
    private List<T> dataList;//某页数据

    public Pager(int pageNum,int pageSize,List<T> sourceList){
        if(sourceList.isEmpty()){
            return;
        }
        this.totalRecord=sourceList.size();
        this.pageSize=pageSize;
        this.totalPage=this.totalRecord/this.pageSize;
        if(this.totalRecord % this.pageSize !=0){
            this.totalPage = this.totalPage + 1;
        }
        this.currentPage = this.totalPage < pageNum ?  this.totalPage : pageNum;
        int fromIndex	= this.pageSize * (this.currentPage -1);
        int toIndex  = this.pageSize * this.currentPage > this.totalRecord ? this.totalRecord : this.pageSize * this.currentPage;
        this.dataList = sourceList.subList(fromIndex, toIndex);
    }

    public Pager(){

    }
    public Pager(int pageSize, int currentPage, int totalRecord, int totalPage,
                 List<T> dataList) {
        super();
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalRecord = totalRecord;
        this.totalPage = totalPage;
        this.dataList = dataList;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
