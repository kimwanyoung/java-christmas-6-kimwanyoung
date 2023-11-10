package christmas.domain;

import static christmas.domain.menu.FoodCategory.DESSERT;
import static christmas.domain.menu.FoodCategory.MAIN_COURSE;
import static christmas.domain.menu.FoodCategory.calculateFoodCountInCategory;

import christmas.domain.dto.OrderedMenuDto;
import christmas.domain.menu.Foods;
import java.util.Set;

public class Discount {

    private VisitDay visitDay;
    private OrderedMenuDto orderedMenuDto;

    public Discount(VisitDay visitDay, OrderedMenuDto orderedMenuDto) {
        this.visitDay = visitDay;
        this.orderedMenuDto = orderedMenuDto;
    }

    private int christmasDdayDiscount() {
        if (visitDay.isChristmasDay()) {
            return -1000 + 100 * (visitDay.getDay() - 1);
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
}

