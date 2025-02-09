package com.join.core.enrollment.repository;

import com.join.core.avatar.domain.Avatar;

import java.util.List;

public interface EnrollmentQueryRepository {

    Double getMemberAverageByStudyId(Long studyId);
    Avatar getLeaderByStudyId(Long studyId);
    List<Avatar> findAvatarsExceptPendingByStudyId(Long studyId);

    List<Avatar> findAvatarsJoinedByStudyId(Long studyId);
}
