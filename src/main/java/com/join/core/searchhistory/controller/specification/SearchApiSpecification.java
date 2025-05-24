package com.join.core.searchhistory.controller.specification;


import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.dto.PageParameterRequest;
import com.join.core.common.response.ApiResponse;
import com.join.core.study.dto.request.SearchParameter;
import com.join.core.study.dto.response.SearchResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

public interface SearchApiSpecification {

    @Tag(name = "${swagger.tag.search}")
    @Operation(summary = "스터디 검색",
            description = "입력한 키워드가 제목에 포함된 스터디 목록을 반환",
            security = {@SecurityRequirement(name = "session-token")})
    ApiResponse<Page<SearchResponse>> searchStudy(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            SearchParameter searchParameter,
            PageParameterRequest pageParameterRequest
    );
}
