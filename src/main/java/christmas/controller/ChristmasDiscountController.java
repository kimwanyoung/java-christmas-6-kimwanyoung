package christmas.controller;

import christmas.domain.OrderedMenu;
import christmas.domain.VisitDay;
import christmas.domain.dto.DiscountResultDto;
import christmas.domain.dto.OrderedMenuDto;
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
        OrderedMenuDto orderedMenuDto = orderedMenu.toOrderMenuDto();
        outputView.displayEventPreviewMessage();

        showOrderSummary(orderedMenuDto);

        DiscountResultDto discountDto = christmasDiscountService
                .calculateDiscount(visitDay, orderedMenu);

        showDiscountSummary(orderedMenuDto, discountDto);
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

    private void showOrderSummary(OrderedMenuDto orderedMenuDto) {
        outputView.displayOrderedMenu(orderedMenuDto);
        outputView.displayTotalOrderAmount(orderedMenuDto);
    }

    private void showDiscountSummary(OrderedMenuDto orderedMenuDto,
                                     DiscountResultDto discountResultDto) {
        outputView.displayGift(discountResultDto);
        outputView.displayTotalDiscounts(discountResultDto);
        outputView.displayTotalDiscountAmount(discountResultDto);
        outputView.displayFinalPayment(orderedMenuDto, discountResultDto);
        outputView.displayBadge(discountResultDto);
    }
}
