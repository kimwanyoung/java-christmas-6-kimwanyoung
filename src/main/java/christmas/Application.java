package christmas;

import christmas.controller.ChristmasDiscountController;
import christmas.service.ChristmasDiscountService;
import christmas.view.DefaultPrinter;
import christmas.view.DefaultReader;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        ChristmasDiscountController controller = getChristmasController();
        controller.run();
    }

    private static ChristmasDiscountController getChristmasController() {
        return new ChristmasDiscountController(
                getDefaultOutputView(),
                getDefaultInputView(),
                getChristmasService());
    }

    private static ChristmasDiscountService getChristmasService() {
        return new ChristmasDiscountService();
    }

    private static OutputView getDefaultOutputView() {
        return new OutputView(new DefaultPrinter());
    }

    private static InputView getDefaultInputView() {
        return new InputView(new DefaultReader(), new DefaultPrinter());
    }
}
