package com.join.core.common.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

	/**
	 * Etc
	 */
	INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST, "E-001", "잘못된 요청입니다."),

	/**
	 * 아바타 관련 오류
	 */
	AVATAR_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "A-001", "주어진 식별자로 아바타를 찾을 수 없습니다."),

	/**
	 * 유저 관련 오류
	 */
	USER_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "U-001", "주어진 식별자로 유저를 찾을 수 없습니다."),

	/**
	 * 인증 관련 오류
	 */
	UNREGISTERED_PROVIDER(HttpStatus.BAD_REQUEST, "OA-001", "등록되지 않은 프로바이더가 입력되었습니다."),
	EMAIL_IS_REGISTER_WITH_ANOTHER_PROVIDER(HttpStatus.BAD_REQUEST, "OA-002", "같은 이메일이 다른 소셜 로그인 플랫폼으로 가입되어 있습니다."),
	UNDEFINED_PROVIDER(HttpStatus.INTERNAL_SERVER_ERROR, "OA-003", "유저타입에 정의되지 않은 프로바이더가 입력되었습니다."),

	/**
	 * 닉네임 관련 오류
	 */
	FAIL_TO_FIND_UNIQUE_NICKNAME(HttpStatus.INTERNAL_SERVER_ERROR, "N-001", "유일한 닉네임을 찾는데 실패했습니다."),
	DUPLICATED_NICKNAME(HttpStatus.BAD_REQUEST, "N-002", "이미 사용중인 닉네임 입니다."),

	/**
	 * 파일 업로드 관련 오류
	 */
	NOT_IMAGE_FILE(HttpStatus.BAD_REQUEST, "F-001", "전송된 파일의 형식이 이미지가 아닙니다."),
	FAIL_TO_GET_TYPE_OF_IMAGE(HttpStatus.INTERNAL_SERVER_ERROR, "F-002", "이미지 파일의 타입을 가져오지 못했습니다."),
	FAIL_TO_ANALYZE_FILE(HttpStatus.INTERNAL_SERVER_ERROR, "F-003", "이미지 파일을 분석하는데 실패했습니다."),
	FAIL_TO_UPLOAD_FILE(HttpStatus.INTERNAL_SERVER_ERROR, "F-004", "파일 업로드에 실패했습니다."),
	IMAGE_FILE_IS_NULL(HttpStatus.BAD_REQUEST, "F-005", "요청된 파일이 null입니다.");

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}
