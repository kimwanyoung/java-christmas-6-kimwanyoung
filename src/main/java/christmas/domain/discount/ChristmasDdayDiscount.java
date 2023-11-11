package christmas.domain.discount;

import christmas.domain.EventName;
import christmas.domain.VisitDay;

public class ChristmasDdayDiscount extends DiscountPolicy {

    private final VisitDay visitDay;

    public ChristmasDdayDiscount(VisitDay visitDay, EventName eventName) {
        super(eventName);
        this.visitDay = visitDay;
    }

    @Override
    public int calculateDiscountAmount() {
        return -(1000 + 100 * (visitDay.getDay() - 1));
    }

    @Override
    public boolean isDiscountDay() {
        return visitDay.isChristmasDay();
    }
}
