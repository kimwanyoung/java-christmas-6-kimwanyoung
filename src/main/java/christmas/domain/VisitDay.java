package christmas.domain;

import static christmas.constants.ExceptionMessage.INVALID_DAY_ERROR_MESSAGE;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class VisitDay {

    private final int visitDay;

    public VisitDay(int visitDay) {
        validate(visitDay);
        this.visitDay = visitDay;
    }

    public int getDay() {
        return visitDay;
    }

    public boolean isChristmasDay() {
        return 1 <= visitDay && visitDay <= 25;
    }

    public boolean isSpecialDay() {
        List<Integer> specialDay = List.of(3, 10, 17, 24, 25, 31);
        return specialDay.contains(visitDay);
    }

    public boolean isWeekday() {
        LocalDate localDate = LocalDate.of(2023, 12, visitDay);
        DayOfWeek today = localDate.getDayOfWeek();
        return List.of(SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY).contains(today);
    }

    public boolean isWeekend() {
        LocalDate localDate = LocalDate.of(2023, 12, visitDay);
        DayOfWeek today = localDate.getDayOfWeek();
        return List.of(FRIDAY, SATURDAY).contains(today);
    }

    private void validate(int visitDay) {
        if (isOutOfRange(visitDay)) {
            throw new IllegalArgumentException(INVALID_DAY_ERROR_MESSAGE);
        }
    }

    private boolean isOutOfRange(int visitDay) {
        return 31 < visitDay || visitDay < 1;
    }
}
