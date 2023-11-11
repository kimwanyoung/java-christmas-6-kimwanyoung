package christmas.domain.discount;

import static christmas.domain.menu.FoodCategory.MAIN_COURSE;
import static christmas.domain.menu.FoodCategory.calculateFoodCountInCategory;

import christmas.domain.EventName;
import christmas.domain.VisitDay;
import christmas.domain.dto.OrderedMenuDto;
import christmas.domain.menu.Foods;
import java.util.Map;

public class WeekendDiscount extends DiscountPolicy {

    private final VisitDay visitDay;
    private final OrderedMenuDto orderedMenuDto;

    public WeekendDiscount(VisitDay visitDay, EventName eventName, OrderedMenuDto orderedMenuDto) {
        super(eventName);
        this.visitDay = visitDay;
        this.orderedMenuDto = orderedMenuDto;
    }

    @Override
    public int calculateDiscountAmount() {
        Map<Foods, Integer> orderedFood = orderedMenuDto.orderedMenu();
        int mainCourseCount = calculateFoodCountInCategory(MAIN_COURSE, orderedFood);
        return mainCourseCount * -2023;
    }

    @Override
    public boolean isDiscountDay() {
        return visitDay.isWeekend();
    }
}
