package com.join.core.meeting.controller;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import com.join.core.meeting.controller.specification.MeetingControllerSpecification;
import com.join.core.meeting.domain.MeetingAppendService;
import com.join.core.meeting.domain.MeetingDeleteService;
import com.join.core.meeting.domain.MeetingReadService;
import com.join.core.meeting.dto.request.MeetingAppendRequest;
import com.join.core.meeting.dto.response.MeetingResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/study/{studyToken}/meetings")
public class MeetingController implements MeetingControllerSpecification {

    private final MeetingAppendService meetingAppendService;
    private final MeetingReadService meetingReadService;
    private final MeetingDeleteService meetingDeleteService;

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

    @GetMapping
    public ApiResponse<List<MeetingResponse>> getMeetingDetails(@PathVariable String studyToken) {
        return ApiResponse.ok(meetingReadService.getMeetings(studyToken));
    }

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
