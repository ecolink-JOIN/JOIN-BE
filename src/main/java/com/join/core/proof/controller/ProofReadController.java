package com.join.core.proof.controller;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import com.join.core.proof.controller.specification.ProofReadControllerSpecification;
import com.join.core.proof.dto.response.CheckProofResponse;
import com.join.core.proof.dto.response.ProofDetailResponse;
import com.join.core.proof.dto.response.ProofsResponse;
import com.join.core.proof.service.ProofReadService;
import com.join.core.proof.service.dto.CheckProofParams;
import com.join.core.proof.service.dto.GetProofsParams;
import com.join.core.proof.service.dto.ProofDetailParams;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/study/{studyToken}")
public class ProofReadController implements ProofReadControllerSpecification {

    private final ProofReadService proofReadService;

    @GetMapping("/meetings/{meetingNo}/proofs")
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<CheckProofResponse> getProofStatus(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable String studyToken,
            @PathVariable Integer meetingNo
    ) {
        return ApiResponse.ok(
                proofReadService.getProofStatus(new CheckProofParams(
                        userPrincipal.getAvatarId(),
                        studyToken,
                        meetingNo
                ))
        );
    }

    @GetMapping("/meetings/{meetingNo}/proofs/{proofId}")
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<ProofDetailResponse> getProofDetail(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable String studyToken,
            @PathVariable Integer meetingNo,
            @PathVariable Long proofId
    ) {
        return ApiResponse.ok(
                proofReadService.getProofDetail(
                        new ProofDetailParams(
                                userPrincipal.getAvatarToken(),
                                proofId,
                                studyToken
                        )
                )
        );
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/avatars/{targetAvatarToken}/proofs")
    public ApiResponse<ProofsResponse> getProofs(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable String studyToken,
            @PathVariable String targetAvatarToken
    ) {

        return ApiResponse.ok(proofReadService.getProofs(new GetProofsParams(
                studyToken,
                userPrincipal.getAvatarToken(),
                targetAvatarToken
        )));
    }
}
