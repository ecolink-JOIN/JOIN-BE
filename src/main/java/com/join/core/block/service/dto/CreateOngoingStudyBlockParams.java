package com.join.core.block.service.dto;

import java.time.LocalDate;

public record CreateOngoingStudyBlockParams(
        String subjectAvatarToken,
        String targetAvatarToken,
        String studyToken,
        LocalDate blockDate
) {
}
