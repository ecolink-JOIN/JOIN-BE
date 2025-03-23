package com.join.core.enrollment.controller.specification;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import com.join.core.enrollment.dto.request.DelegateLeaderRequest;
import com.join.core.enrollment.dto.request.ForcedOutRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface EnrollmentControllerSpecification {

    @Tag(name = "${swagger.tag.enrollment}")
    @Operation(summary = "스터디장 위임 - 인증 필수",
            description = "스터디장 위임 - 팀장만 사용 가능 / target token에 팀장의 토큰 입력시 예외 발생",
            security = {@SecurityRequirement(name = "session-token")})
    ApiResponse<Void> delegateStudyLeader(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable String studyToken,
            @RequestBody DelegateLeaderRequest request
    );

    @Tag(name = "${swagger.tag.enrollment}")
    @Operation(summary = "팀원 강제 탈퇴 - 인증 필수",
            description = "강제 탈퇴 - 팀장만 사용 가능",
            security = {@SecurityRequirement(name = "session-token")})
    ApiResponse<Void> forcedOut(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable String studyToken,
            @RequestBody ForcedOutRequest request
    );
}
