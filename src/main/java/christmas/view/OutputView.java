package christmas.view;

import christmas.domain.DiscountResultDto;
import christmas.domain.EventName;
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

    public static void displayGift(DiscountResultDto discountResultDto) {
        System.out.println("<증정 메뉴>");
        if (discountResultDto.hasGift()) {
            System.out.println("샴페인 1개");
            return;
        }
        System.out.println("없음");
    }

    public static void displayTotalDiscounts(DiscountResultDto discountResultDto) {
        Map<EventName, Integer> discountStatistics = discountResultDto.discountResult();
        System.out.println("<혜택 내역>");
        if (discountStatistics.isEmpty()) {
            System.out.println("없음");
            return;
        }
        for (Entry<EventName, Integer> discount : discountStatistics.entrySet()) {
            System.out.println(discount.getKey().getEventName() + " : " + discount.getValue());
        }
    }

    public static void displayTodalDiscountAmount(DiscountResultDto discountResultDto) {
        System.out.println("<총혜택 금액>");
        Map<EventName, Integer> discountStatistics = discountResultDto.discountResult();
        int totalAmount = 0;
        for (int amount : discountStatistics.values()) {
            totalAmount += amount;
        }
        System.out.println(totalAmount);
    }
}
