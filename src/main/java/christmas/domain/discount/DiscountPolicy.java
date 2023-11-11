package christmas.domain.discount;

import christmas.domain.EventName;
import christmas.domain.VisitDay;

public abstract class DiscountPolicy {

    private final EventName eventName;

    public DiscountPolicy(EventName eventName) {
        this.eventName = eventName;
    }

    public abstract int calculateDiscountAmount(VisitDay visitDay);

    public abstract boolean isDiscountDay(VisitDay visitDay);

    public EventName getDiscountName() {
        return eventName;
    }
}
