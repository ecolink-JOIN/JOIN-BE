package com.join.core.avatar.controller;

import com.join.core.avatar.dto.response.AvatarParticipationRateResponse;
import com.join.core.avatar.service.AvatarReadService;
import com.join.core.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/avatars")
public class AvatarReadController {

    private final AvatarReadService avatarReadService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{avatarToken}")
    public ApiResponse<AvatarParticipationRateResponse> getAvatarParticipationRate(@PathVariable String avatarToken) {
        return ApiResponse.ok(avatarReadService.getAttendanceAndProofRate(avatarToken));
    }
}
