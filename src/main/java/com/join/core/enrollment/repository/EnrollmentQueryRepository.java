package com.join.core.enrollment.repository;

import com.join.core.avatar.domain.Avatar;

public interface EnrollmentQueryRepository {

    Double getMemberAverageByStudyId(Long studyId);
    Avatar getLeaderByStudyId(Long studyId);
}
