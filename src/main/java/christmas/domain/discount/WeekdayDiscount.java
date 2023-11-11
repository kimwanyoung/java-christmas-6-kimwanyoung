package christmas.domain.discount;

import static christmas.constants.DiscountAmountConstant.DISCOUNT_PER_MENU_AMOUNT;
import static christmas.domain.menu.FoodCategory.DESSERT;
import static christmas.domain.menu.FoodCategory.calculateFoodCountInCategory;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;

import christmas.domain.EventName;
import christmas.domain.VisitDay;
import christmas.domain.menu.Foods;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

public class WeekdayDiscount extends DiscountPolicy {

    private static final List<DayOfWeek> WEEKDAY = List.of(SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
            THURSDAY);
    private final Map<Foods, Integer> orderedMenu;

    public WeekdayDiscount(EventName eventName, Map<Foods, Integer> orderedMenu) {
        super(eventName);
        this.orderedMenu = orderedMenu;
    }

    @Override
    public int calculateDiscountAmount(VisitDay visitDay) {
        int dessertCount = calculateFoodCountInCategory(DESSERT, orderedMenu);
        return dessertCount * DISCOUNT_PER_MENU_AMOUNT;
    }

    @Override
    public boolean isDiscountDay(VisitDay visitDay) {
        return visitDay.isWeekday(WEEKDAY);
    }
}
