package christmas.domain.menu;

import static christmas.domain.menu.Foods.BARBECUE_RIB;
import static christmas.domain.menu.Foods.CAESAR_SALAD;
import static christmas.domain.menu.Foods.CHAMPAGNE;
import static christmas.domain.menu.Foods.CHOCOLATE_CAKE;
import static christmas.domain.menu.Foods.CHRISTMAS_PASTA;
import static christmas.domain.menu.Foods.ICECREAM;
import static christmas.domain.menu.Foods.MUSHROOM_SOUP;
import static christmas.domain.menu.Foods.RED_WINE;
import static christmas.domain.menu.Foods.SEAFOOD_PASTA;
import static christmas.domain.menu.Foods.TAPAS;
import static christmas.domain.menu.Foods.T_BONE_STEAK;
import static christmas.domain.menu.Foods.ZERO_COKE;

import christmas.domain.OrderedMenu;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public enum FoodCategory {
    APPETIZER("애피타이저", List.of(MUSHROOM_SOUP, TAPAS, CAESAR_SALAD)), MAIN_COURSE("메인",
            List.of(T_BONE_STEAK, BARBECUE_RIB, SEAFOOD_PASTA, CHRISTMAS_PASTA)), DESSERT("디저트",
            List.of(CHOCOLATE_CAKE, ICECREAM)), BEVERAGE("음료",
            List.of(ZERO_COKE, RED_WINE, CHAMPAGNE));

    private final String name;
    private final List<Foods> foods;

    FoodCategory(String name, List<Foods> foods) {
        this.name = name;
        this.foods = foods;
    }

    public static int calculateFoodCountInCategory(FoodCategory foodCategory,
                                                   OrderedMenu orderedMenu) {
        int count = 0;
        for (Foods food : foodCategory.foods) {
            count += orderedMenu.getCountFoodInCategory(food);
        }
        return count;
    }

    public static List<FoodCategory> getCategoriesOrderedFoods(Map<Foods, Integer> orderMenu) {
        List<FoodCategory> foodCategories = new ArrayList<>();
        for (FoodCategory category : FoodCategory.values()) {
            if (isContainFood(category, orderMenu)) {
                foodCategories.add(category);
            }
        }
        return foodCategories;
    }

    private static boolean isContainFood(FoodCategory category, Map<Foods, Integer> orderMenu) {
        return findCategoryContainingFood(category, orderMenu) != null;
    }

    private static FoodCategory findCategoryContainingFood(FoodCategory category,
                                                           Map<Foods, Integer> orderMenu) {
        for (Foods food : orderMenu.keySet()) {
            if (category.foods.contains(food)) {
                return category;
            }
        }
        return null;
    }
}
