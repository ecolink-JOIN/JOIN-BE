package com.join.core.report.domain;

import com.join.core.report.dto.request.ReportRequest;
import com.join.core.study.domain.Study;
import com.join.core.avatar.domain.Avatar;

public interface ReportStore {
    void store(ReportRequest reportRequest, Study study, Avatar writer, Avatar reporter);

}