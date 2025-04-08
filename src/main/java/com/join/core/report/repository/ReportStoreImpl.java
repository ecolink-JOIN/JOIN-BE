package com.join.core.report.repository;

import com.join.core.report.constant.ReportType;
import com.join.core.report.domain.Report;
import com.join.core.report.domain.ReportStore;
import com.join.core.study.domain.Study;
import com.join.core.avatar.domain.Avatar;
import com.join.core.report.dto.request.ReportRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReportStoreImpl implements ReportStore {

    private final ReportRepository reportRepository;

    @Override
    public void store(ReportRequest reportRequest, Study study, Avatar writer, Avatar reporter) {
        ReportType reportType = reportRequest.getReportType();
        String reason = reportRequest.getReason();

        Report report = new Report(reportType, study, writer, reporter, reason);

        reportRepository.save(report);
    }

}
