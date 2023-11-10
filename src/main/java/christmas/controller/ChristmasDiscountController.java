package christmas.controller;

import christmas.domain.Discount;
import christmas.domain.DiscountResult;
import christmas.domain.OrderedMenu;
import christmas.domain.VisitDay;
import christmas.domain.dto.OrderedMenuDto;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasDiscountController {

    private Discount discount;
    private VisitDay visitDay;
    private OrderedMenu orderedMenu;
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
        orderedMenu = createValidOrderMenu();
        orderedMenuDto = orderedMenu.toOrderedMenuDto();
    }

    private void createDiscountStatus() {
        boolean hasGift = orderedMenuDto.totalAmount() > 120000;
        discount = new Discount(visitDay, orderedMenuDto);
        discountResult = new DiscountResult(discount.calculateDiscountResult(), hasGift);
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
