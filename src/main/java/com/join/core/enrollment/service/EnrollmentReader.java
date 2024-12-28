package com.join.core.enrollment.service;

import com.join.core.avatar.domain.Avatar;

public interface EnrollmentReader {

    double getAverageByStudyId(Long studyId);
    Avatar getLeaderByStudyId(Long studyId);
}
