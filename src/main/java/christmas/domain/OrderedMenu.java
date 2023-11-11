package christmas.domain;

import christmas.domain.menu.Foods;
import java.util.Collections;
import java.util.Map;

public class OrderedMenu {

    private final Map<Foods, Integer> orderedMenu;

    public OrderedMenu(Map<Foods, Integer> orderedMenu) {
        this.orderedMenu = orderedMenu;
    }

    public Map<Foods, Integer> getOrderedMenu() {
        return Collections.unmodifiableMap(orderedMenu);
    }
}
