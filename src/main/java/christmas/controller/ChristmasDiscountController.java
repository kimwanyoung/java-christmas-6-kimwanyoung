package christmas.controller;

import christmas.domain.OrderedMenu;
import christmas.domain.VisitDay;
import christmas.domain.dto.DiscountResultDto;
import christmas.service.ChristmasDiscountService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasDiscountController {

    private final OutputView outputView;
    private final InputView inputView;
    private final ChristmasDiscountService christmasDiscountService;

    public ChristmasDiscountController(
            OutputView outputView,
            InputView inputView,
            ChristmasDiscountService service
    ) {
        this.outputView = outputView;
        this.inputView = inputView;
        this.christmasDiscountService = service;
    }

    public void run() {
        outputView.displayWelcomeMessage();
        VisitDay visitDay = createValidVisitDay();
        OrderedMenu orderedMenu = createValidOrderMenu();
        outputView.displayEventPreviewMessage();

        showOrderSummary(orderedMenu);

        DiscountResultDto discountDto = christmasDiscountService
                .calculateDiscount(visitDay, orderedMenu);

        showDiscountSummary(orderedMenu, discountDto);
    }

    private VisitDay createValidVisitDay() {
        try {
            return new VisitDay(inputView.getVisitDayFromInput());
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return createValidVisitDay();
        }
    }

    private OrderedMenu createValidOrderMenu() {
        try {
            return new OrderedMenu(inputView.getMenuAndCountFromInput());
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return createValidOrderMenu();
        }
    }

    private void showOrderSummary(OrderedMenu orderedMenu) {
        outputView.displayOrderedMenu(orderedMenu);
        outputView.displayTotalOrderAmount(orderedMenu);
    }

    private void showDiscountSummary(OrderedMenu orderedMenu, DiscountResultDto discountResultDto) {
        outputView.displayGift(discountResultDto);
        outputView.displayTotalDiscounts(discountResultDto);
        outputView.displayTotalDiscountAmount(discountResultDto);
        outputView.displayFinalPayment(orderedMenu, discountResultDto);
        outputView.displayBadge(discountResultDto);
    }
}
