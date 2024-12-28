package com.join.core.history.controller.specification;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.dto.PageParameterRequest;
import com.join.core.history.dto.response.ViewStudyReadResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

public interface ViewHistoryReadApiSpecification {

    @Tag(name = "${swagger.tag.view}")
    @Operation(summary = "최근 조회한 스터디 목록 조회 - 인증 필수",
            description = "최근 조회한 스터디 조회",
            security = {@SecurityRequirement(name = "session-token")})
    Page<ViewStudyReadResponse> getViewStudyByAvatarId(
            @AuthenticationPrincipal UserPrincipal principal,
            PageParameterRequest pageParameterRequest
    );
}
