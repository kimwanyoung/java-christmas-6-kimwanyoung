package christmas.domain;

import static christmas.constants.ExceptionMessage.INVALID_MENU_ORDER_ERROR_MESSAGE;
import static christmas.domain.menu.FoodCategory.BEVERAGE;
import static christmas.domain.menu.FoodCategory.getCategoriesOrderedFoods;

import christmas.domain.menu.FoodCategory;
import christmas.domain.menu.Foods;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OrderedMenu {

    private static final int MAX_ORDER_COUNT = 20;
    private final Map<Foods, Integer> orderedMenu;

    public OrderedMenu(Map<Foods, Integer> orderedMenu) {
        validateOverTwenty(orderedMenu.values());
        validateOnlyBeverage(orderedMenu);
        this.orderedMenu = orderedMenu;
    }

    public Map<Foods, Integer> getOrderedMenu() {
        return Collections.unmodifiableMap(orderedMenu);
    }

    public int calculateTotalAmount() {
        int totalAmount = 0;
        for (Entry<Foods, Integer> food : orderedMenu.entrySet()) {
            totalAmount += Foods.calculateFoodsAmount(food.getKey(), food.getValue());
        }
        return totalAmount;
    }

    private static void validateOverTwenty(Collection<Integer> orderMenuCounts) {
        if (calculateOrderFoodCounts(orderMenuCounts) > MAX_ORDER_COUNT) {
            throw new IllegalArgumentException(INVALID_MENU_ORDER_ERROR_MESSAGE);
        }
    }

    private void validateOnlyBeverage(Map<Foods, Integer> orderedMenu) {
        List<FoodCategory> orderFoodCategory = getCategoriesOrderedFoods(orderedMenu);
        if (orderFoodCategory.size() == 1 && orderFoodCategory.contains(BEVERAGE)) {
            throw new IllegalArgumentException(INVALID_MENU_ORDER_ERROR_MESSAGE);
        }
    }

    private static int calculateOrderFoodCounts(Collection<Integer> orderMenuCounts) {
        int count = 0;
        for (int menuCount : orderMenuCounts) {
            count += menuCount;
        }
        return count;
    }
}
