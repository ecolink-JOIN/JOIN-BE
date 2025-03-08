package com.join.core.report.repository;

import com.join.core.avatar.domain.Avatar;
import com.join.core.common.exception.ErrorCode;
import com.join.core.common.exception.impl.BadRequestException;
import com.join.core.report.domain.Report;
import com.join.core.report.domain.ReportReader;
import com.join.core.study.domain.Study;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class ReportReaderImpl implements ReportReader {

    private final ReportRepository reportRepository;

    @Override
    public void validateReportTimeLimit(Avatar reporter, Study study) {
        LocalDateTime timeLimit = LocalDateTime.now().minusMinutes(30);
        Report recentReport = reportRepository.findRecentReport(reporter, study, timeLimit);

        if (recentReport != null) {
            throw new BadRequestException(ErrorCode.REPORT_TIME_LIMIT);
        }
    }

}