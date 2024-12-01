package com.join.core.study.repository;

import com.join.core.application.constant.ApplicationStatus;
import com.join.core.study.domain.Study;
import com.join.core.study.repository.condition.EssentialStudyCondition;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.join.core.application.domain.QApplication.application;
import static com.join.core.bookmark.domain.QBookmark.bookmark;
import static com.join.core.history.domain.QViewHistory.viewHistory;
import static com.join.core.study.domain.QStudy.study;

@RequiredArgsConstructor
@Repository
public class StudyQueryRepositoryImpl implements StudyQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Study> getStudiesOrderByPopularity(EssentialStudyCondition condition, LocalDateTime now, Pageable pageable) {
        List<Study> content = getPopularityStudies(condition, now, pageable);
        Long count = getStudiesCount(condition);
        return new PageImpl<>(content, pageable, count);
    }

    private List<Study> getPopularityStudies(EssentialStudyCondition condition, LocalDateTime now, Pageable pageable) {
        return queryFactory.selectFrom(study)
                .leftJoin(viewHistory).on(
                        viewHistory.study.id.eq(study.id),
                        viewHistory.createdDate.after(now.minusDays(7))
                )
                .leftJoin(bookmark).on(
                        bookmark.study.id.eq(study.id),
                        bookmark.createdDate.after(now.minusDays(7))
                )
                .leftJoin(application).on(
                        application.study.id.eq(study.id),
                        application.status.eq(ApplicationStatus.APPROVED)
                )
                .where(condition.toBooleanBuilder())
                .groupBy(study.id)
                .orderBy(
                        getPopularity().desc(),
                        getStudiesAvg().desc(),
                        study.studyName.asc()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private NumberExpression<Long> getPopularity() {
        return viewHistory.id.countDistinct().add(bookmark.id.countDistinct());
    }

    private NumberExpression<Double> getStudiesAvg() {
        return application.avatar.totalRating.avg();
    }

    private Long getStudiesCount(EssentialStudyCondition condition) {
        return queryFactory.select(study.count())
                .from(study)
                .where(condition.toBooleanBuilder())
                .fetchOne();
    }
}
