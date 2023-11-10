package christmas.domain;

import static christmas.constants.EventDayConstants.CHRISTMAS_DAY;
import static christmas.constants.EventDayConstants.EVENT_END_DAY;
import static christmas.constants.EventDayConstants.EVENT_START_DAY;
import static christmas.constants.EventDayConstants.SPECIAL_DAYS;
import static christmas.constants.ExceptionMessage.INVALID_DAY_ERROR_MESSAGE;
import static christmas.utils.Converter.convertToDayOfWeek;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;

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
        return EVENT_START_DAY <= visitDay && visitDay <= CHRISTMAS_DAY;
    }

    public boolean isSpecialDay() {
        return SPECIAL_DAYS.contains(visitDay);
    }

    public boolean isWeekday() {
        return List.of(SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY)
                .contains(convertToDayOfWeek(visitDay));
    }

    public boolean isWeekend() {
        return List.of(FRIDAY, SATURDAY).contains(convertToDayOfWeek(visitDay));
    }

    private void validate(int visitDay) {
        if (isOutOfRange(visitDay)) {
            throw new IllegalArgumentException(INVALID_DAY_ERROR_MESSAGE);
        }
    }

    private boolean isOutOfRange(int visitDay) {
        return EVENT_END_DAY < visitDay || visitDay < EVENT_START_DAY;
    }
}
