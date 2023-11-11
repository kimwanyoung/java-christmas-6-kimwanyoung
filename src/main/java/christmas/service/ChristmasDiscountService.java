package christmas.service;

import static christmas.domain.EventName.CHRISTMAS_DISCOUNT;
import static christmas.domain.EventName.GIFT_EVENT;
import static christmas.domain.EventName.SPECIAL_DISCOUNT;
import static christmas.domain.EventName.WEEKDAY_DISCOUNT;
import static christmas.domain.EventName.WEEKEND_DISCOUNT;

import christmas.domain.OrderedMenu;
import christmas.domain.VisitDay;
import christmas.domain.discount.ChristmasDdayDiscount;
import christmas.domain.discount.DecemberDiscountEvents;
import christmas.domain.discount.FreeGift;
import christmas.domain.discount.SpecialDayDiscount;
import christmas.domain.discount.WeekdayDiscount;
import christmas.domain.discount.WeekendDiscount;
import christmas.domain.dto.DiscountResultDto;

public class ChristmasDiscountService {
    
    public DiscountResultDto calculateDiscount(VisitDay visitDay, OrderedMenu orderedMenu) {
        DecemberDiscountEvents discountEvents = createDecemberEvents(visitDay, orderedMenu);
        return discountEvents.toDiscountResultDto(orderedMenu.calculateTotalAmount());
    }

    private DecemberDiscountEvents createDecemberEvents(VisitDay visitDay,
                                                        OrderedMenu orderedMenu) {
        return new DecemberDiscountEvents(visitDay,
                new ChristmasDdayDiscount(CHRISTMAS_DISCOUNT),
                new SpecialDayDiscount(SPECIAL_DISCOUNT),
                new WeekdayDiscount(WEEKDAY_DISCOUNT, orderedMenu.getOrderedMenu()),
                new WeekendDiscount(WEEKEND_DISCOUNT, orderedMenu.getOrderedMenu()),
                new FreeGift(GIFT_EVENT, orderedMenu.calculateTotalAmount()));
    }
}
