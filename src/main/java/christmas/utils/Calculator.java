package christmas.utils;

import christmas.domain.EventName;
import java.util.Map;

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
}
