package com.join.core.block.service.dto;

import java.time.LocalDate;

public record CreateBlockParams(
        String subjectAvatarToken,
        String targetAvatarToken,
        LocalDate blockDate
) {
}
