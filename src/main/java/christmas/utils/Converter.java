package christmas.utils;

import static christmas.domain.menu.Foods.convertStringToFoods;
import static christmas.view.InputView.INVALID_MENU_ORDER_ERROR_MESSAGE;

import christmas.domain.menu.Foods;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Converter {

    private static final String COMMA = ",";
    private static final String DASH = "-";

    private Converter() {
    }

    public static Map<Foods, Integer> convertToMenuResult(String input) {
        List<String> splitedMenus = splitByComma(input);
        Map<Foods, Integer> menuResult = new HashMap<>();
        for (String menu : splitedMenus) {
            List<String> menuAndAmount = splitByDash(menu);
            Foods menuName = convertStringToFoods(menuAndAmount.get(0));
            Integer menuAmount = Integer.parseInt(menuAndAmount.get(1));
            validateMenu(menuResult, menuName);
            menuResult.put(menuName, menuAmount);
        }
        return menuResult;
    }

    private static void validateMenu(Map<Foods, Integer> menuResult, Foods menuName) {
        if (menuName == null) {
            throw new IllegalArgumentException(INVALID_MENU_ORDER_ERROR_MESSAGE);
        }
        if (hasDuplicateMenu(menuResult, menuName)) {
            throw new IllegalArgumentException(INVALID_MENU_ORDER_ERROR_MESSAGE);
        }
    }

    private static boolean hasDuplicateMenu(Map<Foods, Integer> menuResult, Foods menuName) {
        return menuResult.containsKey(menuName);
    }

    private static List<String> splitByComma(String input) {
        return Arrays.asList(input.split(COMMA));
    }

    private static List<String> splitByDash(String menu) {
        return Arrays.asList(menu.split(DASH));
    }
}
