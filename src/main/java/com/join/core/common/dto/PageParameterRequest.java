package com.join.core.common.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

@Getter
public class PageParameterRequest implements Pageable {

    @Min(1)
    private final Integer page;
    @Min(1)
    private final Integer size;
    private final Sort sort;

    public PageParameterRequest(Integer page, Integer size, Sort sort) {
        this.page = Objects.requireNonNullElse(page, 1);
        this.size = Objects.requireNonNullElse(size, 20);
        this.sort = Objects.requireNonNullElse(sort, Sort.unsorted());
    }

    @Override
    public int getPageNumber() {
        return page - 1;
    }

    @Override
    public int getPageSize() {
        return size;
    }

    @Override
    public long getOffset() {
        return (long) (page - 1) * size;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return new PageParameterRequest(page + 1, size, sort);
    }

    @Override
    public Pageable previousOrFirst() {
        if (hasPrevious()) {
            return new PageParameterRequest(page - 1, size, sort);
        }
        return first();
    }

    @Override
    public Pageable first() {
        return new PageParameterRequest(1, size, sort);
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return new PageParameterRequest(pageNumber, this.getPageSize(), this.getSort());
    }

    @Override
    public boolean hasPrevious() {
        return page > 1;
    }
}