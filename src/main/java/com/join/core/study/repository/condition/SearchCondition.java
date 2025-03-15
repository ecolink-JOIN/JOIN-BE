package com.join.core.study.repository.condition;

import com.join.core.category.domain.Category;
import com.join.core.common.constant.DayType;
import com.join.core.study.constant.StudyForm;
import com.join.core.study.constant.StudyStatus;
import com.join.core.study.constant.TimeZone;
import com.join.core.study.domain.QStudy;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static com.join.core.schedule.domain.QStudySchedule.studySchedule;
import static com.join.core.study.domain.QStudy.study;

public record SearchCondition(
        String keyword,
        Category category,
        StudyForm form,
        List<DayType> possibleDays,
        TimeZone timeZone,
        Integer minParticipationCount,
        Integer maxParticipationCount,
        String province,
        String city
) {

    public BooleanBuilder toBooleanBuilder() {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(containsTitleKeyword());
        builder.and(eqCategoryId());
        builder.and(eqStudyForm());
        builder.and(eqPossibleDays());
        builder.and(eqTimeZone());
        builder.and(eqAddress());
        builder.and(study.status.eq(StudyStatus.RECRUITING));
        return builder;
    }

    private BooleanExpression containsTitleKeyword() {
        if (keyword == null) {
            return null;
        }
        return study.title.contains(keyword);
    }

    private BooleanExpression eqCategoryId() {
        if (category == null) {
            return null;
        }
        return study.category.id.eq(category.getId());
    }

    private BooleanExpression eqStudyForm() {
        if (form == null) {
            return null;
        }
        return study.form.eq(form);
    }

    private BooleanExpression eqPossibleDays() {
        if (possibleDays == null) {
            return null;
        }
        return studySchedule.weekOfDay.in(possibleDays);
    }

    private BooleanExpression eqTimeZone() {
        if (timeZone == null) {
            return null;
        }
        return studySchedule.stTime.goe(timeZone.getStartTime())
                .and(studySchedule.stTime.before(timeZone.getEndTime()));
    }

    private BooleanExpression eqAddress() {
        if (StringUtils.isEmpty(province) && StringUtils.isEmpty(city)) {
            return null;
        }
        return study.address.province.eq(province).and(study.address.city.eq(city));
    }

    public BooleanExpression getHavingClause() {
        if (minParticipationCount == null || maxParticipationCount == null) {
            if (minParticipationCount != null) {
                return studySchedule.id.count().goe(minParticipationCount);
            }
            if (maxParticipationCount != null) {
                return studySchedule.id.count().loe(maxParticipationCount);
            }
            return studySchedule.id.count().between(0, 7);
        }

        return studySchedule.id.count().between(minParticipationCount, maxParticipationCount);
    }

    public BooleanExpression getWhereClause() {
        QStudy study = QStudy.study;
        if (minParticipationCount == null || maxParticipationCount == null) {
            if (minParticipationCount == null && maxParticipationCount == null) {
                return null;
            }
            if (minParticipationCount != null) {
                return JPAExpressions
                        .select(studySchedule.id.count())
                        .from(studySchedule)
                        .where(studySchedule.study.id.eq(study.id))
                        .goe((long) minParticipationCount);
            }
            return JPAExpressions
                    .select(studySchedule.id.count())
                    .from(studySchedule)
                    .where(studySchedule.study.id.eq(study.id))
                    .loe((long) maxParticipationCount);
        }
        return Expressions.booleanTemplate(
                "{0} BETWEEN ({1}) AND ({2})",
                JPAExpressions
                        .select(studySchedule.id.count())
                        .from(studySchedule)
                        .where(studySchedule.study.id.eq(study.id)),
                minParticipationCount,
                maxParticipationCount
        );
    }
}
