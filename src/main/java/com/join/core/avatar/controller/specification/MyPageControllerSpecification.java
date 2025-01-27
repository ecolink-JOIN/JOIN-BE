package com.join.core.avatar.controller.specification;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.avatar.dto.response.MyPageInfoResponse;
import com.join.core.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

public interface MyPageControllerSpecification {

    @Tag(name = "${swagger.tag.user}")
    @Operation(summary = "마이페이지 조회 - 인증 필수",
            description = "마이페이지 조회",
            security = {@SecurityRequirement(name = "session-token")})
    ApiResponse<MyPageInfoResponse> getMyPageInfo(
            @AuthenticationPrincipal UserPrincipal principal);
}
