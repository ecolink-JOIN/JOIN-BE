package com.join.core.block.controller;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.block.dto.request.CreateBlockRequest;
import com.join.core.block.dto.response.CreateBlockResponse;
import com.join.core.block.service.BlockService;
import com.join.core.block.service.dto.CreateBlockParams;
import com.join.core.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/blocks")
public class BlockController {

    private final BlockService blockService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ApiResponse<CreateBlockResponse> create(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody CreateBlockRequest createBlockRequest
    ) {
        return ApiResponse.created(blockService.createBlock(
                new CreateBlockParams(
                        userPrincipal.getAvatarToken(),
                        createBlockRequest.targetAvatarToken(),
                        createBlockRequest.blockDate()
                )
        ));
    }
}
