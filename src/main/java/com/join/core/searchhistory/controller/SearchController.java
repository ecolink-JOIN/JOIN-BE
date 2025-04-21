package com.join.core.searchhistory.controller;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.dto.PageParameterRequest;
import com.join.core.common.response.ApiResponse;
import com.join.core.searchhistory.controller.specification.SearchApiSpecification;
import com.join.core.searchhistory.service.SearchHistorySaveService;
import com.join.core.searchhistory.service.dto.SearchContentParameter;
import com.join.core.study.dto.request.SearchParameter;
import com.join.core.study.dto.response.SearchResponse;
import com.join.core.study.service.StudyReadService;
import com.join.core.study.service.dto.SearchCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/study/search")
public class SearchController implements SearchApiSpecification {

    private final SearchHistorySaveService searchHistorySaveService;
    private final StudyReadService studyReadService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ApiResponse<Page<SearchResponse>> searchStudy(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            SearchParameter searchParameter,
            @Valid PageParameterRequest pageParameterRequest
    ) {
        searchHistorySaveService.save(
                new SearchContentParameter(userPrincipal.getAvatarToken(), searchParameter.keyword())
        );
        return ApiResponse.ok(
                studyReadService.search(
                        new SearchCommand(
                                userPrincipal,
                                searchParameter,
                                pageParameterRequest
                        )
                )
        );
    }
}
