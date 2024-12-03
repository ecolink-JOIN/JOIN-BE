package com.join.core.study.controller;

import com.join.core.common.response.ApiResponse;
import com.join.core.study.dto.response.StudyDetailResponse;
import com.join.core.study.service.StudyReadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/study")
public class StudyReadController {

    private final StudyReadService studyReadService;

    @Tag(name = "${swagger.tag.study}")
    @Operation(summary = "스터디 상세 조회 - 인증 필수",
            description = "스터디 상세 조회 - 인증 필수",
            security = {@SecurityRequirement(name = "session-token")})
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{studyId}")
    public ApiResponse<StudyDetailResponse> getStudyDetails(@PathVariable Long studyId) {
        StudyDetailResponse studyDetail = studyReadService.getStudyDetails(studyId);
        return ApiResponse.ok(studyDetail);
    }

}
