package christmas.controller;

import christmas.domain.Discount;
import christmas.domain.DiscountResult;
import christmas.domain.OrderedMenu;
import christmas.domain.VisitDay;
import christmas.domain.dto.OrderedMenuDto;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasDiscountController {

    public void run() {
        OutputView.displayWelcomeMessage();
        VisitDay visitDay = new VisitDay(InputView.getVisitDayFromInput());
        OrderedMenu orderedMenu = new OrderedMenu(InputView.getMenuAndCountFromInput());
        OutputView.displayEventPreviewMessage();
        OrderedMenuDto orderedMenuDto = orderedMenu.toOrderedMenuDto();
        OutputView.displayOrderedMenu(orderedMenuDto);
        OutputView.displayTotalOrderAmount(orderedMenuDto);
        boolean hasGift = orderedMenuDto.totalAmount() > 120000;
        Discount discount = new Discount(visitDay, orderedMenuDto);
        DiscountResult discountResult = new DiscountResult(
                discount.calculateDiscountResult(), hasGift);
        OutputView.displayGift(discountResult);
        OutputView.displayTotalDiscounts(discountResult);
        OutputView.displayTotalDiscountAmount(discountResult);
        OutputView.displayFinalPayment(orderedMenuDto, discountResult);
        OutputView.displayBadge(discountResult);
    }
}
