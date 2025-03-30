package com.join.core.block.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.block.controller.specification.BlockControllerSpecification;
import com.join.core.block.dto.request.CreateBlockRequest;
import com.join.core.block.dto.request.CreateOngoingStudyBlockRequest;
import com.join.core.block.dto.response.CreateBlockResponse;
import com.join.core.block.service.BlockService;
import com.join.core.block.service.dto.CreateBlockParams;
import com.join.core.block.service.dto.CreateOngoingStudyBlockParams;
import com.join.core.common.response.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/blocks")
public class BlockController implements BlockControllerSpecification {

    private final BlockService blockService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ApiResponse<CreateBlockResponse> create(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody @Valid CreateBlockRequest createBlockRequest
    ) {
        return ApiResponse.created(blockService.block(
                new CreateBlockParams(
                        userPrincipal.getAvatarToken(),
                        createBlockRequest.targetAvatarToken(),
                        createBlockRequest.blockDate()
                )
        ));
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/study-member")
    public ApiResponse<CreateBlockResponse> createBlockStudyEnrollment(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody @Valid CreateOngoingStudyBlockRequest createOngoingStudyBlockRequest
    ) {
        return ApiResponse.created(blockService.blockStudyEnrollment(
                new CreateOngoingStudyBlockParams(
                        userPrincipal.getAvatarToken(),
                        createOngoingStudyBlockRequest.targetAvatarToken(),
                        createOngoingStudyBlockRequest.studyToken(),
                        createOngoingStudyBlockRequest.blockDate()
                )
        ));
    }
}
