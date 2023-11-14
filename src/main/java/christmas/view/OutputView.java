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

import christmas.domain.Badge;
import christmas.domain.EventName;
import christmas.domain.dto.DiscountResultDto;
import christmas.domain.dto.ReservationDto;
import christmas.domain.menu.Foods;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {
    
    private static final String WON = "%s원\n";
    private static final String DISCOUNT_MESSAGE = "%s: ";
    private static final String NOTHING = "없음";
    private static final String MENU_INFO = "%s %d개\n";
    private static final DecimalFormat AMOUNT_FORMAT = new DecimalFormat("###,###");

    private final Printer printer;

    public OutputView(Printer printer) {
        this.printer = printer;
    }

    public void displayWelcomeMessage() {
        printer.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void displayEventPreviewMessage(ReservationDto reservationDto) {
        printer.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", reservationDto.visitDay());
    }

    public void displayOrderedMenu(ReservationDto reservationDto) {
        printer.println(ORDER_MENU);
        printMenuItems(reservationDto.orderedMenu());
    }

    public void displayTotalOrderAmount(ReservationDto reservationDto) {
        printer.println(ORDER_TOTAL_AMOUNT);
        printer.println(formattedAmount(reservationDto.totalOrderAmount()));
    }

    public void displayGift(DiscountResultDto discountResultDto) {
        printer.println(GIFT_MENU);
        printGift(discountResultDto.discountResult());
    }

    public void displayTotalDiscounts(DiscountResultDto discountResultDto) {
        printer.println(DISCOUNT_DETAILS);
        printDiscountResult(discountResultDto.discountResult());
    }

    public void displayTotalDiscountAmount(DiscountResultDto discountResultDto) {
        printer.println(TOTAL_DISCOUNT_AMOUNT);
        printer.printf(WON, formattedAmount(discountResultDto.totalDiscountAmount()));
    }

    public void displayFinalPayment(ReservationDto reservationDto,
                                    DiscountResultDto discountResultDto) {
        printer.println(FINAL_PAYMENT_AMOUNT);

        int totalOrderAmount = reservationDto.totalOrderAmount();
        int totalDiscountAmount = discountResultDto.totalDiscountAmount();
        int finalPayment = totalOrderAmount + totalDiscountAmount;

        printPayment(discountResultDto.discountResult(), finalPayment);
    }

    public void displayBadge(DiscountResultDto discountResultDto) {
        printer.println(EVENT_BADGE);
        String badge = Badge.calculateBadge(discountResultDto.totalDiscountAmount());
        printBadge(badge);
    }

    private void printMenuItems(Map<Foods, Integer> orderedMenu) {
        for (Entry<Foods, Integer> menu : orderedMenu.entrySet()) {
            String menuName = menu.getKey().getName();
            Integer menuCount = menu.getValue();
            printer.printf(MENU_INFO, menuName, menuCount);
        }
    }

    private void printGift(Map<EventName, Integer> discountResults) {
        if (discountResults.containsKey(GIFT_EVENT)) {
            printer.printf(MENU_INFO, CHAMPAGNE.getName(), 1);
            return;
        }
        printer.println(NOTHING);
    }

    private void printDiscountResult(Map<EventName, Integer> discountResult) {
        if (discountResult.isEmpty()) {
            printer.println(NOTHING);
            return;
        }
        for (Entry<EventName, Integer> discount : discountResult.entrySet()) {
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
            int giftPrice = Foods.calculateFoodsAmount(CHAMPAGNE, 1);
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
