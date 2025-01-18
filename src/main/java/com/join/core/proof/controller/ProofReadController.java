package com.join.core.proof.controller;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import com.join.core.proof.controller.specification.ProofReadControllerSpecification;
import com.join.core.proof.dto.response.CheckProofResponse;
import com.join.core.proof.service.ProofReadService;
import com.join.core.proof.service.dto.CheckProofCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/study/{studyToken}/meetings/{meetingNo}/proofs")
public class ProofReadController implements ProofReadControllerSpecification {

    private final ProofReadService proofReadService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<CheckProofResponse> getProofStatus(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable String studyToken,
            @PathVariable Integer meetingNo
    ) {
        return ApiResponse.ok(
                proofReadService.getProofStatus(new CheckProofCommand(
                        userPrincipal.getAvatarId(),
                        studyToken,
                        meetingNo
                ))
        );
    }
}
