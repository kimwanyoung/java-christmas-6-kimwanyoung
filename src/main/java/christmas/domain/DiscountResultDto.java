package christmas.domain;

import java.util.Map;

public record DiscountResultDto(Map<EventName, Integer> discountResult, boolean hasGift) {
}
