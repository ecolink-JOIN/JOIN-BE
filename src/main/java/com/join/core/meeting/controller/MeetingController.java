package com.join.core.meeting.controller;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import com.join.core.meeting.dto.request.MeetingAppendRequest;
import com.join.core.meeting.dto.response.MeetingResponse;
import com.join.core.meeting.service.MeetingAppendService;
import com.join.core.meeting.service.MeetingDeleteService;
import com.join.core.meeting.service.MeetingReadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/study/{studyToken}/meetings")
public class MeetingController {

    private final MeetingAppendService meetingAppendService;
    private final MeetingReadService meetingReadService;
    private final MeetingDeleteService meetingDeleteService;

    @Tag(name = "${swagger.tag.meeting}")
    @Operation(summary = "회차 추가 - 인증 필수",
            description = "회차 추가 - 인증 필수",
            security = {@SecurityRequirement(name = "session-token")})
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ApiResponse<Void> append(@AuthenticationPrincipal UserPrincipal principal,
                                    @Valid @RequestBody MeetingAppendRequest request,
                                    @PathVariable String studyToken
                                    ) {
        Long avatarId = principal.getAvatarId();
        meetingAppendService.appendMeetingToStudy(avatarId, studyToken, request);
        return ApiResponse.ok();
    }

    @Tag(name = "${swagger.tag.meeting}")
    @Operation(summary = "회차 리스트 조회",
            description = "회차 리스트 조회",
            security = {@SecurityRequirement(name = "session-token")})
    @GetMapping
    public ApiResponse<List<MeetingResponse>> getMeetingDetails(@PathVariable String studyToken) {
        return ApiResponse.ok(meetingReadService.getMeetings(studyToken));
    }

    @Tag(name = "${swagger.tag.meeting}")
    @Operation(summary = "회차 삭제 - 인증 필수",
            description = "회차 삭제 - 인증 필수",
            security = {@SecurityRequirement(name = "session-token")})
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{meetingId}")
    public ApiResponse<Void> delete(@AuthenticationPrincipal UserPrincipal principal,
                                    @PathVariable String studyToken,
                                    @PathVariable Long meetingId) {
        Long avatarId = principal.getAvatarId();
        meetingDeleteService.delete(avatarId, studyToken, meetingId);
        return ApiResponse.noContent();
    }
}
