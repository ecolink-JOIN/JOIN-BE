package com.join.core.notice.controller.specification;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import com.join.core.notice.dto.response.NoticeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface NoticeControllerSpecification {

    @Tag(name = "${swagger.tag.user}")
    @Operation(summary = "앱 공지사항 조회 - 인증 선택",
            description = "앱 공지사항 조회 - 인증 선택",
            security = {@SecurityRequirement(name = "session-token")})
    ApiResponse<List<NoticeResponse>> getNotices(@AuthenticationPrincipal UserPrincipal principal);

}
