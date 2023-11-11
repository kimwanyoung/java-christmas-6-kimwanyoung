package christmas.controller;

import christmas.domain.OrderedMenu;
import christmas.domain.dto.DiscountResultDto;
import christmas.domain.dto.OrderedMenuDto;
import christmas.service.ChristmasDiscountService;
import christmas.view.OutputView;

public class ChristmasDiscountController {

    private final ChristmasDiscountService christmasDiscountService;

    public ChristmasDiscountController(ChristmasDiscountService service) {
        this.christmasDiscountService = service;
    }

    public void run() {
        OutputView.displayWelcomeMessage();
        OrderedMenu orderedMenu = christmasDiscountService.orderMenu();
        OrderedMenuDto orderedMenuDto = orderedMenu.toOrderMenuDto();
        OutputView.displayEventPreviewMessage();

        showOrderSummary(orderedMenuDto);

        DiscountResultDto discountDto = christmasDiscountService.calculateDiscount(orderedMenu);

        showDiscountSummary(orderedMenuDto, discountDto);
    }

    private void showOrderSummary(OrderedMenuDto orderedMenuDto) {
        OutputView.displayOrderedMenu(orderedMenuDto);
        OutputView.displayTotalOrderAmount(orderedMenuDto);
    }

    private void showDiscountSummary(OrderedMenuDto orderedMenuDto,
                                     DiscountResultDto discountResultDto) {
        OutputView.displayGift(discountResultDto);
        OutputView.displayTotalDiscounts(discountResultDto);
        OutputView.displayTotalDiscountAmount(discountResultDto);
        OutputView.displayFinalPayment(orderedMenuDto, discountResultDto);
        OutputView.displayBadge(discountResultDto);
    }
}
