package com.join.core.job.controller;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import com.join.core.job.controller.specification.BatchJobApiSpecification;
import com.join.core.job.domain.BatchJobService;
import com.join.core.job.dto.request.BatchJobRequest;
import com.join.core.job.dto.request.BatchJobUpdateRequest;
import com.join.core.job.dto.response.BatchJobResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

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
            @RequestBody BatchJobUpdateRequest updateRequest
    ) {
        batchJobService.updateBatchJob(batchJobId, updateRequest, principal.getUserId());
        return ApiResponse.ok();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{studyToken}/batch-jobs")
    public ApiResponse<List<BatchJobResponse>> getBatchJobs(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable String studyToken
    ) {
        return ApiResponse.ok(batchJobService.getBatchJobsByStudy(studyToken, principal.getUserId()));
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{batchJobId}")
    public ApiResponse<Void> deleteBatchJob(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable Long batchJobId
    ) {
        batchJobService.deleteBatchJob(batchJobId, principal.getUserId());
        return ApiResponse.ok();
    }

}