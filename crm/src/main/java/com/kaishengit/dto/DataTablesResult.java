package com.kaishengit.dto;

import java.util.List;

public class DataTablesResult<T> {

    private String draw;
    private List<T> data;
    private Long recordsTotal;
    private Long recordsFiltered;

    public DataTablesResult(String draw, List<T> data, Long recordsTotal, Long recordsFiltered) {
        this.draw = draw;
        this.data = data;
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
    }

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }
}
