package christmas.domain.discount;

import static christmas.constants.DiscountAmountConstant.FREE_GIFT_AMOUNT;
import static christmas.constants.DiscountAmountConstant.MIN_AMOUNT_FOR_GIFT;

import christmas.domain.EventName;
import christmas.domain.VisitDay;

public class FreeGift extends DiscountPolicy {

    private final int totalAmount;

    public FreeGift(EventName eventName, int totalAmount) {
        super(eventName);
        this.totalAmount = totalAmount;
    }

    @Override
    public int calculateDiscountAmount(VisitDay visitDay) {
        return FREE_GIFT_AMOUNT;
    }

    @Override
    public boolean isDiscountDay(VisitDay visitDay) {
        return totalAmount >= MIN_AMOUNT_FOR_GIFT;
    }
}
