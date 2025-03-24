package com.join.core.study.controller.specification;

import com.join.core.common.response.ApiResponse;
import com.join.core.study.dto.response.StudyMemberResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

public interface StudyMemberApiSpecification {

    @Tag(name = "${swagger.tag.study}")
    @Operation(summary = "스터디원 조회",
            description = "스터디에 소속된 스터디원 목록을 조회합니다.")
    ApiResponse<Collection<StudyMemberResponse>> getStudyMembers(
            @PathVariable String studyToken
    );
}
