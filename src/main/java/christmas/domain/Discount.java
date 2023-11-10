package christmas.domain;

import static christmas.domain.EventName.CHRISTMAS_DISCOUNT;
import static christmas.domain.EventName.GIFT_EVENT;
import static christmas.domain.EventName.SPECIAL_DISCOUNT;
import static christmas.domain.EventName.WEEKDAY_DISCOUNT;
import static christmas.domain.EventName.WEEKEND_DISCOUNT;
import static christmas.domain.menu.FoodCategory.DESSERT;
import static christmas.domain.menu.FoodCategory.MAIN_COURSE;
import static christmas.domain.menu.FoodCategory.calculateFoodCountInCategory;

import christmas.domain.dto.OrderedMenuDto;
import christmas.domain.menu.Foods;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Discount {

    private VisitDay visitDay;
    private OrderedMenuDto orderedMenuDto;

    public Discount(VisitDay visitDay, OrderedMenuDto orderedMenuDto) {
        this.visitDay = visitDay;
        this.orderedMenuDto = orderedMenuDto;
    }

    public Map<EventName, Integer> toDiscountResultDto() {
        Map<EventName, Integer> discountStatistics = new HashMap<>();
        addDiscountGreaterThanZero(discountStatistics, CHRISTMAS_DISCOUNT, christmasDdayDiscount());
        addDiscountGreaterThanZero(discountStatistics, WEEKDAY_DISCOUNT, weekdayDiscount());
        addDiscountGreaterThanZero(discountStatistics, WEEKEND_DISCOUNT, weekendDiscount());
        addDiscountGreaterThanZero(discountStatistics, SPECIAL_DISCOUNT, specialDiscount());
        addGiftIfConditionSatisfied(discountStatistics);
        return discountStatistics;
    }

    private int christmasDdayDiscount() {
        if (visitDay.isChristmasDay()) {
            return -(1000 + 100 * (visitDay.getDay() - 1));
        }
        return 0;
    }

    private int specialDiscount() {
        if (visitDay.isSpecialDay()) {
            return -1000;
        }
        return 0;
    }

    private int weekdayDiscount() {
        if (visitDay.isWeekday()) {
            Set<Foods> orderedFood = orderedMenuDto.orderedMenu().keySet();
            int dessertCount = calculateFoodCountInCategory(DESSERT, orderedFood);
            return dessertCount * -2023;
        }
        return 0;
    }

    private int weekendDiscount() {
        if (visitDay.isWeekend()) {
            Set<Foods> orderedFood = orderedMenuDto.orderedMenu().keySet();
            int mainCourseCount = calculateFoodCountInCategory(MAIN_COURSE, orderedFood);
            return mainCourseCount * -2023;
        }
        return 0;
    }

    private void addDiscountGreaterThanZero(
            Map<EventName, Integer> discountStatistics, EventName name, int discountAmount
    ) {
        if (discountAmount < 0) {
            discountStatistics.put(name, discountAmount);
        }
    }

    private void addGiftIfConditionSatisfied(Map<EventName, Integer> discountStatistics) {
        if (orderedMenuDto.totalAmount() > 120000) {
            discountStatistics.put(GIFT_EVENT, -25000);
        }
    }
}

