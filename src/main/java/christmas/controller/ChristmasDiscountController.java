package christmas.controller;

import static christmas.domain.EventName.CHRISTMAS_DISCOUNT;
import static christmas.domain.EventName.GIFT_EVENT;
import static christmas.domain.EventName.SPECIAL_DISCOUNT;
import static christmas.domain.EventName.WEEKDAY_DISCOUNT;
import static christmas.domain.EventName.WEEKEND_DISCOUNT;

import christmas.domain.EventName;
import christmas.domain.OrderedMenu;
import christmas.domain.VisitDay;
import christmas.domain.discount.ChristmasDdayDiscount;
import christmas.domain.discount.DecemberDiscountEvents;
import christmas.domain.discount.FreeGift;
import christmas.domain.discount.SpecialDayDiscount;
import christmas.domain.discount.WeekdayDiscount;
import christmas.domain.discount.WeekendDiscount;
import christmas.domain.dto.DiscountResultDto;
import christmas.domain.dto.OrderedMenuDto;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

public class ChristmasDiscountController {

    private VisitDay visitDay;
    private OrderedMenuDto orderedMenuDto;
    private DiscountResultDto discountResultDto;

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
        OutputView.displayGift(discountResultDto);
        OutputView.displayTotalDiscounts(discountResultDto);
        OutputView.displayTotalDiscountAmount(discountResultDto);
        OutputView.displayFinalPayment(orderedMenuDto, discountResultDto);
        OutputView.displayBadge(discountResultDto);
    }

    private void createOrderStatus() {
        visitDay = createValidVisitDay();
        OrderedMenu orderedMenu = createValidOrderMenu();
        orderedMenuDto = new OrderedMenuDto(orderedMenu.getOrderedMenu());
    }

    private void createDiscountStatus() {
        DecemberDiscountEvents decemberDiscountEvents = new DecemberDiscountEvents(visitDay,
                new ChristmasDdayDiscount(CHRISTMAS_DISCOUNT),
                new SpecialDayDiscount(SPECIAL_DISCOUNT),
                new WeekdayDiscount(WEEKDAY_DISCOUNT, orderedMenuDto),
                new WeekendDiscount(WEEKEND_DISCOUNT, orderedMenuDto),
                new FreeGift(GIFT_EVENT, orderedMenuDto));
        Map<EventName, Integer> appliedEvents = decemberDiscountEvents.calculateDiscountResult();
        discountResultDto = new DiscountResultDto(appliedEvents);
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
