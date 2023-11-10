package christmas.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Converter {

    private static final String COMMA = ",";
    private static final String DASH = "-";

    private Converter() {
    }

    public static Map<String, Integer> convertToMenuResult(String input) {
        List<String> splitedMenus = splitByComma(input);
        Map<String, Integer> menuResult = new HashMap<>();
        for (String menu : splitedMenus) {
            List<String> menuAndAmount = splitByDash(menu);
            String menuName = menuAndAmount.get(0);
            Integer menuAmount = Integer.parseInt(menuAndAmount.get(1));
            validateDuplicateMenu(menuResult, menuName);
            menuResult.put(menuName, menuAmount);
        }
        return menuResult;
    }

    private static void validateDuplicateMenu(Map<String, Integer> menuResult, String menuName) {
        if (menuResult.containsKey(menuName)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private static List<String> splitByComma(String input) {
        return Arrays.asList(input.split(COMMA));
    }

    private static List<String> splitByDash(String menu) {
        return Arrays.asList(menu.split(DASH));
    }
}
