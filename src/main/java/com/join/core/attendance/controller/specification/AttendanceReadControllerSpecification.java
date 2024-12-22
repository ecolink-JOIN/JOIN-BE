package com.join.core.attendance.controller.specification;

import com.join.core.attendance.dto.response.CheckAttendanceResponse;
import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;

public interface AttendanceReadControllerSpecification {

    @Tag(name = "${swagger.tag.attendance}")
    @Operation(summary = "출석 조회 - 인증 필수",
            description = "출석 내역 조회",
            security = {@SecurityRequirement(name = "session-token")})
    ApiResponse<CheckAttendanceResponse> checkAttendance(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable String studyToken,
            @PathVariable Integer meetingNo
    );
}
