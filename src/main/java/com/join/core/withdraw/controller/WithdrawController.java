package com.join.core.withdraw.controller;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import com.join.core.avatar.domain.AvatarReader;
import com.join.core.withdraw.controller.specification.WithdrawApiSpecification;
import com.join.core.withdraw.domain.WithdrawService;
import com.join.core.withdraw.dto.request.WithdrawRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/withdraw")
public class WithdrawController implements WithdrawApiSpecification {

    private final WithdrawService withdrawService;

    @Override
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ApiResponse<Void> createWithdraw(
            @AuthenticationPrincipal UserPrincipal principal,
            @RequestBody WithdrawRequest withdrawRequest
    ) {
        withdrawService.requestWithdraw(principal.getAvatarId(), withdrawRequest, withdrawRequest.getStudyToken());
        return ApiResponse.ok();
    }

}
