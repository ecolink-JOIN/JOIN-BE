package com.join.core.enrollment.controller.specification;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import com.join.core.enrollment.dto.response.ProofAndAttendanceStatusResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;

public interface EnrollmentReaderControllerSpecification {

    @Tag(name = "${swagger.tag.enrollment-detail}")
    @Operation(summary = "스터디 참여자 별 출석, 인증 현황 조회 - 인증 필수",
            description = "스터디 참여자 별 출석, 인증 현황 조회 - 팀장만 사용 가능",
            security = {@SecurityRequirement(name = "session-token")})
    ApiResponse<ProofAndAttendanceStatusResponse> getParticipationDetails(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable String studyToken,
            @PathVariable String targetToken
    );
}
