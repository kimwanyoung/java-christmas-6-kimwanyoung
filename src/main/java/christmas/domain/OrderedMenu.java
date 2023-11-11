package christmas.domain;

import static christmas.constants.ExceptionMessage.INVALID_MENU_ORDER_ERROR_MESSAGE;
import static christmas.domain.menu.FoodCategory.BEVERAGE;
import static christmas.domain.menu.FoodCategory.getCurrentFoodCategory;

import christmas.domain.menu.FoodCategory;
import christmas.domain.menu.Foods;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class OrderedMenu {

    private final Map<Foods, Integer> orderedMenu;

    public OrderedMenu(Map<Foods, Integer> orderedMenu) {
        validateOnlyBeverage(orderedMenu);
        this.orderedMenu = orderedMenu;
    }

    public Map<Foods, Integer> getOrderedMenu() {
        return Collections.unmodifiableMap(orderedMenu);
    }

    private void validateOnlyBeverage(Map<Foods, Integer> orderedMenu) {
        List<FoodCategory> orderFoodCategory = getCurrentFoodCategory(orderedMenu);
        if (orderFoodCategory.size() == 1 && orderFoodCategory.contains(BEVERAGE)) {
            throw new IllegalArgumentException(INVALID_MENU_ORDER_ERROR_MESSAGE);
        }
    }
}
