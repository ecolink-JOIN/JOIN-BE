package com.join.core.study.controller;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import com.join.core.study.dto.request.StudyOrderByPopularityParameter;
import com.join.core.study.dto.request.StudyOrderByPopularityRequest;
import com.join.core.study.dto.response.PopularStudyReadResponse;
import com.join.core.study.dto.response.StudyDetailResponse;
import com.join.core.study.service.StudyReadService;
import com.join.core.study.service.dto.StudyOrderByPopularityCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping
    public ApiResponse<List<PopularStudyReadResponse>> getStudiesOrderByPopularity(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            StudyOrderByPopularityParameter studyOrderByPopularityParameter,
            @RequestBody StudyOrderByPopularityRequest studyOrderByPopularityRequest
    ) {
        return ApiResponse.ok(
                studyReadService.getStudiesOrderByPopularity(
                    new StudyOrderByPopularityCommand(
                            userPrincipal,
                            studyOrderByPopularityParameter.category(),
                            studyOrderByPopularityParameter.form(),
                            studyOrderByPopularityRequest.now()
                    )
                )
        );
    }
}
