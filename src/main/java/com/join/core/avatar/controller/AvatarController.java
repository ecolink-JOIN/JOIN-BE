package com.join.core.avatar.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.avatar.domain.AvatarCommand;
import com.join.core.avatar.domain.AvatarInfo;
import com.join.core.avatar.domain.AvatarService;
import com.join.core.common.config.swagger.SwaggerBody;
import com.join.core.common.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Encoding;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/avatars")
public class AvatarController {

	private final AvatarService avatarService;

	@Tag(name = "${swagger.tag.sign-up}")
	@Operation(summary = "닉네임 유효성 검사 API", description = "닉네임 유효성 검사 API")
	@GetMapping("/nickname/valid")
	public ApiResponse<AvatarInfo.ValidNickname> isValidNickname(
		@ParameterObject AvatarCommand.ChangeNickname command) {
		return ApiResponse.ok(avatarService.isValid(command));
	}

	@Tag(name = "${swagger.tag.sign-up}")
	@Operation(summary = "닉네임 변경 API - 인증 필요",
		description = "닉네임 변경 API - 인증 필요",
		security = {@SecurityRequirement(name = "session-token")})
	@PreAuthorize("isAuthenticated()")
	@PatchMapping("/nickname")
	public ApiResponse<AvatarInfo.ValidNickname> changeNickname(@AuthenticationPrincipal UserPrincipal principal,
		@RequestBody @Valid AvatarCommand.ChangeNickname command) {
		return ApiResponse.ok(avatarService.changeNickname(principal.getAvatarId(), command));
	}

	@Tag(name = "${swagger.tag.sign-up}")
	@Tag(name = "${swagger.tag.profile-update}")
	@Operation(summary = "프로필 사진 변경 API - 인증 필요",
		description = "프로필 사진 변경 - 인증 필요, "
					  + "request 부분을 application/json 으로 설정해서 요청을 보내주세요.",
		security = {@SecurityRequirement(name = "session-token")})
	@SwaggerBody(content = @Content(
		encoding = @Encoding(name = "request", contentType = MediaType.APPLICATION_JSON_VALUE)))
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value = "/photos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ApiResponse<Void> changePhoto(
		@AuthenticationPrincipal UserPrincipal principal,
		@Parameter(description = "변경할 프로필 사진 파일")
		@RequestPart(value = "file", required = false) @Nullable MultipartFile file,
		@Valid @RequestPart("request") AvatarCommand.ChangePhoto command
	) {
		avatarService.changePhoto(principal.getAvatarId(), command, file);
		return ApiResponse.ok();
	}

}
