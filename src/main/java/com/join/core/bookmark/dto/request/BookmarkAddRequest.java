package com.join.core.bookmark.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record BookmarkAddRequest(
        @Schema(description = "스터디 ID", example = "1")
        Long studyId
) {

}
