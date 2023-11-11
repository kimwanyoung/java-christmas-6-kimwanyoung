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
import christmas.domain.dto.OrderedMenuDto;
import christmas.domain.menu.Foods;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

public class WeekdayDiscount extends DiscountPolicy {

    private static final List<DayOfWeek> WEEKDAY = List.of(SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
            THURSDAY);
    private final OrderedMenuDto orderedMenuDto;

    public WeekdayDiscount(EventName eventName, OrderedMenuDto orderedMenuDto) {
        super(eventName);
        this.orderedMenuDto = orderedMenuDto;
    }

    @Override
    public int calculateDiscountAmount(VisitDay visitDay) {
        Map<Foods, Integer> orderedFood = orderedMenuDto.orderedMenu();
        int dessertCount = calculateFoodCountInCategory(DESSERT, orderedFood);
        return dessertCount * DISCOUNT_PER_MENU_AMOUNT;
    }

    @Override
    public boolean isDiscountDay(VisitDay visitDay) {
        return visitDay.isWeekday(WEEKDAY);
    }
}
