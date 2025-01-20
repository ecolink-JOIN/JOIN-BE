package com.join.core.block.dto.request;

import java.time.LocalDate;

public record CreateBlockRequest(String targetAvatarToken, LocalDate blockDate) {
}
