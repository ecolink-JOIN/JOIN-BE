package com.join.core.study.repository.condition;

import com.join.core.common.constant.DayType;
import com.join.core.study.constant.TimeZone;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Stream;

import static com.join.core.address.domain.QAddress.address;
import static com.join.core.schedule.domain.QStudySchedule.studySchedule;

public record CustomStudyCondition(
        List<DayType> possibleDays,
        TimeZone timeZone,
        Integer minParticipationCount,
        Integer maxParticipationCount,
        String province,
        String city
) {

    public NumberExpression<Integer> toScore() {
        return Stream.of(
                        eqPossibleDays(),
                        eqTimeZone(),
                        eqParticipationCount(),
                        eqAddress()
                )
                .reduce(Expressions.asNumber(0), NumberExpression::add);
    }

    private NumberExpression<Integer> eqPossibleDays() {
        if (possibleDays == null) {
            return Expressions.asNumber(0);
        }
        return createScoreExpression(studySchedule.weekOfDay.in(possibleDays));
    }

    private NumberExpression<Integer> eqTimeZone() {
        if (timeZone == null) {
            return Expressions.asNumber(0);
        }
        return createScoreExpression(
                studySchedule.stTime.goe(timeZone.getStartTime())
                        .and(studySchedule.stTime.before(timeZone.getEndTime()))
        );
    }

    private NumberExpression<Integer> eqParticipationCount() {
        if (minParticipationCount == null && maxParticipationCount == null) {
            return Expressions.asNumber(0);
        }
        return createScoreExpression(
                studySchedule.id.count().between(minParticipationCount, maxParticipationCount)
        );
    }

    private NumberExpression<Integer> eqAddress() {
        if (StringUtils.isEmpty(province) && StringUtils.isEmpty(city)) {
            return Expressions.asNumber(0);
        }
        BooleanExpression addressCondition = createAddressCondition();
        return createScoreExpression(addressCondition);
    }

    private BooleanExpression createAddressCondition() {
        if (StringUtils.isEmpty(province)) {
            return address.city.eq(city);
        }
        if (StringUtils.isEmpty(city)) {
            return address.province.eq(province);
        }
        return address.province.eq(province).and(address.city.eq(city));
    }

    private NumberExpression<Integer> createScoreExpression(BooleanExpression expression) {
        return Expressions.cases()
                .when(expression)
                .then(1)
                .otherwise(0);
    }
}
