package com.join.core.study.service.dto;

import com.join.core.auth.domain.UserPrincipal;
import com.join.core.study.constant.StudyForm;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public record StudyOrderByPopularityCommand(
        UserPrincipal userPrincipal,
        String categoryName,
        StudyForm form,
        LocalDateTime now,
        Pageable pageable
) {
}
