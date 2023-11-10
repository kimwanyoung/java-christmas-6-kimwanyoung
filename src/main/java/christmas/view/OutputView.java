package christmas.view;

import christmas.domain.dto.OrderedMenuDto;
import christmas.domain.menu.Foods;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {

    private static final String WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";

    private OutputView() {
    }

    public static void displayWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public static void displayOrderedMenu(OrderedMenuDto orderedMenuDto) {
        System.out.println("<주문 메뉴>");
        Map<Foods, Integer> orderedMenu = orderedMenuDto.orderedMenu();
        for (Entry<Foods, Integer> menu : orderedMenu.entrySet()) {
            String name = menu.getKey().getName();
            Integer count = menu.getValue();
            System.out.println(name + " " + count + "개");
        }
    }

    public static void displayTotalOrderAmount(OrderedMenuDto orderedMenuDto) {
        DecimalFormat amountFormat = new DecimalFormat("###,###");
        int totalAmount = orderedMenuDto.totalAmount();
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(amountFormat.format(totalAmount));
    }
}
