package com.join.core.common.dto;

public record PageParameterRequest(Integer page, Integer size) {

    public PageParameterRequest {
        if (page == null) {
            page = 1;
        }
        if (size == null) {
            size = 20;
        }
    }
}
