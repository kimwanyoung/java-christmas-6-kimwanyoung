package christmas.domain.discount;

import static christmas.constants.DiscountAmountConstant.FREE_GIFT_AMOUNT;
import static christmas.constants.DiscountAmountConstant.MIN_AMOUNT_FOR_DISCOUNT;
import static christmas.constants.DiscountAmountConstant.MIN_AMOUNT_FOR_GIFT;
import static christmas.domain.EventName.GIFT_EVENT;

import christmas.domain.EventName;
import christmas.domain.dto.DiscountResultDto;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DecemberDiscountEvents {

    private final List<DiscountPolicy> discounts;

    public DecemberDiscountEvents(DiscountPolicy... discounts) {
        this.discounts = List.of(discounts);
    }

    public DiscountResultDto toDiscountResultDto(int totalOrderAmount) {
        if (totalOrderAmount < MIN_AMOUNT_FOR_DISCOUNT) {
            return new DiscountResultDto(new HashMap<>(), 0);
        }

        Map<EventName, Integer> discountStatistics = calculateDiscountStatistics(totalOrderAmount);
        int totalDiscountAmount = calculateTotalDiscountAmount(discountStatistics);

        return new DiscountResultDto(
                Collections.unmodifiableMap(discountStatistics),
                totalDiscountAmount
        );
    }

    private Map<EventName, Integer> calculateDiscountStatistics(int totalOrderAmount) {
        Map<EventName, Integer> discountStatistics = new HashMap<>();
        addDiscountPolicySatisfiedCondition(discountStatistics);
        addFreeGiftSatisfiedCondition(discountStatistics, totalOrderAmount);
        return discountStatistics;
    }

    private void addDiscountPolicySatisfiedCondition(Map<EventName, Integer> discountStatistics) {
        for (DiscountPolicy discountPolicy : discounts) {
            if (discountPolicy.isDiscountDay()) {
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
        int discountAmount = discountPolicy.calculateDiscountAmount();
        EventName discountName = discountPolicy.getDiscountName();
        if (discountAmount < 0) {
            discountStatistics.put(discountName, discountAmount);
        }
    }

    private void addFreeGiftSatisfiedCondition(Map<EventName, Integer> discountStatistics,
                                               int totalOrderAmount) {
        if (totalOrderAmount >= MIN_AMOUNT_FOR_GIFT) {
            discountStatistics.put(GIFT_EVENT, FREE_GIFT_AMOUNT);
        }
    }
}

