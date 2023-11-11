package christmas.domain.discount;

import christmas.domain.EventName;
import christmas.domain.dto.OrderedMenuDto;

public class FreeGift extends DiscountPolicy {

    private final OrderedMenuDto orderedMenuDto;

    public FreeGift(EventName eventName, OrderedMenuDto orderedMenuDto) {
        super(eventName);
        this.orderedMenuDto = orderedMenuDto;
    }

    @Override
    public int calculateDiscountAmount() {
        return -25000;
    }

    @Override
    public boolean isDiscountDay() {
        return orderedMenuDto.totalAmount() >= 120000;
    }
}
