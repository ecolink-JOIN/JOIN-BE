package com.join.core.history.dto.request;

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
