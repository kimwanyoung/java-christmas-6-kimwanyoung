package christmas.domain.dto;

import christmas.domain.menu.Foods;
import java.util.Map;

public record OrderedMenuDto(Map<Foods, Integer> orderedMenu, int totalAmount) {
}
