package christmas.domain.discount;

import christmas.domain.EventName;
import christmas.domain.VisitDay;

public class SpecialDayDiscount extends DiscountPolicy {

    private final VisitDay visitDay;

    public SpecialDayDiscount(VisitDay visitDay, EventName eventName) {
        super(eventName);
        this.visitDay = visitDay;
    }

    @Override
    public int calculateDiscountAmount() {
        return -1000;
    }

    @Override
    public boolean isDiscountDay() {
        return visitDay.isSpecialDay();
    }
}
