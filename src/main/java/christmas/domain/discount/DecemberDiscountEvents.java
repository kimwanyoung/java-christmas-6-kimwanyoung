package christmas.domain.discount;

import christmas.domain.EventName;
import christmas.domain.VisitDay;
import christmas.domain.dto.DiscountResultDto;
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

    public DiscountResultDto toDiscountResultDto() {
        Map<EventName, Integer> discountStatistics = new HashMap<>();
        for (DiscountPolicy discountPolicy : discounts) {
            if (discountPolicy.isDiscountDay(visitDay)) {
                addDiscountGreaterThanZero(discountStatistics, discountPolicy);
            }
        }
        int totalDiscountAmount = calculateTotalDiscountAmount(discountStatistics);
        return new DiscountResultDto(discountStatistics, totalDiscountAmount);
    }

    private int calculateTotalDiscountAmount(Map<EventName, Integer> discountStatistics) {
        int totalDiscountAmount = 0;
        for (Integer amount : discountStatistics.values()) {
            totalDiscountAmount += amount;
        }
        return totalDiscountAmount;
    }

    private void addDiscountGreaterThanZero(
            Map<EventName, Integer> discountStatistics, DiscountPolicy discountPolicy
    ) {
        int discountAmount = discountPolicy.calculateDiscountAmount(visitDay);
        EventName discountName = discountPolicy.getDiscountName();
        if (discountPolicy.calculateDiscountAmount(visitDay) < 0) {
            discountStatistics.put(discountName, discountAmount);
        }
    }
}

