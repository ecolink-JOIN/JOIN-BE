package com.join.core.block.controller.specification;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.block.dto.request.CreateBlockRequest;
import com.join.core.block.dto.response.CreateBlockResponse;
import com.join.core.common.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;

public interface BlockControllerSpecification {

    @Tag(name = "${swagger.tag.block}")
    @Operation(summary = "일반 사용자 차단 - 인증 필수",
            description = "진행 중인 스터디의 멤버를 차단할 경우 예외 발생 - 별도의 API로 요청",
            security = {@SecurityRequirement(name = "session-token")})
    ApiResponse<CreateBlockResponse> create(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody CreateBlockRequest createBlockRequest
    );
}
