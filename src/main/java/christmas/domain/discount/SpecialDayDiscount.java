package christmas.domain.discount;

import static christmas.constants.DiscountAmountConstant.BASE_DISCOUNT_AMOUNT;

import christmas.domain.EventName;
import christmas.domain.VisitDay;
import java.util.List;

public class SpecialDayDiscount extends DiscountPolicy {

    private static final List<Integer> SPECIAL_DAYS = List.of(3, 10, 17, 24, 25, 31);
    private final VisitDay visitDay;

    public SpecialDayDiscount(EventName eventName, VisitDay visitDay) {
        super(eventName);
        this.visitDay = visitDay;
    }

    @Override
    public int calculateDiscountAmount() {
        return BASE_DISCOUNT_AMOUNT;
    }

    @Override
    public boolean isDiscountDay() {
        return visitDay.isSpecialDay(SPECIAL_DAYS);
    }
}
