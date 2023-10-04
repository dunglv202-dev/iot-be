package edu.ptit.iot.model;

import lombok.Getter;

@Getter
public class Pagination {
    private int page = 0;
    private int size = 20;

    public void setPage(int page) {
        if (page < 0) throw new RuntimeException("Invalid page number");
        this.page = page;
    }

    public void setSize(int size) {
        if (size > 50) throw new RuntimeException("Page size must be less or equal to than 50");
        this.size = size;
    }
}
