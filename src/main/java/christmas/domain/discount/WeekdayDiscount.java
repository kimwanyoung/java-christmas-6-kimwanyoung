package christmas.domain.discount;

import static christmas.constants.DiscountAmountConstant.DISCOUNT_PER_MENU_AMOUNT;
import static christmas.domain.menu.FoodCategory.DESSERT;
import static christmas.domain.menu.FoodCategory.calculateFoodCountInCategory;

import christmas.domain.EventName;
import christmas.domain.VisitDay;
import christmas.domain.dto.OrderedMenuDto;
import christmas.domain.menu.Foods;
import java.util.Map;

public class WeekdayDiscount extends DiscountPolicy {
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
        return visitDay.isWeekday();
    }
}
