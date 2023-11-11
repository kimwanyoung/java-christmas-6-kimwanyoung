package christmas.domain.discount;

import static christmas.constants.DiscountAmountConstant.BASE_DISCOUNT_AMOUNT;

import christmas.domain.EventName;
import christmas.domain.VisitDay;
import java.util.List;

public class SpecialDayDiscount extends DiscountPolicy {

    private static final List<Integer> SPECIAL_DAYS = List.of(3, 10, 17, 24, 25, 31);

    public SpecialDayDiscount(EventName eventName) {
        super(eventName);
    }

    @Override
    public int calculateDiscountAmount(VisitDay visitDay) {
        return BASE_DISCOUNT_AMOUNT;
    }

    @Override
    public boolean isDiscountDay(VisitDay visitDay) {
        return visitDay.isSpecialDay(SPECIAL_DAYS);
    }
}
