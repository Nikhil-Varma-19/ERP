package com.example.erp.backend.dtos;

import lombok.Data;

import java.util.List;

@Data
public class PageResponseDto<T> {
    private List<T> results;
    private long totalRecords;
    private int currentPage;
    private int totalPages;
}

