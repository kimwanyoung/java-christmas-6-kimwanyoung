package christmas.domain.discount;

import static christmas.constants.DiscountAmountConstant.MIN_AMOUNT_FOR_DISCOUNT;

import christmas.domain.EventName;
import christmas.domain.VisitDay;
import christmas.domain.dto.DiscountResultDto;
import java.util.Collections;
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

    public DiscountResultDto toDiscountResultDto(int totalOrderAmount) {
        Map<EventName, Integer> discountStatistics = new HashMap<>();
        addDiscountPolicySatisfiedCondition(discountStatistics);
        int totalDiscountAmount = calculateTotalDiscountAmount(discountStatistics);

        if (totalOrderAmount < MIN_AMOUNT_FOR_DISCOUNT) {
            return new DiscountResultDto(new HashMap<>(), 0);
        }
        return new DiscountResultDto(
                Collections.unmodifiableMap(discountStatistics), totalDiscountAmount
        );
    }

    private void addDiscountPolicySatisfiedCondition(Map<EventName, Integer> discountStatistics) {
        for (DiscountPolicy discountPolicy : discounts) {
            if (discountPolicy.isDiscountDay(visitDay)) {
                addDiscountMoreThanZero(discountStatistics, discountPolicy);
            }
        }
    }

    private int calculateTotalDiscountAmount(Map<EventName, Integer> discountStatistics) {
        int totalDiscountAmount = 0;
        for (Integer amount : discountStatistics.values()) {
            totalDiscountAmount += amount;
        }
        return totalDiscountAmount;
    }

    private void addDiscountMoreThanZero(
            Map<EventName, Integer> discountStatistics, DiscountPolicy discountPolicy
    ) {
        int discountAmount = discountPolicy.calculateDiscountAmount(visitDay);
        EventName discountName = discountPolicy.getDiscountName();
        if (discountPolicy.calculateDiscountAmount(visitDay) < 0) {
            discountStatistics.put(discountName, discountAmount);
        }
    }
}

