package com.join.core.proof.controller;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.common.response.ApiResponse;
import com.join.core.proof.dto.response.ProofPhotoResponse;
import com.join.core.proof.service.photo.ProofPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/proof/files")
public class ProofPhotoController {

    private final ProofPhotoService proofPhotoService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ApiResponse<ProofPhotoResponse> save(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestPart MultipartFile file
    ) {
        return ApiResponse.created(
                proofPhotoService.savePhoto(file, userPrincipal.getAvatarId())
        );
    }

}
