package com.join.core.proof.controller.specification;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import com.join.core.proof.dto.response.CheckProofResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;

public interface ProofReadControllerSpecification {

    @Tag(name = "${swagger.tag.proof}")
    @Operation(summary = "회차 인증 여부 조회 - 인증 필수",
            description = "회차 인증 여부 조회 기능 - 인증 필수",
            security = {@SecurityRequirement(name = "session-token")})
    ApiResponse<CheckProofResponse> getProofStatus(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable String studyToken,
            @PathVariable Integer meetingNo
    );
}
