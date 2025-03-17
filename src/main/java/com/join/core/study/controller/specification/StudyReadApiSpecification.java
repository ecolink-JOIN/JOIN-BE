package com.join.core.study.controller.specification;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.dto.PageParameterRequest;
import com.join.core.common.response.ApiResponse;
import com.join.core.study.dto.request.CustomStudyParameter;
import com.join.core.study.dto.request.SearchParameter;
import com.join.core.study.dto.request.StudyOrderByPopularityParameter;
import com.join.core.study.dto.response.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

public interface StudyReadApiSpecification {

    @Tag(name = "${swagger.tag.study}")
    @Operation(summary = "인기 스터디 조회",
            description = "인기순 스터디 조회")
    ApiResponse<Page<PopularStudyReadResponse>> getStudiesOrderByPopularity(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            StudyOrderByPopularityParameter studyOrderByPopularityParameter,
            PageParameterRequest pageParameterRequest
    );

    @Tag(name = "${swagger.tag.study}")
    @Operation(summary = "맞춤 스터디 조회",
            description = "맞춤 스터디 조회")
    ApiResponse<Collection<CustomStudyResponse>> recommendStudies(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            CustomStudyParameter customStudyParameter
    );

    @Tag(name = "${swagger.tag.study}")
    @Operation(summary = "스터디 검색",
            description = "입력한 키워드가 제목에 포함된 스터디 목록을 반환")
    ApiResponse<Page<SearchResponse>> searchStudy(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            SearchParameter searchParameter,
            PageParameterRequest pageParameterRequest
    );

    @Tag(name = "${swagger.tag.block}")
    @Operation(summary = "차단할 사용자 목록 - 인증 필수",
            description = "스터디별 차단 가능한 사용자 목록",
            security = {@SecurityRequirement(name = "session-token")})
    ApiResponse<Collection<StudyListForBlockResponse>> getStudiesForBlock(@AuthenticationPrincipal UserPrincipal userPrincipal);

    @Tag(name = "${swagger.tag.study}")
    @Operation(summary = "스터디 현황 조회",
            description = "스터디 현황 조회")
    ApiResponse<StudyStatusResponse> getStudyStatus(@PathVariable String studyToken);
}
