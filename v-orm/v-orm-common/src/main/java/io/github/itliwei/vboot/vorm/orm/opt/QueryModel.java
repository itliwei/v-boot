package io.github.itliwei.vboot.vorm.orm.opt;

import java.util.List;

/**
 * @author : liwei
 * @date 2019/05/11 15:14
 */
public class QueryModel{

    private int pageNumber = 1;

    private int pageSize = 10;

    private List<OrderBy> orderBys;

    public QueryModel(){

    }

    public QueryModel(int pageNumber,int pageSize){
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<OrderBy> getOrderBys() {
        return orderBys;
    }

    public void setOrderBys(List<OrderBy> orderBys) {
        this.orderBys = orderBys;
    }
}