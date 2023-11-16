package christmas.domain;

public enum EventName {
    CHRISTMAS_DISCOUNT("크리스마스 디데이 할인"),
    SPECIAL_DISCOUNT("특별 할인"),
    WEEKDAY_DISCOUNT("평일 할인"),
    WEEKEND_DISCOUNT("주말 할인"),
    GIFT_EVENT("증정 이벤트");

    private final String eventName;

    EventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }
}
