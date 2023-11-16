package christmas.domain.dto;

import christmas.domain.EventName;
import java.util.Map;

public record DiscountResultDto(Map<EventName, Integer> discountResult, int totalDiscountAmount) {
}
