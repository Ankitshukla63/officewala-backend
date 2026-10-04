package com.officewala.dto.friday;

import java.util.List;

public class PagedFridayResponse {
    private List<FridayPostDTO> items;
    private long total;
    private int page;
    private int pageSize;
    private int pageCount;

    public PagedFridayResponse() {}

    public PagedFridayResponse(List<FridayPostDTO> items, long total, int page, int pageSize, int pageCount) {
        this.items = items;
        this.total = total;
        this.page = page;
        this.pageSize = pageSize;
        this.pageCount = pageCount;
    }

    public List<FridayPostDTO> getItems() { return items; }
    public void setItems(List<FridayPostDTO> items) { this.items = items; }

    public long getTotal() { return total; }
    public void setTotal(long total) { this.total = total; }

    public int getPage() { return page; }
    public void setPage(int page) { this.page = page; }

    public int getPageSize() { return pageSize; }
    public void setPageSize(int pageSize) { this.pageSize = pageSize; }

    public int getPageCount() { return pageCount; }
    public void setPageCount(int pageCount) { this.pageCount = pageCount; }
}
