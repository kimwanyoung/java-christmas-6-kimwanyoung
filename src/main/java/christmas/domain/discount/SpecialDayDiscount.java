package christmas.domain.discount;

import static christmas.constants.DiscountAmountConstant.BASE_DISCOUNT_AMOUNT;

import christmas.domain.EventName;
import christmas.domain.VisitDay;

public class SpecialDayDiscount extends DiscountPolicy {

    public SpecialDayDiscount(EventName eventName) {
        super(eventName);
    }

    @Override
    public int calculateDiscountAmount(VisitDay visitDay) {
        return BASE_DISCOUNT_AMOUNT;
    }

    @Override
    public boolean isDiscountDay(VisitDay visitDay) {
        return visitDay.isSpecialDay();
    }
}
