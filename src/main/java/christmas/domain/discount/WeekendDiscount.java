package christmas.domain.discount;

import static christmas.constants.DiscountAmountConstant.DISCOUNT_PER_MENU_AMOUNT;
import static christmas.domain.menu.FoodCategory.MAIN_COURSE;
import static christmas.domain.menu.FoodCategory.calculateFoodCountInCategory;

import christmas.domain.EventName;
import christmas.domain.VisitDay;
import christmas.domain.dto.OrderedMenuDto;
import christmas.domain.menu.Foods;
import java.util.Map;

public class WeekendDiscount extends DiscountPolicy {

    private final OrderedMenuDto orderedMenuDto;

    public WeekendDiscount(EventName eventName, OrderedMenuDto orderedMenuDto) {
        super(eventName);
        this.orderedMenuDto = orderedMenuDto;
    }

    @Override
    public int calculateDiscountAmount(VisitDay visitDay) {
        Map<Foods, Integer> orderedFood = orderedMenuDto.orderedMenu();
        int mainCourseCount = calculateFoodCountInCategory(MAIN_COURSE, orderedFood);
        return mainCourseCount * DISCOUNT_PER_MENU_AMOUNT;
    }

    @Override
    public boolean isDiscountDay(VisitDay visitDay) {
        return visitDay.isWeekend();
    }
}