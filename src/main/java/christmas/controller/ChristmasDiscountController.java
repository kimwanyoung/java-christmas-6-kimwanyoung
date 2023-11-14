package christmas.controller;

import christmas.domain.dto.DiscountResultDto;
import christmas.domain.dto.ReservationDto;
import christmas.domain.menu.Foods;
import christmas.service.ChristmasDiscountService;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

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

        processReservation();
        ReservationDto reservationDto = christmasService.calculateReservation();

        outputView.displayEventPreviewMessage(reservationDto);

        DiscountResultDto discountResultDto = christmasService.calculateDiscount();

        showOrderSummary(reservationDto);
        showDiscountSummary(discountResultDto);
        showFinalPaymentSummary(reservationDto, discountResultDto);
    }

    private void processReservation() {
        processVisitDayInput();
        processOrderedMenuInput();
    }

    private void processVisitDayInput() {
        while (true) {
            try {
                int visitDay = inputView.getVisitDayFromInput();
                christmasService.generateValidVisitDay(visitDay);
                break;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void processOrderedMenuInput() {
        while (true) {
            try {
                Map<Foods, Integer> orderedMenu = inputView.getMenuAndCountFromInput();
                christmasService.generateValidOrderedMenu(orderedMenu);
                break;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
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
