package com.join.core.withdraw.controller;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import com.join.core.withdraw.controller.specification.WithdrawApiSpecification;
import com.join.core.withdraw.domain.WithdrawService;
import com.join.core.withdraw.dto.request.WithdrawRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/study")
public class WithdrawController implements WithdrawApiSpecification {

    private final WithdrawService withdrawService;

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

}
