package com.join.core.common.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;

import java.util.Objects;

@Getter
public class PageParameterRequest {

    @Min(1)
    private final Integer page;
    @Min(1)
    private final Integer size;

    public PageParameterRequest(Integer page, Integer size) {
        this.page = Objects.requireNonNullElse(page, 1);
        this.size = Objects.requireNonNullElse(size, 20);
    }
}