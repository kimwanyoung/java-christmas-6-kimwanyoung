package christmas.domain;

import christmas.domain.dto.OrderedMenuDto;
import christmas.domain.menu.Foods;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;

public class OrderedMenu {

    private final Map<Foods, Integer> orderedMenu;

    public OrderedMenu(Map<Foods, Integer> orderedMenu) {
        this.orderedMenu = orderedMenu;
    }

    public OrderedMenuDto toOrderedMenuDto() {
        int totalAmount = 0;
        for (Entry<Foods, Integer> food : orderedMenu.entrySet()) {
            totalAmount += Foods.calculateFoodsAmount(food.getKey(), food.getValue());
        }
        return new OrderedMenuDto(Collections.unmodifiableMap(orderedMenu), totalAmount);
    }
}
