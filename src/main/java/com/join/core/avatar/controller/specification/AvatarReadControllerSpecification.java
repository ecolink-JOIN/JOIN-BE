package com.join.core.avatar.controller.specification;

import com.join.core.avatar.dto.response.AvatarParticipationRateResponse;
import com.join.core.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;

public interface AvatarReadControllerSpecification {

    @Tag(name = "${swagger.tag.enrollment-detail}")
    @Operation(summary = "사용자별 인증률 및 출석률 조회 - 인증 필수",
            description = "사용자별 인증률 및 출석률 조회",
            security = {@SecurityRequirement(name = "session-token")})
    ApiResponse<AvatarParticipationRateResponse> getAvatarParticipationRate(@PathVariable String avatarToken);
}
