package com.join.core.study.controller;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.dto.PageParameterRequest;
import com.join.core.common.response.ApiResponse;
import com.join.core.study.controller.specification.StudyReadApiSpecification;
import com.join.core.study.dto.request.CustomStudyParameter;
import com.join.core.study.dto.request.SearchParameter;
import com.join.core.study.dto.request.StudyOrderByPopularityParameter;
import com.join.core.study.dto.response.CustomStudyResponse;
import com.join.core.study.dto.response.PopularStudyReadResponse;
import com.join.core.study.dto.response.SearchResponse;
import com.join.core.study.dto.response.StudyDetailResponse;
import com.join.core.study.service.StudyReadService;
import com.join.core.study.service.dto.CustomStudyCommand;
import com.join.core.study.service.dto.SearchCommand;
import com.join.core.study.service.dto.StudyOrderByPopularityCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/study")
public class StudyReadController implements StudyReadApiSpecification {

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

    @GetMapping("/popular")
    public ApiResponse<Page<PopularStudyReadResponse>> getStudiesOrderByPopularity(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            StudyOrderByPopularityParameter studyOrderByPopularityParameter,
            @Valid PageParameterRequest pageParameterRequest
    ) {
        return ApiResponse.ok(
                studyReadService.getStudiesOrderByPopularity(
                    new StudyOrderByPopularityCommand(
                            userPrincipal,
                            studyOrderByPopularityParameter.category(),
                            studyOrderByPopularityParameter.form(),
                            studyOrderByPopularityParameter.now(),
                            pageParameterRequest.getPage(),
                            pageParameterRequest.getSize()
                    )
                )
        );
    }

    @GetMapping("/recommendation")
    public ApiResponse<Collection<CustomStudyResponse>> recommendStudies(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            CustomStudyParameter customStudyParameter
            ) {
        return ApiResponse.ok(studyReadService.recommendStudies(
                new CustomStudyCommand(
                        userPrincipal,
                        customStudyParameter.category(),
                        customStudyParameter.form(),
                        customStudyParameter.possibleDays(),
                        customStudyParameter.timeZone(),
                        customStudyParameter.minParticipationCount(),
                        customStudyParameter.maxParticipationCount(),
                        customStudyParameter.province(),
                        customStudyParameter.city()
                )
        ));
    }

    @GetMapping("/search")
    public ApiResponse<Page<SearchResponse>> searchStudy(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            SearchParameter searchParameter,
            @Valid PageParameterRequest pageParameterRequest
    ) {
        return ApiResponse.ok(
                studyReadService.search(
                        new SearchCommand(
                                userPrincipal,
                                searchParameter.keyword(),
                                pageParameterRequest.getPage(),
                                pageParameterRequest.getSize()
                        )
                )
        );
    }
}
