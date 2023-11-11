package christmas.domain.discount;

import christmas.domain.EventName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DecemberDiscountEvents {

    private final List<DiscountPolicy> discounts;

    public DecemberDiscountEvents(DiscountPolicy... discounts) {
        this.discounts = List.of(discounts);
    }

    public Map<EventName, Integer> calculateDiscountResult() {
        Map<EventName, Integer> discountStatistics = new HashMap<>();
        for (DiscountPolicy discountPolicy : discounts) {
            if (discountPolicy.isDiscountDay()) {
                addDiscountGreaterThanZero(discountStatistics, discountPolicy);
            }
        }
        return discountStatistics;
    }

    private void addDiscountGreaterThanZero(
            Map<EventName, Integer> discountStatistics, DiscountPolicy discountPolicy
    ) {
        int discountAmount = discountPolicy.calculateDiscountAmount();
        EventName discountName = discountPolicy.getDiscountName();
        if (discountPolicy.calculateDiscountAmount() < 0) {
            System.out.println(discountPolicy.calculateDiscountAmount());
            discountStatistics.put(discountName, discountAmount);
        }
    }
}

