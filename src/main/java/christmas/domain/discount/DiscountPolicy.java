package christmas.domain.discount;

import christmas.domain.EventName;

public abstract class DiscountPolicy {

    private final EventName eventName;

    public DiscountPolicy(EventName eventName) {
        this.eventName = eventName;
    }

    public abstract int calculateDiscountAmount();

    public abstract boolean isDiscountDay();

    public EventName getDiscountName() {
        return eventName;
    }
}
