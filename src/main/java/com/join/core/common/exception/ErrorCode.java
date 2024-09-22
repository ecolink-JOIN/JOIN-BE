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
	 * 공통
	 */
	INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "C-001", "잘못된 매개변수가 입력되었습니다."),

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
	DUPLICATED_NICKNAME(HttpStatus.BAD_REQUEST, "N-001", "이미 사용 중인 닉네임입니다."),
	INVALID_NICKNAME_FORMAT(HttpStatus.BAD_REQUEST, "N-002", "닉네임 포맷이 적절하지 않습니다."),

	/**
	 * 파일 업로드 관련 오류
	 */
	NOT_IMAGE_FILE(HttpStatus.BAD_REQUEST, "F-001", "전송된 파일의 형식이 이미지가 아닙니다."),
	FAIL_TO_GET_MIME_TYPE_OF_FILE(HttpStatus.INTERNAL_SERVER_ERROR, "F-002", "파일의 MIME 타입을 가져오지 못했습니다."),
	FAIL_TO_ANALYZE_FILE(HttpStatus.INTERNAL_SERVER_ERROR, "F-003", "이미지 파일을 분석하는데 실패했습니다."),
	FAIL_TO_UPLOAD_FILE(HttpStatus.INTERNAL_SERVER_ERROR, "F-004", "파일 업로드에 실패했습니다."),
	IMAGE_FILE_IS_NULL(HttpStatus.BAD_REQUEST, "F-005", "요청된 파일이 null입니다."),
	IMAGE_EXTENSION_IS_NOT_ALLOWED(HttpStatus.BAD_REQUEST, "F-006", "요청된 파일의 확장자는 지원되지 않습니다."),

	/**
	 * 약관 관련 오류
	 */
	TERM_NOT_EXIST(HttpStatus.BAD_REQUEST, "T-001", "요청된 약관은 존재하지 않습니다."),

	/**
	 * 주소 선택 관련 오류
	 */
	ADDRESS_SELECTION_REQUIRED(HttpStatus.BAD_REQUEST, "AD-001", "주소 선택이 누락되었습니다."),

	/**
	 * 카테고리 선택 관련 오류
	 */
	CATEGORY_SELECTION_REQUIRED(HttpStatus.BAD_REQUEST, "C-001", "카테고리 선택이 누락되었습니다."),

	/**
	 * 스터디 관련 오류
	 */
	STUDY_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "S-001", "주어진 식별자로 스터디를 찾을 수 없습니다."),
	DUPLICATE_APPLICATION(HttpStatus.BAD_REQUEST, "S-002", "이미 지원한 스터디입니다."),
	APPLICATION_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "S-003", "주어진 식별자로 지원 정보를 찾을 수 없습니다."),

	/**
	 * 권한 관련 오류
	 */
	UNAUTHORIZED_ACCESS(HttpStatus.INTERNAL_SERVER_ERROR, "AU-001", "해당 요청에 대한 권한이 없습니다.");

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;

}
