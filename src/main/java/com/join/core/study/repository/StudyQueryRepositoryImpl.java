package com.join.core.study.repository;

import com.join.core.application.constant.ApplicationStatus;
import com.join.core.study.constant.StudyForm;
import com.join.core.study.constant.StudyStatus;
import com.join.core.study.domain.Study;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
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
    public List<Study> getStudiesOrderByPopularity(Long categoryId, StudyForm form, LocalDateTime now) {
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
                .where(
                        eqCategory(categoryId),
                        eqForm(form),
                        study.status.eq(StudyStatus.RECRUITING)
                )
                .groupBy(study.id)
                .orderBy(
                        getPopularity().desc(),
                        getStudiesAvg().desc(),
                        study.studyName.asc()
                )
                .fetch();
    }

    private BooleanExpression eqCategory(Long categoryId) {
        if (categoryId == null) {
            return null;
        }
        return study.category.id.eq(categoryId);
    }

    private BooleanExpression eqForm(StudyForm form) {
        if (form == null) {
            return null;
        }
        return study.form.eq(form);
    }

    private NumberExpression<Long> getPopularity() {
        return viewHistory.id.countDistinct().add(bookmark.id.countDistinct());
    }

    private NumberExpression<Double> getStudiesAvg() {
        return application.avatar.totalRating.avg();
    }
}
