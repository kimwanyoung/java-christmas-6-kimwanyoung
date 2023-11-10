package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String VISIT_DAY_INPUT_MESSAGE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String INVALID_DAY_ERROR_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    private InputView() {
    }

    public static int getVisitDayFromInput() {
        try {
            System.out.println(VISIT_DAY_INPUT_MESSAGE);
            String visitDay = read();
            return Integer.parseInt(visitDay);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(INVALID_DAY_ERROR_MESSAGE);
        }
    }

    private static String read() {
        return Console.readLine().trim();
    }
}
