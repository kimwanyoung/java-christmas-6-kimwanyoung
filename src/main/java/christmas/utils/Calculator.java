package christmas.utils;

import christmas.domain.EventName;
import christmas.domain.menu.Foods;
import java.util.Map;
import java.util.Map.Entry;

public class Calculator {

    private Calculator() {
    }

    public static int calculateTotalDiscountAmount(Map<EventName, Integer> discountStatistics) {
        int totalDiscountAmount = 0;
        for (Integer amount : discountStatistics.values()) {
            totalDiscountAmount += amount;
        }
        return totalDiscountAmount;
    }

    public static int calculateTotalAmount(Map<Foods, Integer> orderedMenu) {
        int totalAmount = 0;
        for (Entry<Foods, Integer> food : orderedMenu.entrySet()) {
            totalAmount += Foods.calculateFoodsAmount(food.getKey(), food.getValue());
        }
        return totalAmount;
    }
}
