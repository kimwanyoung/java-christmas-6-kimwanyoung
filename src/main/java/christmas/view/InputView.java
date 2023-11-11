package christmas.view;

import static christmas.constants.ExceptionMessage.INVALID_DAY_ERROR_MESSAGE;
import static christmas.constants.ExceptionMessage.INVALID_MENU_ORDER_ERROR_MESSAGE;
import static christmas.utils.InputValidator.validateEmptyInput;
import static christmas.utils.InputValidator.validateMenuFormat;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.menu.Foods;
import christmas.utils.Converter;
import java.util.Map;

public class InputView {

    private static final String VISIT_DAY_INPUT_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String ORDER_MENU_INPUT_MESSAGE = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String MENU_INPUT_FORMAT = "^[가-힣]+-[1-9]\\d*((,[가-힣]+-[1-9]\\d*)+)?$";

    private InputView() {
    }

    public static int getVisitDayFromInput() {
        try {
            System.out.println(VISIT_DAY_INPUT_MESSAGE);
            String visitDay = read();
            validateEmptyInput(visitDay, INVALID_DAY_ERROR_MESSAGE);
            return Integer.parseInt(visitDay);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(INVALID_DAY_ERROR_MESSAGE);
        }
    }

    public static Map<Foods, Integer> getMenuAndCountFromInput() {
        try {
            System.out.println(ORDER_MENU_INPUT_MESSAGE);
            String orderMenu = read();
            validateEmptyInput(orderMenu, INVALID_MENU_ORDER_ERROR_MESSAGE);
            validateMenuFormat(orderMenu, MENU_INPUT_FORMAT);
            return Converter.convertToMenuResult(orderMenu);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(INVALID_MENU_ORDER_ERROR_MESSAGE);
        }
    }

    private static String read() {
        return Console.readLine().trim();
    }
}
