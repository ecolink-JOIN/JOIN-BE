package com.join.core.study.service.dto;

import com.join.core.auth.domain.UserPrincipal;
import org.springframework.data.domain.Pageable;

public record SearchCommand(
        UserPrincipal userPrincipal,
        String keyword,
        Pageable pageable
) {
}
