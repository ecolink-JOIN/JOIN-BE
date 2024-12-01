package com.join.core.application.repository;

import com.join.core.application.constant.ApplicationStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.join.core.application.domain.QApplication.application;

@RequiredArgsConstructor
@Repository
public class ApplicationQueryRepositoryImpl implements ApplicationQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Double getAverageByStudyId(Long studyId) {
        return queryFactory
                .select(application.avatar.totalRating.avg().coalesce(0.0))
                .from(application)
                .where(
                        application.study.id.eq(studyId),
                        application.status.eq(ApplicationStatus.APPROVED)
                )
                .fetchOne();
    }
}
