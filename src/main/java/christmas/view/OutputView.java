package christmas.view;

public class OutputView {

    private static final String WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";

    private OutputView() {
    }

    public static void displayWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }
}
