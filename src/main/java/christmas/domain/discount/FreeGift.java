package christmas.domain.discount;

import static christmas.constants.DiscountAmountConstant.FREE_GIFT_AMOUNT;
import static christmas.constants.DiscountAmountConstant.MIN_AMOUNT_FOR_GIFT;
import static christmas.utils.Calculator.calculateTotalAmount;

import christmas.domain.EventName;
import christmas.domain.VisitDay;
import christmas.domain.dto.OrderedMenuDto;

public class FreeGift extends DiscountPolicy {

    private final OrderedMenuDto orderedMenuDto;

    public FreeGift(EventName eventName, OrderedMenuDto orderedMenuDto) {
        super(eventName);
        this.orderedMenuDto = orderedMenuDto;
    }

    @Override
    public int calculateDiscountAmount(VisitDay visitDay) {
        return FREE_GIFT_AMOUNT;
    }

    @Override
    public boolean isDiscountDay(VisitDay visitDay) {
        return calculateTotalAmount(orderedMenuDto.orderedMenu()) >= MIN_AMOUNT_FOR_GIFT;
    }
}
