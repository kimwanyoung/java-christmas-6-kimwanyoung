package christmas.controller;

import christmas.domain.OrderedMenu;
import christmas.domain.dto.DiscountResultDto;
import christmas.domain.dto.OrderedMenuDto;
import christmas.service.ChristmasDiscountService;
import christmas.view.OutputView;

public class ChristmasDiscountController {

    private final OutputView outputView;
    private final ChristmasDiscountService christmasDiscountService;

    public ChristmasDiscountController(OutputView outputView, ChristmasDiscountService service) {
        this.outputView = outputView;
        this.christmasDiscountService = service;
    }

    public void run() {
        outputView.displayWelcomeMessage();
        OrderedMenu orderedMenu = christmasDiscountService.orderMenu();
        OrderedMenuDto orderedMenuDto = orderedMenu.toOrderMenuDto();
        outputView.displayEventPreviewMessage();

        showOrderSummary(orderedMenuDto);

        DiscountResultDto discountDto = christmasDiscountService.calculateDiscount(orderedMenu);

        showDiscountSummary(orderedMenuDto, discountDto);
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
