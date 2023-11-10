package christmas.controller;

import christmas.domain.Discount;
import christmas.domain.DiscountResultDto;
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
        System.out.println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        OrderedMenuDto orderedMenuDto = orderedMenu.toOrderedMenuDto();
        OutputView.displayOrderedMenu(orderedMenuDto);
        OutputView.displayTotalOrderAmount(orderedMenuDto);
        boolean hasGift = orderedMenuDto.totalAmount() > 120000;
        Discount discount = new Discount(visitDay, orderedMenuDto);
        DiscountResultDto discountResultDto = new DiscountResultDto(
                discount.toDiscountResultDto(), hasGift);
        OutputView.displayTotalDiscounts(discountResultDto);
    }
}
