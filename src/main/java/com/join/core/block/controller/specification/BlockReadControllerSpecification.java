package com.join.core.block.controller.specification;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.block.dto.response.BlockMemberResponse;
import com.join.core.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.Collection;

public interface BlockReadControllerSpecification {

    @Tag(name = "${swagger.tag.block}")
    @Operation(summary = "차단 목록 조회 - 인증 필수",
            description = "차단 목록 조회",
            security = {@SecurityRequirement(name = "session-token")})
    ApiResponse<Collection<BlockMemberResponse>> getBlocks(@AuthenticationPrincipal UserPrincipal userPrincipal);
}
