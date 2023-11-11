package christmas.domain.discount;

import static christmas.constants.DiscountAmountConstant.BASE_DISCOUNT_AMOUNT;

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
        return BASE_DISCOUNT_AMOUNT;
    }

    @Override
    public boolean isDiscountDay() {
        return visitDay.isSpecialDay();
    }
}
