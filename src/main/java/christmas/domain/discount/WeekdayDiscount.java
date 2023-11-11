package christmas.domain.discount;

import static christmas.domain.menu.FoodCategory.DESSERT;
import static christmas.domain.menu.FoodCategory.calculateFoodCountInCategory;

import christmas.domain.EventName;
import christmas.domain.VisitDay;
import christmas.domain.dto.OrderedMenuDto;
import christmas.domain.menu.Foods;
import java.util.Map;

public class WeekdayDiscount extends DiscountPolicy {

    private final VisitDay visitDay;
    private final OrderedMenuDto orderedMenuDto;

    public WeekdayDiscount(VisitDay visitDay, EventName eventName, OrderedMenuDto orderedMenuDto) {
        super(eventName);
        this.visitDay = visitDay;
        this.orderedMenuDto = orderedMenuDto;
    }

    @Override
    public int calculateDiscountAmount() {
        Map<Foods, Integer> orderedFood = orderedMenuDto.orderedMenu();
        int dessertCount = calculateFoodCountInCategory(DESSERT, orderedFood);
        return dessertCount * -2023;
    }

    @Override
    public boolean isDiscountDay() {
        return visitDay.isWeekday();
    }
}
