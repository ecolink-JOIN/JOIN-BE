package com.join.core.block.dto.response;

import java.time.LocalDate;

public record CreateBlockResponse(Long id, String blockAvatarToken, LocalDate blockDate) {
}
