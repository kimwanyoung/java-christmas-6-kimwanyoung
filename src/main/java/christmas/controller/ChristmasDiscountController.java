package christmas.controller;

import christmas.domain.Discount;
import christmas.domain.EventName;
import christmas.domain.EventStatistics;
import christmas.domain.OrderedMenu;
import christmas.domain.VisitDay;
import christmas.domain.dto.OrderedMenuDto;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

public class ChristmasDiscountController {

    private EventStatistics eventStatistics = new EventStatistics();
    private Discount discount;

    public void run() {
        OutputView.displayWelcomeMessage();
        VisitDay visitDay = new VisitDay(InputView.getVisitDayFromInput());
        OrderedMenu orderedMenu = new OrderedMenu(InputView.getMenuAndCountFromInput());
        System.out.println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        OrderedMenuDto orderedMenuDto = orderedMenu.toOrderedMenuDto();
        eventStatistics.canReceiveGift(orderedMenuDto);
        OutputView.displayOrderedMenu(orderedMenuDto);
        OutputView.displayTotalOrderAmount(orderedMenuDto);
        OutputView.displayGift(eventStatistics);
        discount = new Discount(visitDay, orderedMenuDto);
        Map<EventName, Integer> discountStatistics = discount.calculatePossibleDiscount();
        OutputView.displayTotalDiscounts(discountStatistics);
    }
}
