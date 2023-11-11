package christmas;

import christmas.controller.ChristmasDiscountController;
import christmas.service.ChristmasDiscountService;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        ChristmasDiscountService christmasDiscountService = new ChristmasDiscountService();
        ChristmasDiscountController controller = new ChristmasDiscountController(
                christmasDiscountService);
        controller.run();
    }
}
