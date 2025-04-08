package com.join.core.study.dto.response;

import java.util.Collection;

public record StudyListForBlockResponse(
        String title,
        String studyToken,
        Collection<AvatarResponse> members,
        boolean isActive
) {
}
