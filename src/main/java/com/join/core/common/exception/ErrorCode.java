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
	AVATAR_NOT_FOUND(HttpStatus.NOT_FOUND, "A-001", "주어진 식별자로 아바타를 찾을 수 없습니다."),

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
	ADDRESS_INPUT_REQUIRED(HttpStatus.BAD_REQUEST, "AD-001", "주소 입력이 누락되었습니다."),

	/**
	 * 카테고리 선택 관련 오류
	 */
	CATEGORY_SELECTION_REQUIRED(HttpStatus.BAD_REQUEST, "CA-001", "카테고리 선택이 누락되었습니다."),

	/**
	 * 스터디 관련 오류
	 */
	STUDY_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "S-001", "주어진 식별자로 스터디를 찾을 수 없습니다."),
	DUPLICATE_APPLICATION(HttpStatus.BAD_REQUEST, "S-002", "이미 지원한 스터디입니다."),
	APPLICATION_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "S-003", "주어진 식별자로 지원 정보를 찾을 수 없습니다."),
	NOT_ACTIVE_STUDY(HttpStatus.BAD_REQUEST, "S-004", "진행 중인 스터디가 아닙니다."),

	/**
	 * 벌금 관련 오류
	 */
	FINE_INPUT_REQUIRED(HttpStatus.BAD_REQUEST, "F-001", "벌금 관련 입력이 누락되었습니다."),
	FINE_NOT_FOUND(HttpStatus.BAD_REQUEST, "F-002", "부과된 벌금이 없습니다."),

	/**
	 * 회차 관련 오류
	 */
	MEETING_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "M-001", "주어진 식별자로 회차를 찾을 수 없습니다."),

	/**
	 * 공지 관련 오류
	 */
	NOT_LEADER_OF_STUDY(HttpStatus.FORBIDDEN, "L-003", "스터디 공지를 작성할 수 있는 권한이 없습니다."),

	/**
	 * 권한 관련 오류
	 */
	UNAUTHORIZED_ACCESS(HttpStatus.INTERNAL_SERVER_ERROR, "AU-001", "해당 요청에 대한 권한이 없습니다."),


	/**
	 * 출석 관련 오류
	 */
	OUT_OF_ATTENDANCE_TIME(HttpStatus.BAD_REQUEST, "AT-001", "출석 시간이 아닙니다."),
	ATTENDANCE_ALREADY_COMPLETED(HttpStatus.CONFLICT, "AT-003", "이미 출석이 완료되었습니다."),
	ATTENDANCE_NOT_FOUND(HttpStatus.NOT_FOUND, "AT-004", "존재하지 않는 출석 내역입니다."),
	ATTENDANCE_UPDATE_FORBIDDEN(HttpStatus.FORBIDDEN, "AT-005", "출석 수정 권한이 없습니다."),

	/**
	 * 인증 관련 오류
	 */
	PROOF_PHOTO_NOT_FOUND(HttpStatus.NOT_FOUND, "PR-001", "해당 파일을 찾을 수 없습니다."),
	EMPTY_PROOF_PHOTO(HttpStatus.BAD_REQUEST, "PR-002", "사진 인증을 위해 이미지를 업로드해야 합니다."),
	DUPLICATED_PROOF(HttpStatus.CONFLICT, "PR-003", "이미 진행 중인 인증이 존재합니다."),
	OUT_OF_PROOF_TIME(HttpStatus.BAD_REQUEST, "PR-004", "인증 시간이 아닙니다."),
	INVALID_PROOF_STATUS(HttpStatus.INTERNAL_SERVER_ERROR, "PR-005", "인증 상태를 조회하는 과정에서 오류가 발생하였습니다."),
	INVALID_PROOF_ID(HttpStatus.BAD_REQUEST, "PR-006", "존재하지 않는 인증 정보입니다,"),
	ALREADY_CHECK_PROOF(HttpStatus.CONFLICT, "PR-007", "이미 수락 또는 반려된 인증입니다."),

	/**
	* 스터디 참여자 관련 오류
	*/
	NOT_MEMBER_OF_STUDY(HttpStatus.FORBIDDEN, "EN-001", "스터디에 참여중인 사용자가 아닙니다."),
    LEADER_ONLY_ACCESS(HttpStatus.FORBIDDEN, "EN-002", "해당 기능은 스터디 리더만 사용할 수 있습니다."),
	INVALID_MEMBER(HttpStatus.BAD_REQUEST, "EN-003", "존재하지 않는 팀원입니다."),
	MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "EN-004", "스터디에 참여중인 스터디원이 없습니다."),

	/**
	* 북마크 관련 오류
	*/
	BOOKMARK_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "B-001", "북마크가 이미 존재합니다."),
    BOOKMARK_NOT_FOUND(HttpStatus.BAD_REQUEST, "B-002", "취소할 북마크를 찾을 수 없습니다."),

	/**
	 * 신고 관련 오류
	 */
	REPORT_TIME_LIMIT(HttpStatus.BAD_REQUEST, "R-001", "신고자는 30분 내에 동일한 신고를 할 수 없습니다."),

    /**
	 * 평가 관련 오류
	 */
	EVALUATION_PERIOD_INVALID(HttpStatus.BAD_REQUEST, "EV-001", "스터디원 평가 기간이 아닙니다."),

	/**
	 * 차단 관련 오류
	 */
	BLOCK_ALREADY_EXISTS(HttpStatus.CONFLICT, "BL-001", "이미 존재하는 차단 내역입니다."),
	SELF_BLOCK_NOT_ALLOWED(HttpStatus.BAD_REQUEST, "BL-002", "자기 자신을 차단할 수 없습니다."),
	ACTIVE_STUDY_EXISTS(HttpStatus.BAD_REQUEST, "BL-003", "함께 진행 중인 스터디가 존재합니다."),
	ONGOING_STUDY_MEMBER_ONLY(HttpStatus.BAD_REQUEST, "BL-004", "해당 기능은 진행 중인 스터디의 팀원만 차단할 수 있습니다."),
	STUDY_LEADER_CAN_NOT_WITDRAW(HttpStatus.BAD_REQUEST, "BL-005", "함께 진행 중인 스터디 중 팀장인 스터디가 존재합니다,"),
	TARGET_IS_NOT_MEMBER_OF_STUDY(HttpStatus.BAD_REQUEST, "BL-006", "차단하려는 상대가 스터디의 팀원이 아닙니다."),

	/**
	 * 스터디장 위임 관련 오류
	 */
	ALREADY_STUDY_LEADER(HttpStatus.BAD_REQUEST, "DG-001", "이미 스터디장인 사용자입니다."),

	/**
	 * 자동 알림 관련 오류
	 */
	BATCHJOB_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "BJ-001", "해당 시간에 이미 등록된 자동 알림이 있습니다."),
	BATCHJOB_NOT_FOUND(HttpStatus.BAD_REQUEST, "BJ-002", "변경할 자동 알림을 찾을 수 없습니다."),

	/**
	 * 앱 공지사항 관련 오류
	 */
	NOTICE_NOT_FOUND(HttpStatus.BAD_REQUEST, "AN-001", "앱 공지사항을 찾을 수 없습니다."),
  
    /**
	 * 탈퇴 관련 오류
	 */
	WITHDRAW_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "W-001", "이미 등록된 탈퇴 요청이 있습니다.");

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;

}
