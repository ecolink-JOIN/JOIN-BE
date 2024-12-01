package com.join.core.study.repository.condition;

import com.join.core.category.domain.Category;
import com.join.core.study.constant.StudyForm;
import com.join.core.study.constant.StudyStatus;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import static com.join.core.study.domain.QStudy.study;

public record EssentialStudyCondition(Category category, StudyForm studyForm) {


    public BooleanBuilder toBooleanBuilder() {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(eqCategoryId());
        builder.and(eqStudyForm());
        builder.and(study.status.eq(StudyStatus.RECRUITING));
        return builder;
    }
    private BooleanExpression eqCategoryId() {
        if (category == null) {
            return null;
        }
        return study.category.id.eq(category.getId());
    }

    private BooleanExpression eqStudyForm() {
        if (studyForm == null) {
            return null;
        }
        return study.form.eq(studyForm);
    }
}
