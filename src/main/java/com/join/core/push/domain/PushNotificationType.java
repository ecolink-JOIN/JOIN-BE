package com.join.core.push.domain;

import java.util.Map;

import lombok.Getter;

public enum PushNotificationType {
	STUDY_NAME_UPDATED("스터디명 변경", "{oldStudyName}이(가) {newStudyName}으로 변경되었습니다."),
	NEW_SESSION_ADDED("스터디 일정 변경", "{date}에 스터디 일정이 추가되었어요."),
	SESSION_REMOVED("스터디 일정 변경", "{date} 진행 예정이었던 스터디가 취소되었어요."),
	SCHEDULE_UPDATED("스터디 일정 변경", "{studyName}의 스터디 스케줄이 변경되었습니다."),
	NOTICE_UPDATED("스터디 신규 공지", "{studyName}의 스터디 공지 사항이 있습니다. 확인해주세요!"),
	RULES_UPDATED("스터디 규칙 변경", "{studyName}의 운영 규칙이 변경되었습니다."),
	RULES_GUIDE_UPDATED("스터디 규칙 안내 변경", "{studyName}의 규칙 안내가 변경되었습니다."),
	STUDY_ENDED("스터디 종료", "{studyName}의 스터디가 종료되었습니다."),
	NEW_APPLICANT_NOTIFICATION("스터디 신청자 확인하기", "스터디 신청자가 있어요. 지금 확인해보세요."),
	JOIN_APPROVED_NOTIFICATION("스터디 가입 확인하기", "{studyName} 스터디에 합류하게 되었어요. 지금 확인해보세요."),
	MEMBER_REMOVAL("스터디 퇴장 알림", "{studyName} 스터디와 더 이상 함께할 수 없어요. 사유 확인하기"),
	MEMBER_WITHDRAWAL_REQUEST("스터디 탈퇴 요청 확인하기", "{memberNickname}님이 {studyName}의 탈퇴를 요청합니다."),
	MEMBER_WITHDRAWAL_APPROVED("스터디 탈퇴 처리 완료", "{studyName} 탈퇴 처리가 완료되었습니다."),
	LEADER_DELEGATION_REQUEST("스터디 권한 위임 안내", "{leaderNickname}님이 {studyName}의 스터디장 위임을 요청합니다. 지금 확인해보세요."),
	LEADER_DELEGATION_APPROVED("스터디 권한 변경 안내", "{studyName}의 스터디장이 {newLeaderNickname}님으로 변경되었습니다.");

	@Getter
	private final String title;
	private final String template;

	PushNotificationType(String title, String template) {
		this.title = title;
		this.template = template;
	}

	public String apply(Map<String, String> parameters) {
		String result = template;
		for (Map.Entry<String, String> entry : parameters.entrySet()) {
			result = result.replace("{" + entry.getKey() + "}", entry.getValue());
		}
		return result;
	}

}

