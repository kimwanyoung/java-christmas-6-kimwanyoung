package christmas.domain.discount;

import static christmas.constants.DiscountAmountConstant.DISCOUNT_PER_MENU_AMOUNT;
import static christmas.domain.menu.FoodCategory.MAIN_COURSE;
import static christmas.domain.menu.FoodCategory.calculateFoodCountInCategory;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.SATURDAY;

import christmas.domain.EventName;
import christmas.domain.VisitDay;
import christmas.domain.menu.Foods;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

public class WeekendDiscount extends DiscountPolicy {

    private static final List<DayOfWeek> WEEKEND = List.of(FRIDAY, SATURDAY);
    private final Map<Foods, Integer> orderedMenu;
    private final VisitDay visitDay;

    public WeekendDiscount(EventName eventName, Map<Foods, Integer> orderedMenu,
                           VisitDay visitDay) {
        super(eventName);
        this.orderedMenu = orderedMenu;
        this.visitDay = visitDay;
    }

    @Override
    public int calculateDiscountAmount() {
        int mainCourseCount = calculateFoodCountInCategory(MAIN_COURSE, orderedMenu);
        return mainCourseCount * DISCOUNT_PER_MENU_AMOUNT;
    }

    @Override
    public boolean isDiscountDay() {
        return visitDay.isWeekend(WEEKEND);
    }
}
