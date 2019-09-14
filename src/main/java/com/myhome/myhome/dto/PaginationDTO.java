package com.myhome.myhome.dto;

import java.util.ArrayList;
import java.util.List;

public class PaginationDTO<T> {
    private List<T> data;
    private Boolean showPrevious;
    private Boolean showFirstPage;
    private Boolean showNext;
    private Boolean showEndPage;

    private Integer page;
    private List<Integer> pages=  new ArrayList<>();
    private Integer totalPage;


    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Boolean getShowPrevious() {
        return showPrevious;
    }

    public void setShowPrevious(Boolean showPrevious) {
        this.showPrevious = showPrevious;
    }

    public Boolean getShowFirstPage() {
        return showFirstPage;
    }

    public void setShowFirstPage(Boolean showFirstPage) {
        this.showFirstPage = showFirstPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Boolean getShowNext() {
        return showNext;
    }

    public void setShowNext(Boolean showNext) {
        this.showNext = showNext;
    }

    public Boolean getShowEndPage() {
        return showEndPage;
    }

    public void setShowEndPage(Boolean showEndPage) {
        this.showEndPage = showEndPage;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Integer> getPages() {
        return pages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }

    public void setPagination(Integer totalPage, Integer page) {


        this.totalPage=totalPage;
        this.page=page;

        pages.add(page);
        for (int i=1;i<=3;i++){
            if (page - i>0){
                pages.add(0,page-i);
            }
            if (page + i<=totalPage){
                pages.add(page + i);
            }
        }

        //第一页是否显示上一页的按钮
        if (page==1){
            showPrevious=false;
        }else{
            showPrevious=true;
        }
        //最后一页是否显示下一页的按钮
        if (page==totalPage){
            showNext=false;
        }else{
            showNext=true;
        }
        //是否展示尾页的按钮

        if (pages.contains(totalPage)){
            showEndPage=false;
        }else {
            showEndPage=true;
        }
        //是否展示首页的按钮
        if (pages.contains(1)){
            showFirstPage=false;
        }else {
            showFirstPage=true;
        }

    }
}
