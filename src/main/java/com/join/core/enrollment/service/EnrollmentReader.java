package com.join.core.enrollment.service;

import java.util.List;

import com.join.core.avatar.domain.Avatar;
import com.join.core.enrollment.domain.Enrollment;

public interface EnrollmentReader {

    double getAverageByStudyId(Long studyId);
    Avatar getLeaderByStudyId(Long studyId);
    boolean existEnrollmentByAvatarIdAndStudyId(Long avatarId, Long studyId);
    void validateEnrollment(Long avatarId, Long studyId);
    Enrollment getEnrollmentByAvatarIdAndStudyId(Long avatarId, Long studyId);
    List<Enrollment> getByStudyId(Long studyId);
}
