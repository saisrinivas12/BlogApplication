package com.example.BlogApplication.entities;

import com.example.BlogApplication.payload.PostDto;

import java.util.List;

public class PostResponse {

    private int pageNo;

    private int pageSize;

    private List<PostDto> content;

    private int totalPages;

    private long totalElements;

    private boolean isLastPage;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<PostDto> getContent() {
        return content;
    }

    public void setContent(List<PostDto> content) {
        this.content = content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }

    @Override
    public String toString() {
        return "PostResponse{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", content=" + content +
                ", totalPages=" + totalPages +
                ", totalElements=" + totalElements +
                ", isLastPage=" + isLastPage +
                '}';
    }
}
