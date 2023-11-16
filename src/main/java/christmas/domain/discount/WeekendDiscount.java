package christmas.domain.discount;

import static christmas.constants.DiscountAmountConstant.DISCOUNT_PER_MENU_AMOUNT;
import static christmas.domain.menu.FoodCategory.MAIN_COURSE;
import static christmas.domain.menu.FoodCategory.calculateFoodCountInCategory;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.SATURDAY;

import christmas.domain.EventName;
import christmas.domain.OrderedMenu;
import christmas.domain.VisitDay;
import java.time.DayOfWeek;
import java.util.List;

public class WeekendDiscount extends DiscountPolicy {

    private static final List<DayOfWeek> WEEKEND = List.of(FRIDAY, SATURDAY);
    private final OrderedMenu orderedMenu;
    private final VisitDay visitDay;

    public WeekendDiscount(EventName eventName, OrderedMenu orderedMenu, VisitDay visitDay) {
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
