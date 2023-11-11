package christmas.domain.discount;

import static christmas.constants.DiscountAmountConstant.BASE_DISCOUNT_AMOUNT;
import static christmas.constants.DiscountAmountConstant.CHRISTMAS_INCREASE_AMOUNT;

import christmas.domain.EventName;
import christmas.domain.VisitDay;

public class ChristmasDdayDiscount extends DiscountPolicy {

    public ChristmasDdayDiscount(EventName eventName) {
        super(eventName);
    }

    @Override
    public int calculateDiscountAmount(VisitDay visitDay) {
        return BASE_DISCOUNT_AMOUNT + CHRISTMAS_INCREASE_AMOUNT * (visitDay.getDay() - 1);
    }

    @Override
    public boolean isDiscountDay(VisitDay visitDay) {
        return visitDay.isChristmasDay();
    }
}
