package com.join.core.enrollment.service;

import com.join.core.avatar.domain.Avatar;
import com.join.core.enrollment.domain.Enrollment;

import java.util.List;

public interface EnrollmentReader {

    double getAverageByStudyId(Long studyId);
    Avatar getLeaderByStudyId(Long studyId);
    List<Enrollment> findJoinedEnrollmentByStudyId(Long studyId);
    boolean existEnrollmentByAvatarIdAndStudyId(Long avatarId, Long studyId);
    void validateEnrollment(Long avatarId, Long studyId);
    Enrollment getEnrollmentByAvatarIdAndStudyId(Long avatarId, Long studyId);
}
