package com.join.core.proof.controller;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import com.join.core.proof.dto.request.CreateProofRequest;
import com.join.core.proof.dto.response.CreateProofResponse;
import com.join.core.proof.service.ProofService;
import com.join.core.proof.service.dto.CreateProofCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/study/{studyToken}/meetings/{meetingNo}/proof")
public class ProofController {

    private final ProofService proofService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ApiResponse<CreateProofResponse> createProof(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable String studyToken,
            @PathVariable Integer meetingNo,
            @Valid @RequestBody CreateProofRequest createProofRequest
    ) {
        return ApiResponse.created(
                proofService.createProof(new CreateProofCommand(
                        createProofRequest.proofType(),
                        createProofRequest.proofPhotoUrl(),
                        userPrincipal.getAvatarId(),
                        studyToken,
                        meetingNo,
                        createProofRequest.provenDate()
                ))
        );
    }
}
