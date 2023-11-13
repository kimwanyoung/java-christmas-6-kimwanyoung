package christmas.controller;

import christmas.domain.OrderedMenu;
import christmas.domain.VisitDay;
import christmas.domain.dto.DiscountResultDto;
import christmas.domain.dto.ReservationDto;
import christmas.service.ChristmasDiscountService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class ChristmasDiscountController {

    private final OutputView outputView;
    private final InputView inputView;
    private final ChristmasDiscountService christmasService;

    public ChristmasDiscountController(
            OutputView outputView,
            InputView inputView,
            ChristmasDiscountService service
    ) {
        this.outputView = outputView;
        this.inputView = inputView;
        this.christmasService = service;
    }

    public void run() {
        outputView.displayWelcomeMessage();

        VisitDay visitDay = getValidVisitDay();
        OrderedMenu orderedMenu = getValidOrderMenu();
        ReservationDto reservationDto = christmasService.reservation(visitDay, orderedMenu);

        outputView.displayEventPreviewMessage(reservationDto);

        DiscountResultDto discountResultDto = christmasService.calculateDiscount();

        showOrderSummary(reservationDto);
        showDiscountSummary(discountResultDto);
        showFinalPaymentSummary(reservationDto, discountResultDto);
    }

    private VisitDay getValidVisitDay() {
        try {
            return new VisitDay(inputView.getVisitDayFromInput());
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return getValidVisitDay();
        }
    }

    private OrderedMenu getValidOrderMenu() {
        try {
            return new OrderedMenu(inputView.getMenuAndCountFromInput());
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return getValidOrderMenu();
        }
    }

    private void showOrderSummary(ReservationDto reservationDto) {
        outputView.displayOrderedMenu(reservationDto);
        outputView.displayTotalOrderAmount(reservationDto);
    }

    private void showDiscountSummary(DiscountResultDto discountResultDto) {
        outputView.displayGift(discountResultDto);
        outputView.displayTotalDiscounts(discountResultDto);
        outputView.displayTotalDiscountAmount(discountResultDto);
    }

    private void showFinalPaymentSummary(ReservationDto reservationDto,
                                         DiscountResultDto discountResultDto) {
        outputView.displayFinalPayment(reservationDto, discountResultDto);
        outputView.displayBadge(discountResultDto);
    }
}
