package com.cssl.pojo;

import java.util.List;

public class Page {
    //总页数  = 总页数 % 每页大小==0 ？ 总页数 / 每页大小 ： 总页数 /每页大小 + 1 ；
    private int totalPage;
    //总记录数
    private int totalCount;
    //每页大小
    private int pageSize;
    //第几页
    private int pageIndex;
    //每页容量
    private List<House> list;

    private House house;

    public Page() {
    }

    public Page(int totalPage, int totalCount, int pageSize, int pageIndex, List<House> list) {
        this.totalPage = totalPage;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.list = list;
    }

    public Page(int totalPage, int totalCount, int pageSize, int pageIndex, List<House> list, House house) {
        this.totalPage = totalPage;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.list = list;
        this.house = house;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public List<House> getList() {
        return list;
    }

    public void setList(List<House> list) {
        this.list = list;
    }
}
