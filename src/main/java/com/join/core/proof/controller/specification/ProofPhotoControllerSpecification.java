package com.join.core.proof.controller.specification;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import com.join.core.proof.dto.response.ProofPhotoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

public interface ProofPhotoControllerSpecification {

    @Tag(name = "${swagger.tag.proof}")
    @Operation(summary = "인증 이미지 저장 - 인증 필수",
            description = "인증 이미지 저장 - 인증 필수",
            security = {@SecurityRequirement(name = "session-token")})
    ApiResponse<ProofPhotoResponse> save(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestPart MultipartFile file
    );
}
