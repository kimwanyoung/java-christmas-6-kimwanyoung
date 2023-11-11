package christmas;

import christmas.controller.ChristmasDiscountController;
import christmas.service.ChristmasDiscountService;
import christmas.view.DefaultPrinter;
import christmas.view.DefaultReader;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        OutputView outputView = new OutputView(new DefaultPrinter());
        InputView inputView = new InputView(new DefaultReader(), new DefaultPrinter());
        
        ChristmasDiscountService christmasDiscountService = new ChristmasDiscountService();
        ChristmasDiscountController controller = new ChristmasDiscountController(
                outputView,
                inputView,
                christmasDiscountService);

        controller.run();
    }
}
