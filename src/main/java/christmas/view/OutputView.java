package christmas.view;

import static christmas.constants.DiscountInfoConstants.DISCOUNT_DETAILS;
import static christmas.constants.DiscountInfoConstants.EVENT_BADGE;
import static christmas.constants.DiscountInfoConstants.FINAL_PAYMENT_AMOUNT;
import static christmas.constants.DiscountInfoConstants.GIFT_MENU;
import static christmas.constants.DiscountInfoConstants.ORDER_MENU;
import static christmas.constants.DiscountInfoConstants.ORDER_TOTAL_AMOUNT;
import static christmas.constants.DiscountInfoConstants.TOTAL_DISCOUNT_AMOUNT;
import static christmas.domain.menu.Foods.CHAMPAGNE;
import static christmas.domain.menu.Foods.calculateFoodsAmount;

import christmas.domain.Badge;
import christmas.domain.DiscountResult;
import christmas.domain.EventName;
import christmas.domain.dto.OrderedMenuDto;
import christmas.domain.menu.Foods;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {

    private static final String WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String EVENT_PREVIEW_MESSAGE = "12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private static final String WON = "%s원\n";
    private static final String DISCOUNT_MESSAGE = "%s: ";
    private static final String NOTHING = "없음";
    private static final String MENU_INFO = "%s %d개\n";
    private static final DecimalFormat AMOUNT_FORMAT = new DecimalFormat("###,###");

    private OutputView() {
    }

    public static void displayWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }

    public static void displayEventPreviewMessage() {
        System.out.println(EVENT_PREVIEW_MESSAGE);
    }

    public static void displayOrderedMenu(OrderedMenuDto orderedMenuDto) {
        System.out.println(ORDER_MENU);
        Map<Foods, Integer> orderedMenu = orderedMenuDto.orderedMenu();
        for (Entry<Foods, Integer> menu : orderedMenu.entrySet()) {
            String name = menu.getKey().getName();
            Integer count = menu.getValue();
            System.out.printf(MENU_INFO, name, count);
        }
    }

    public static void displayTotalOrderAmount(OrderedMenuDto orderedMenuDto) {

        int totalAmount = orderedMenuDto.totalAmount();
        System.out.println(ORDER_TOTAL_AMOUNT);
        System.out.println(formattedAmount(totalAmount));
    }

    public static void displayGift(DiscountResult discountResult) {
        System.out.println(GIFT_MENU);
        if (discountResult.getHasGift()) {
            System.out.printf(MENU_INFO, CHAMPAGNE.getName(), 1);
            return;
        }
        System.out.println(NOTHING);
    }

    public static void displayTotalDiscounts(DiscountResult discountResult) {
        Map<EventName, Integer> discountStatistics = discountResult.getDiscountResult();
        System.out.println(DISCOUNT_DETAILS);
        if (discountStatistics.isEmpty()) {
            System.out.println(NOTHING);
            return;
        }
        for (Entry<EventName, Integer> discount : discountStatistics.entrySet()) {
            String eventName = discount.getKey().getEventName();
            int discountAmount = discount.getValue();
            System.out.printf(DISCOUNT_MESSAGE + WON, eventName, formattedAmount(discountAmount));
        }
    }

    public static void displayTotalDiscountAmount(DiscountResult discountResult) {
        System.out.println(TOTAL_DISCOUNT_AMOUNT);
        int totalDiscountAmount = discountResult.calculateTotalDiscountAmount();
        System.out.printf(WON, formattedAmount(totalDiscountAmount));
    }

    public static void displayFinalPayment(
            OrderedMenuDto orderedMenuDto, DiscountResult discountResult
    ) {
        System.out.println(FINAL_PAYMENT_AMOUNT);
        int totalAmount = orderedMenuDto.totalAmount();
        int discountAmount = discountResult.calculateTotalDiscountAmount();
        if (discountResult.getHasGift()) {
            int finalPayment = totalAmount + discountAmount + calculateFoodsAmount(CHAMPAGNE, 1);
            System.out.printf(WON, formattedAmount(finalPayment));
            return;
        }
        System.out.printf(WON, formattedAmount(totalAmount - discountAmount));
    }

    public static void displayBadge(DiscountResult discountResult) {
        System.out.println(EVENT_BADGE);
        int totalDiscountAmount = discountResult.calculateTotalDiscountAmount();
        System.out.println(Badge.calculateBadge(totalDiscountAmount));
    }

    private static String formattedAmount(int amount) {
        return AMOUNT_FORMAT.format(amount);
    }
}
