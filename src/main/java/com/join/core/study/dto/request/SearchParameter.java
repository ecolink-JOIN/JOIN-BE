package com.join.core.study.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record SearchParameter(@Schema(example = "검색할 스터디의 제목") String keyword) {
}
