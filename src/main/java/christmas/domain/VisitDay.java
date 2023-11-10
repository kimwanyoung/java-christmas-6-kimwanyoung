package christmas.domain;

import static christmas.view.InputView.INVALID_DAY_ERROR_MESSAGE;

public class VisitDay {

    private final int visitDay;

    public VisitDay(int visitDay) {
        validate(visitDay);
        this.visitDay = visitDay;
    }

    private void validate(int visitDay) {
        if (isOutOfRange(visitDay)) {
            throw new IllegalArgumentException(INVALID_DAY_ERROR_MESSAGE);
        }
    }

    private boolean isOutOfRange(int visitDay) {
        return 31 < visitDay || visitDay < 1;
    }
}
