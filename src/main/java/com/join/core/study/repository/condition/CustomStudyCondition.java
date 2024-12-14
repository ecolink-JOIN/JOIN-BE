package com.join.core.study.repository.condition;

import com.join.core.common.constant.DayType;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;

import java.time.LocalTime;
import java.util.List;

import static com.join.core.address.domain.QAddress.address;
import static com.join.core.schedule.domain.QStudySchedule.studySchedule;

public record CustomStudyCondition(
        List<DayType> possibleDays,
        String timeZone,
        Integer minParticipationCount,
        Integer maxParticipationCount,
        String province,
        String city
) {

    public NumberExpression<Integer> toScore() {
        NumberExpression<Integer> score = Expressions.asNumber(0);
        score = eqPossibleDays(score);
        score = eqTimeZone(score);
        score = eqParticipationCount(score);
        score = eqAddress(score);
        return score;
    }

    private NumberExpression<Integer> eqPossibleDays(NumberExpression<Integer> score) {
        if (possibleDays == null || possibleDays.isEmpty()) {
            return score;
        }
        return score.add(Expressions.cases()
                .when(studySchedule.weekOfDay.in(possibleDays)).then(1)
                .otherwise(0));
    }

    // TODO: 오전/오후/저녁 처리 방식 상의 필요
    private NumberExpression<Integer> eqTimeZone(NumberExpression<Integer> score) {
        if (timeZone == null || timeZone.isEmpty()) {
            return score;
        }
        if (timeZone.equals("오전")) {
            return score.add(Expressions.cases()
                    .when(studySchedule.stTime.before(LocalTime.of(12, 0, 0)))
                    .then(1)
                    .otherwise(0));
        } else {
            return score.add(Expressions.cases()
                    .when(studySchedule.stTime.after(LocalTime.of(12, 0, 0)))
                    .then(1)
                    .otherwise(0));
        }
    }

    private NumberExpression<Integer> eqParticipationCount(NumberExpression<Integer> score) {
        if (minParticipationCount == null && maxParticipationCount == null) {
            return score;
        }
        return score.add(Expressions.cases()
                .when(studySchedule.id.count().between(minParticipationCount, maxParticipationCount))
                .then(1)
                .otherwise(0));
    }

    private NumberExpression<Integer> eqAddress(NumberExpression<Integer> score) {
        if ((province == null || province.isEmpty()) || (city == null || city.isEmpty())) {
            if (province == null || province.isEmpty()) {
                return eqCityOnly(score);
            }
            return eqProvinceOnly(score);
        }
        return score.add(Expressions.cases()
                .when(address.province.eq(province).and(address.city.eq(city))).then(1)
                .otherwise(0));
    }

    private NumberExpression<Integer> eqProvinceOnly(NumberExpression<Integer> score) {
        if (province == null || province.isEmpty()) {
            return score;
        }
        return score.add(Expressions.cases()
                .when(address.province.eq(province)).then(1)
                .otherwise(0));
    }

    private NumberExpression<Integer> eqCityOnly(NumberExpression<Integer> score) {
        if (city == null || city.isEmpty()) {
            return score;
        }
        return score.add(Expressions.cases()
                .when(address.city.eq(city)).then(1)
                .otherwise(0));
    }
}
