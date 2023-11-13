package christmas.domain.discount;

import static christmas.constants.DiscountAmountConstant.BASE_DISCOUNT_AMOUNT;
import static christmas.constants.DiscountAmountConstant.CHRISTMAS_INCREASE_AMOUNT;
import static christmas.constants.EventDayConstants.EVENT_START_DAY;

import christmas.domain.EventName;
import christmas.domain.VisitDay;

public class ChristmasDdayDiscount extends DiscountPolicy {

    private static final int CHRISTMAS_DAY = 25;
    private final VisitDay visitDay;

    public ChristmasDdayDiscount(EventName eventName, VisitDay visitDay) {
        super(eventName);
        this.visitDay = visitDay;
    }

    @Override
    public int calculateDiscountAmount() {
        return BASE_DISCOUNT_AMOUNT + CHRISTMAS_INCREASE_AMOUNT * (visitDay.getDay() - 1);
    }

    @Override
    public boolean isDiscountDay() {
        return visitDay.isChristmasDay(EVENT_START_DAY, CHRISTMAS_DAY);
    }
}
