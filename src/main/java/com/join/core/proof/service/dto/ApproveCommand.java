package com.join.core.proof.service.dto;

public record ApproveCommand(Long avatarId, String studyToken, Integer meetingNo, Long proofId) {
}
