package com.join.core.report.controller;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import com.join.core.report.controller.specification.ReportApiSpecification;
import com.join.core.report.domain.ReportService;
import com.join.core.report.dto.request.ReportRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/report")
public class ReportController implements ReportApiSpecification {

    private final ReportService reportService;

    @Override
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ApiResponse<Void> createReport(
            @AuthenticationPrincipal UserPrincipal principal,
            @RequestBody ReportRequest reportRequest
    ) {
        reportService.createReport(principal.getAvatarId(), reportRequest.getStudyToken(), reportRequest);
        return ApiResponse.ok();
    }

}
