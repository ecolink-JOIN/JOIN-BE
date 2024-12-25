package com.join.core.meeting.controller.specification;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import com.join.core.meeting.dto.request.MeetingAppendRequest;
import com.join.core.meeting.dto.response.MeetingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface MeetingControllerSpecification {

    @Tag(name = "${swagger.tag.meeting}")
    @Operation(summary = "회차 추가 - 인증 필수",
            description = "회차 추가 - 인증 필수",
            security = {@SecurityRequirement(name = "session-token")})
    ApiResponse<Void> append(@AuthenticationPrincipal UserPrincipal principal,
                                    @Valid @RequestBody MeetingAppendRequest request,
                                    @PathVariable String studyToken
    );

    @Tag(name = "${swagger.tag.meeting}")
    @Operation(summary = "회차 리스트 조회",
            description = "회차 리스트 조회",
            security = {@SecurityRequirement(name = "session-token")})
    ApiResponse<List<MeetingResponse>> getMeetingDetails(@PathVariable String studyToken);

    @Tag(name = "${swagger.tag.meeting}")
    @Operation(summary = "회차 삭제 - 인증 필수",
            description = "회차 삭제 - 인증 필수",
            security = {@SecurityRequirement(name = "session-token")})
    ApiResponse<Void> delete(@AuthenticationPrincipal UserPrincipal principal,
                                    @PathVariable String studyToken,
                                    @PathVariable Long meetingId);
}
