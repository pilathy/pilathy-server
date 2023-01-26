package com.pilathy.domain.rds.domain.common;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@EqualsAndHashCode
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class DateInterval {

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Builder(access = AccessLevel.PRIVATE)
    private DateInterval(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static DateInterval of(LocalDate startDate, LocalDate endDate) {
        validateInterval(startDate, endDate);
        return DateInterval.builder()
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }

    private static void validateInterval(LocalDate startDate, LocalDate endDate) {
        // TODO 예외 처리
//        if (startDate.isAfter(endDate)) {
//            throw new InvalidException(String.format("시작 날짜(%s)가 종료 날짜(%s)보다 이후 일 수 없습니", startDate, endDate), ErrorCode.E400_INVALID_DATE_TIME_INTERVAL);
//        }
    }

}
