package com.join.core.study.service.dto;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.study.dto.request.SearchParameter;
import org.springframework.data.domain.Pageable;

public record SearchCommand(
        UserPrincipal userPrincipal,
        SearchParameter parameter,
        Pageable pageable
) {
}
