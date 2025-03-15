package com.join.core.attendance.controller.specification;

import com.join.core.attendance.dto.CreateAttendanceRequest;
import com.join.core.attendance.dto.UpdateAttendanceRequest;
import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface AttendanceControllerSpecification {

    @Tag(name = "${swagger.tag.attendance}")
    @Operation(summary = "출석 - 인증 필수",
            description = "출석 진행",
            security = {@SecurityRequirement(name = "session-token")})
    ApiResponse<Void> createAttendance(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable String studyToken,
            @PathVariable Integer meetingNo,
            @RequestBody CreateAttendanceRequest request
    );

    @Tag(name = "${swagger.tag.attendance}")
    @Operation(summary = "출석 수정 - 인증 필수",
            description = "출석 수정 - 리더만 수정 가증",
            security = {@SecurityRequirement(name = "session-token")})
    ApiResponse<Void> updateAttendance(
            @AuthenticationPrincipal UserPrincipal principal,
            @PathVariable String studyToken,
            @PathVariable Integer meetingNo,
            @PathVariable Long attendanceId,
            @RequestBody UpdateAttendanceRequest request
    );
}
