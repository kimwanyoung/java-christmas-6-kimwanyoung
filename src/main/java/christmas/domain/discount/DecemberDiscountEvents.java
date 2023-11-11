package christmas.domain.discount;

import christmas.domain.EventName;
import christmas.domain.VisitDay;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DecemberDiscountEvents {

    private final List<DiscountPolicy> discounts;
    private final VisitDay visitDay;

    public DecemberDiscountEvents(VisitDay visitDay, DiscountPolicy... discounts) {
        this.discounts = List.of(discounts);
        this.visitDay = visitDay;
    }

    public Map<EventName, Integer> calculateDiscountResult() {
        Map<EventName, Integer> discountStatistics = new HashMap<>();
        for (DiscountPolicy discountPolicy : discounts) {
            if (discountPolicy.isDiscountDay(visitDay)) {
                addDiscountGreaterThanZero(discountStatistics, discountPolicy);
            }
        }
        return discountStatistics;
    }

    private void addDiscountGreaterThanZero(
            Map<EventName, Integer> discountStatistics, DiscountPolicy discountPolicy
    ) {
        int discountAmount = discountPolicy.calculateDiscountAmount(visitDay);
        EventName discountName = discountPolicy.getDiscountName();
        if (discountPolicy.calculateDiscountAmount(visitDay) < 0) {
            System.out.println(discountPolicy.calculateDiscountAmount(visitDay));
            discountStatistics.put(discountName, discountAmount);
        }
    }
}

