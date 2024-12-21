package com.join.core.meeting.controller;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import com.join.core.meeting.dto.request.MeetingAppendRequest;
import com.join.core.meeting.service.MeetingAppendService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/meetings")
public class MeetingController {

    private final MeetingAppendService meetingAppendService;

    @Tag(name = "${swagger.tag.meeting}")
    @Operation(summary = "회차 추가 - 인증 필수",
            description = "회차 추가 - 인증 필수",
            security = {@SecurityRequirement(name = "session-token")})
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ApiResponse<Void> append(@AuthenticationPrincipal UserPrincipal principal,
                                   @Valid @RequestBody MeetingAppendRequest request) {
        Long avatarId = principal.getAvatarId();
        meetingAppendService.appendMeetingToStudy(avatarId, request);
        return ApiResponse.ok();
    }

}
