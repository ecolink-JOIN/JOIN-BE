package com.join.core.avatar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.join.core.avatar.domain.AvatarCommand;
import com.join.core.avatar.domain.AvatarInfo;
import com.join.core.avatar.domain.AvatarService;
import com.join.core.common.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/avatars")
public class AvatarController {

	private final AvatarService avatarService;

	@Tag(name = "${swagger.tag.sign-up}")
	@Operation(summary = "닉네임 유효성 검사 API",
		description = "닉네임 유효성 검사 API")
	@GetMapping("/nickname/valid")
	public ApiResponse<AvatarInfo.ValidNickname> isValidNickname(AvatarCommand.ChangeNickname command) {
		return ApiResponse.ok(avatarService.isValid(command));
	}

}
