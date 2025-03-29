package com.join.core.study.repository;

import com.join.core.enrollment.constant.EnrollmentStatus;
import com.join.core.enrollment.constant.StudyRole;
import com.join.core.study.constant.StudyStatus;
import com.join.core.study.domain.Study;
import com.join.core.study.repository.condition.CustomStudyCondition;
import com.join.core.study.repository.condition.EssentialStudyCondition;
import com.join.core.study.repository.condition.SearchCondition;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.join.core.address.domain.QAddress.address;
import static com.join.core.bookmark.domain.QBookmark.bookmark;
import static com.join.core.enrollment.domain.QEnrollment.enrollment;
import static com.join.core.history.domain.QViewHistory.viewHistory;
import static com.join.core.schedule.domain.QStudySchedule.studySchedule;
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
                .leftJoin(enrollment).on(
                        enrollment.study.id.eq(study.id),
                        enrollment.status.eq(EnrollmentStatus.JOINED)
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
        return enrollment.avatar.totalRating.avg();
    }

    private Long getStudiesCount(EssentialStudyCondition condition) {
        return queryFactory.select(study.count())
                .from(study)
                .where(condition.toBooleanBuilder())
                .fetchOne();
    }

    @Override
    public List<Study> getStudiesOrderByRecommendations(
            EssentialStudyCondition essentialStudyCondition,
            CustomStudyCondition customStudyCondition
    ) {
        return queryFactory.selectFrom(study)
                .leftJoin(enrollment).on(
                        enrollment.study.id.eq(study.id),
                        enrollment.status.eq(EnrollmentStatus.JOINED)
                )
                .leftJoin(studySchedule).on(
                        studySchedule.study.id.eq(study.id)
                )
                .where(
                        essentialStudyCondition.toBooleanBuilder()
                )
                .groupBy(study.id, studySchedule.id)
                .orderBy(
                        customStudyCondition.toScore().desc(),
                        getStudiesAvg().desc(),
                        study.studyName.asc()
                )
                .limit(20)
                .fetch();
    }

    @Override
    public boolean existsByEnrollmentsAvatarToken(String subjectToken, String targetToken) {
        return queryFactory.selectFrom(study)
                .leftJoin(enrollment).on(
                        enrollment.study.id.eq(study.id),
                        enrollment.status.eq(EnrollmentStatus.JOINED)
                )
                .where(
                        study.status.eq(StudyStatus.ACTIVE),
                        enrollment.avatar.avatarToken.eq(subjectToken)
                                .or(enrollment.avatar.avatarToken.eq(targetToken))
                )
                .groupBy(study.id)
                .having(enrollment.avatar.avatarToken.countDistinct().eq(2L))
                .fetchFirst() != null;
    }


    @Override
    public List<Study> findAllByAvatarIdAndRole(Long avatarId, StudyRole status) {
        return queryFactory.selectFrom(study)
                .leftJoin(enrollment).on(
                        enrollment.avatar.id.eq(avatarId),
                        enrollment.role.eq(status)
                )
                .fetch();
    }

    @Override
    public List<Study> findJoinedStudyByAvatarId(Long avatarId) {
        return queryFactory.selectFrom(study)
                .leftJoin(enrollment).on(
                        enrollment.avatar.id.eq(avatarId),
                        enrollment.status.eq(EnrollmentStatus.JOINED)
                )
                .fetch();
    }

    @Override
    public List<Study> findBookmarkStudyByAvatarId(Long avatarId) {
        return queryFactory.selectFrom(study)
                .join(bookmark).on(
                        bookmark.avatar.id.eq(avatarId)
                )
                .orderBy(bookmark.updatedDate.desc())
                .fetch();
    }

    @Override
    public boolean existsByEnrollmentAvatarIdAndStudyToken(Long avatarId, String studyToken) {
        return queryFactory
                .selectOne()
                .from(enrollment)
                .join(enrollment.study, study)
                .where(
                        study.studyToken.eq(studyToken),
                        enrollment.avatar.id.eq(avatarId),
                        enrollment.status.eq(EnrollmentStatus.JOINED)
                )
                .fetchFirst() != null;
    }

    public List<Study> findByAvatarId(Long avatarId) {
        return queryFactory.selectFrom(study)
            .leftJoin(enrollment).on(enrollment.study.id.eq(study.id))
            .where(
                    enrollment.avatar.id.eq(avatarId),
                    enrollment.status.eq(EnrollmentStatus.JOINED)
            )
            .fetch();
    }

    @Override
    public Page<Study> searchByConditions(SearchCondition condition, Pageable pageable) {
        List<Study> content = getSearchStudy(condition, pageable);
        Long count = countSearchStudy(condition);
        return new PageImpl<>(content, pageable, count);

    }

    private List<Study> getSearchStudy(SearchCondition condition, Pageable pageable) {
        return queryFactory.selectFrom(study)
                .leftJoin(studySchedule).on(
                        studySchedule.study.id.eq(study.id)
                )
                .innerJoin(address).on(address.id.eq(study.address.id))
                .where(
                        condition.toBooleanBuilder()
                )
                .groupBy(study.id, studySchedule.id)
                .having(condition.getHavingClause())
                .orderBy(
                        study.title.asc()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private Long countSearchStudy(SearchCondition condition) {
        return queryFactory.select(study.id.count().coalesce(0L))
                .from(study)
                .leftJoin(studySchedule).on(studySchedule.study.id.eq(study.id))
                .join(address).on(address.id.eq(study.address.id))
                .where(
                        condition.toBooleanBuilder(),
                        condition.getWhereClause()
                )
                .fetchOne();
    }
}
