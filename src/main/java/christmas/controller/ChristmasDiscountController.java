package christmas.controller;

import static christmas.domain.EventName.CHRISTMAS_DISCOUNT;
import static christmas.domain.EventName.GIFT_EVENT;
import static christmas.domain.EventName.SPECIAL_DISCOUNT;
import static christmas.domain.EventName.WEEKDAY_DISCOUNT;
import static christmas.domain.EventName.WEEKEND_DISCOUNT;

import christmas.domain.DiscountResult;
import christmas.domain.EventName;
import christmas.domain.OrderedMenu;
import christmas.domain.VisitDay;
import christmas.domain.discount.ChristmasDdayDiscount;
import christmas.domain.discount.DecemberDiscountEvents;
import christmas.domain.discount.FreeGift;
import christmas.domain.discount.SpecialDayDiscount;
import christmas.domain.discount.WeekdayDiscount;
import christmas.domain.discount.WeekendDiscount;
import christmas.domain.dto.OrderedMenuDto;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

public class ChristmasDiscountController {

    private VisitDay visitDay;
    private OrderedMenuDto orderedMenuDto;
    private DiscountResult discountResult;

    public void run() {
        OutputView.displayWelcomeMessage();
        createOrderStatus();
        OutputView.displayEventPreviewMessage();
        displayOrderInformation();
        createDiscountStatus();
        displayDiscountInformation();
    }

    private void displayOrderInformation() {
        OutputView.displayOrderedMenu(orderedMenuDto);
        OutputView.displayTotalOrderAmount(orderedMenuDto);
    }

    private void displayDiscountInformation() {
        OutputView.displayGift(discountResult);
        OutputView.displayTotalDiscounts(discountResult);
        OutputView.displayTotalDiscountAmount(discountResult);
        OutputView.displayFinalPayment(orderedMenuDto, discountResult);
        OutputView.displayBadge(discountResult);
    }

    private void createOrderStatus() {
        visitDay = createValidVisitDay();
        OrderedMenu orderedMenu = createValidOrderMenu();
        orderedMenuDto = orderedMenu.toOrderedMenuDto();
    }

    private void createDiscountStatus() {
        DecemberDiscountEvents decemberDiscountEvents = new DecemberDiscountEvents(visitDay,
                new ChristmasDdayDiscount(CHRISTMAS_DISCOUNT),
                new SpecialDayDiscount(SPECIAL_DISCOUNT),
                new WeekdayDiscount(WEEKDAY_DISCOUNT, orderedMenuDto),
                new WeekendDiscount(WEEKEND_DISCOUNT, orderedMenuDto),
                new FreeGift(GIFT_EVENT, orderedMenuDto));
        Map<EventName, Integer> appliedEvents = decemberDiscountEvents.calculateDiscountResult();
        discountResult = new DiscountResult(appliedEvents, appliedEvents.containsKey(GIFT_EVENT));
    }

    private VisitDay createValidVisitDay() {
        try {
            return new VisitDay(InputView.getVisitDayFromInput());
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return createValidVisitDay();
        }
    }

    private OrderedMenu createValidOrderMenu() {
        try {
            return new OrderedMenu(InputView.getMenuAndCountFromInput());
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return createValidOrderMenu();
        }
    }
}
