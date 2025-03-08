package com.join.core.report.domain;

import com.join.core.avatar.domain.Avatar;
import com.join.core.study.domain.Study;

public interface ReportReader {
    void validateReportTimeLimit(Avatar reporter, Study study);

}
