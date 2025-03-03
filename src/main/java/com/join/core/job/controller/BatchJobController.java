package com.join.core.job.controller;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import com.join.core.job.controller.specification.BatchJobApiSpecification;
import com.join.core.job.domain.BatchJobService;
import com.join.core.job.dto.request.BatchJobRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/batch-job")
public class BatchJobController implements BatchJobApiSpecification {

    private final BatchJobService batchJobService;

    @Override
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ApiResponse<Void> addBatchJob(
            @AuthenticationPrincipal UserPrincipal principal,
            @RequestBody BatchJobRequest request
    ) {
        batchJobService.createBatchJob(request, principal.getUserId());
        return ApiResponse.ok();
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{batchJobId}")
    public ApiResponse<Void> updateBatchJob(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable Long batchJobId,
            @RequestBody BatchJobRequest request
    ) {
        batchJobService.updateBatchJob(batchJobId, request, principal.getUserId());
        return ApiResponse.ok();
    }

}