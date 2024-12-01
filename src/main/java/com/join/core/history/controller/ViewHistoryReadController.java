package com.join.core.history.controller;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.dto.PageParameterRequest;
import com.join.core.history.dto.response.ViewStudyReadResponse;
import com.join.core.history.service.ViewHistoryReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/views")
public class ViewHistoryReadController {

    private final ViewHistoryReadService viewHistoryReadService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public Page<ViewStudyReadResponse> getViewStudyByAvatarId(
            @AuthenticationPrincipal UserPrincipal principal,
            PageParameterRequest pageParameterRequest
    ) {
        return viewHistoryReadService.getViewStudyByAvatarId(principal.getAvatarId(), pageParameterRequest);
    }
}
