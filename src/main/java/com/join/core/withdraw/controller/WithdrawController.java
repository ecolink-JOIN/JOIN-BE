package com.join.core.withdraw.controller;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import com.join.core.withdraw.controller.specification.WithdrawApiSpecification;
import com.join.core.withdraw.domain.WithdrawReadService;
import com.join.core.withdraw.domain.WithdrawService;
import com.join.core.withdraw.dto.request.WithdrawRequest;
import com.join.core.withdraw.dto.response.WithdrawResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/study")
public class WithdrawController implements WithdrawApiSpecification {

    private final WithdrawService withdrawService;
    private final WithdrawReadService withdrawReadService;

    @Override
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{studyToken}/withdraw")
    public ApiResponse<Void> createWithdraw(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable String studyToken,
            @RequestBody WithdrawRequest withdrawRequest
    ) {
        withdrawService.requestWithdraw(principal.getAvatarId(), withdrawRequest, studyToken);
        return ApiResponse.ok();
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{studyToken}/withdraw/{withdrawId}/approve")
    public ApiResponse<Void> approveWithdraw(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable String studyToken,
            @PathVariable Long withdrawId
    ) {
        withdrawService.approveWithdraw(withdrawId, principal.getAvatarId(), studyToken);
        return ApiResponse.ok();
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{studyToken}/withdraw/request")
    public ApiResponse<List<WithdrawResponse>> getWithdrawRequests(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable String studyToken) {
        return ApiResponse.ok(withdrawReadService.getWithdrawRequests(studyToken, principal.getAvatarId()));
    }

}
