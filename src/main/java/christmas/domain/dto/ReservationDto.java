package christmas.domain.dto;

import christmas.domain.menu.Foods;
import java.util.Map;

public record ReservationDto(int visitDay, Map<Foods, Integer> orderedMenu, int totalOrderAmount) {
}
