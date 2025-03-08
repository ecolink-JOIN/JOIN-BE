package com.join.core.proof.service.dto;

public record RejectCommand(Long avatarId, String studyToken, Integer meetingNo, Long proofId) {
}
