package christmas.domain;

import static christmas.constants.EventDayConstants.EVENT_END_DAY;
import static christmas.constants.EventDayConstants.EVENT_START_DAY;
import static christmas.constants.ExceptionMessage.INVALID_DAY_ERROR_MESSAGE;
import static christmas.utils.Converter.convertToDayOfWeek;

import java.time.DayOfWeek;
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

    public boolean isChristmasDay(int eventStartDay, int christmasDay) {
        return eventStartDay <= visitDay && visitDay <= christmasDay;
    }

    public boolean isSpecialDay(List<Integer> specialDays) {
        return specialDays.contains(visitDay);
    }

    public boolean isWeekday(List<DayOfWeek> weekday) {
        return weekday.contains(convertToDayOfWeek(visitDay));
    }

    public boolean isWeekend(List<DayOfWeek> weekend) {
        return weekend.contains(convertToDayOfWeek(visitDay));
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
