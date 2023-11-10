package christmas.domain;

import java.util.Collections;
import java.util.Map;

public class DiscountResult {

    private final Map<EventName, Integer> discountResult;
    private final boolean hasGift;

    public DiscountResult(Map<EventName, Integer> discountResult, boolean hasGift) {
        this.discountResult = discountResult;
        this.hasGift = hasGift;
    }

    public int calculateTotalDiscountAmount() {
        int totalDiscountAmount = 0;
        for (int amount : discountResult.values()) {
            totalDiscountAmount += amount;
        }
        return totalDiscountAmount;
    }

    public Map<EventName, Integer> getDiscountResult() {
        return Collections.unmodifiableMap(discountResult);
    }

    public boolean getHasGift() {
        return hasGift;
    }
}
