package christmas.view;

import christmas.domain.EventName;
import christmas.domain.EventStatistics;
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

    public static void displayGift(EventStatistics eventStatistics) {
        System.out.println("<증정 메뉴>");
        if (eventStatistics.hasGift()) {
            System.out.println("샴페인 1개");
            return;
        }
        System.out.println("없음");
    }

    public static void displayTotalDiscounts(Map<EventName, Integer> discountStatistics) {
        System.out.println("<혜택 내역>");
        if (discountStatistics.isEmpty()) {
            System.out.println("없음");
            return;
        }
        for (Entry<EventName, Integer> discount : discountStatistics.entrySet()) {
            System.out.println(discount.getKey().getEventName() + " : " + discount.getValue());
        }
    }
}
