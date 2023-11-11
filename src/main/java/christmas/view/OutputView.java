package christmas.view;

import static christmas.constants.DiscountInfoConstants.DISCOUNT_DETAILS;
import static christmas.constants.DiscountInfoConstants.EVENT_BADGE;
import static christmas.constants.DiscountInfoConstants.FINAL_PAYMENT_AMOUNT;
import static christmas.constants.DiscountInfoConstants.GIFT_MENU;
import static christmas.constants.DiscountInfoConstants.ORDER_MENU;
import static christmas.constants.DiscountInfoConstants.ORDER_TOTAL_AMOUNT;
import static christmas.constants.DiscountInfoConstants.TOTAL_DISCOUNT_AMOUNT;
import static christmas.domain.EventName.GIFT_EVENT;
import static christmas.domain.menu.Foods.CHAMPAGNE;
import static christmas.domain.menu.Foods.calculateFoodsAmount;

import christmas.domain.Badge;
import christmas.domain.EventName;
import christmas.domain.dto.DiscountResultDto;
import christmas.domain.dto.OrderedMenuDto;
import christmas.domain.menu.Foods;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {

    private final String WELCOME_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private final String EVENT_PREVIEW_MESSAGE = "12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private final String WON = "%s원\n";
    private final String DISCOUNT_MESSAGE = "%s: ";
    private final String NOTHING = "없음";
    private final String MENU_INFO = "%s %d개\n";
    private final DecimalFormat AMOUNT_FORMAT = new DecimalFormat("###,###");

    private final Printer printer;

    public OutputView(Printer printer) {
        this.printer = printer;
    }

    public void displayWelcomeMessage() {
        printer.println(WELCOME_MESSAGE);
    }

    public void displayEventPreviewMessage() {
        printer.println(EVENT_PREVIEW_MESSAGE);
    }

    public void displayOrderedMenu(OrderedMenuDto orderedMenuDto) {
        printer.println(ORDER_MENU);
        printMenuItems(orderedMenuDto.orderedMenu());
    }

    public void displayTotalOrderAmount(OrderedMenuDto orderedMenuDto) {
        printer.println(ORDER_TOTAL_AMOUNT);
        printer.println(formattedAmount(orderedMenuDto.totalAmount()));
    }

    public void displayGift(DiscountResultDto discountResultDto) {
        printer.println(GIFT_MENU);
        printGift(discountResultDto.discountResult());
    }

    public void displayTotalDiscounts(DiscountResultDto discountResultDto) {
        Map<EventName, Integer> discountStatistics = discountResultDto.discountResult();
        printer.println(DISCOUNT_DETAILS);
        printDiscountStatistics(discountStatistics);
    }

    public void displayTotalDiscountAmount(DiscountResultDto discountResultDto) {
        printer.println(TOTAL_DISCOUNT_AMOUNT);
        int discountAmount = discountResultDto.totalDiscountAmount();
        printer.printf(WON, formattedAmount(discountAmount));
    }

    public void displayFinalPayment(
            OrderedMenuDto orderedMenuDto, DiscountResultDto discountResultDto
    ) {
        printer.println(FINAL_PAYMENT_AMOUNT);
        Map<EventName, Integer> discountResult = discountResultDto.discountResult();
        int finalPayment = orderedMenuDto.totalAmount() + discountResultDto.totalDiscountAmount();
        printPayment(discountResult, finalPayment);
    }

    public void displayBadge(DiscountResultDto discountResultDto) {
        printer.println(EVENT_BADGE);
        int discountAmount = discountResultDto.totalDiscountAmount();
        String badge = Badge.calculateBadge(discountAmount);
        printBadge(badge);
    }

    private void printMenuItems(Map<Foods, Integer> orderedMenu) {
        for (Entry<Foods, Integer> menu : orderedMenu.entrySet()) {
            String name = menu.getKey().getName();
            Integer count = menu.getValue();
            printer.printf(MENU_INFO, name, count);
        }
    }

    private void printGift(Map<EventName, Integer> discountResults) {
        if (discountResults.containsKey(GIFT_EVENT)) {
            printer.printf(MENU_INFO, CHAMPAGNE.getName(), 1);
            return;
        }
        printer.println(NOTHING);
    }

    private void printDiscountStatistics(Map<EventName, Integer> discountStatistics) {
        if (discountStatistics.isEmpty()) {
            printer.println(NOTHING);
            return;
        }
        for (Entry<EventName, Integer> discount : discountStatistics.entrySet()) {
            printDiscounts(discount);
        }
    }

    private void printDiscounts(Entry<EventName, Integer> discount) {
        String eventName = discount.getKey().getEventName();
        int discountAmount = discount.getValue();
        printer.printf(DISCOUNT_MESSAGE + WON, eventName, formattedAmount(discountAmount));
    }

    private void printPayment(Map<EventName, Integer> discountResult, int finalPayment) {
        if (discountResult.containsKey(GIFT_EVENT)) {
            int giftPrice = calculateFoodsAmount(CHAMPAGNE, 1);
            printer.printf(WON, formattedAmount(finalPayment + giftPrice));
            return;
        }

        printer.printf(WON, formattedAmount(finalPayment));
    }

    private void printBadge(String badge) {
        if (badge == null) {
            printer.println(NOTHING);
            return;
        }

        printer.println(badge);
    }

    private String formattedAmount(int amount) {
        return AMOUNT_FORMAT.format(amount);
    }
}
