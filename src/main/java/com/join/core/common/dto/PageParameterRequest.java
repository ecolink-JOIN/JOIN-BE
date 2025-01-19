package com.join.core.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

@Getter
public class PageParameterRequest implements Pageable {

    @Min(1)
    private final Integer pageNumber;
    @Min(1)
    private final Integer pageSize;

    @Schema(hidden = true)
    private final Sort sort;
    @Schema(hidden = true)
    private int offset;
    @Schema(hidden = true)
    private boolean paged = true;
    @Schema(hidden = true)
    private boolean unpaged = false;

    public PageParameterRequest(Integer pageNumber, Integer pageSize, Sort sort) {
        this.pageNumber = Objects.requireNonNullElse(pageNumber, 1);
        this.pageSize = Objects.requireNonNullElse(pageSize, 20);
        this.sort = Objects.requireNonNullElse(sort, Sort.unsorted());
    }

    @Override
    public int getPageNumber() {
        return pageNumber - 1;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public long getOffset() {
        return (long) (pageNumber - 1) * pageSize;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return new PageParameterRequest(pageNumber + 1, pageSize, sort);
    }

    @Override
    public Pageable previousOrFirst() {
        if (hasPrevious()) {
            return new PageParameterRequest(pageNumber - 1, pageSize, sort);
        }
        return first();
    }

    @Override
    public Pageable first() {
        return new PageParameterRequest(1, pageSize, sort);
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return new PageParameterRequest(pageNumber, this.getPageSize(), this.getSort());
    }

    @Override
    public boolean hasPrevious() {
        return pageNumber > 1;
    }
}