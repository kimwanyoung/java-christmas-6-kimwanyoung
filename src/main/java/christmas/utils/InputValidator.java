package christmas.utils;

import static christmas.constants.ExceptionMessage.INVALID_MENU_ORDER_ERROR_MESSAGE;

public class InputValidator {

    private InputValidator() {
    }

    public static void validateMenuFormat(String input, String pattern) {
        if (!input.matches(pattern)) {
            throw new IllegalArgumentException(INVALID_MENU_ORDER_ERROR_MESSAGE);
        }
    }

    public static void validateEmptyInput(String input, String errorMessage) {
        if (input.isEmpty() || input.isBlank()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
