package com.join.core.block.controller;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.block.dto.response.BlockMemberResponse;
import com.join.core.block.service.BlockReadService;
import com.join.core.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/blocks")
public class BlockReadController {

    private final BlockReadService blockReadService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ApiResponse<Collection<BlockMemberResponse>> getBlocks(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ApiResponse.ok(blockReadService.getBlocks(userPrincipal.getAvatarToken()));
    }
}
