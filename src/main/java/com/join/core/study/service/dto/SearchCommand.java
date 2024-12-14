package com.join.core.study.service.dto;

import com.join.core.auth.domain.UserPrincipal;

public record SearchCommand(UserPrincipal userPrincipal, String keyword) {
}
