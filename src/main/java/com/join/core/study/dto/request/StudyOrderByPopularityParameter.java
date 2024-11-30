package com.join.core.study.dto.request;

import com.join.core.study.constant.StudyForm;

public record StudyOrderByPopularityParameter(
        String category,
        StudyForm form,
        Integer page,
        Integer size
) {

    public StudyOrderByPopularityParameter {
        if (page == null || page < 1) {
            page = 1;
        }
        if (size == null || size < 1) {
            size = 20;
        }
    }
}
