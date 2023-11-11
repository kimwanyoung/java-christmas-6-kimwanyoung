package christmas.view;

public class DefaultPrinter implements Printer {

    @Override
    public void println(String message) {
        System.out.println(message);
    }

    @Override
    public void printf(String message, Object... args) {
        System.out.printf(message, args);
    }
}
