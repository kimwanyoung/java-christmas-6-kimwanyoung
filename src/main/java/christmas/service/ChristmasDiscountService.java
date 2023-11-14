package christmas.service;

import static christmas.domain.EventName.CHRISTMAS_DISCOUNT;
import static christmas.domain.EventName.SPECIAL_DISCOUNT;
import static christmas.domain.EventName.WEEKDAY_DISCOUNT;
import static christmas.domain.EventName.WEEKEND_DISCOUNT;

import christmas.domain.OrderedMenu;
import christmas.domain.VisitDay;
import christmas.domain.discount.ChristmasDdayDiscount;
import christmas.domain.discount.DecemberDiscountEvents;
import christmas.domain.discount.SpecialDayDiscount;
import christmas.domain.discount.WeekdayDiscount;
import christmas.domain.discount.WeekendDiscount;
import christmas.domain.dto.DiscountResultDto;
import christmas.domain.dto.ReservationDto;
import christmas.domain.menu.Foods;
import java.util.Map;

public class ChristmasDiscountService {

    private VisitDay visitDay;
    private OrderedMenu orderedMenu;

    public void generateValidVisitDay(int visitDay) {
        this.visitDay = new VisitDay(visitDay);
    }

    public void generateValidOrderedMenu(Map<Foods, Integer> orderedMenu) {
        this.orderedMenu = new OrderedMenu(orderedMenu);
    }

    public ReservationDto calculateReservation() {
        return new ReservationDto(
                visitDay.getDay(),
                orderedMenu.getOrderedMenu(),
                orderedMenu.calculateTotalAmount()
        );
    }

    public DiscountResultDto calculateDiscount() {
        DecemberDiscountEvents discountEvents = createDecemberEvents();
        return discountEvents.toDiscountResultDto(orderedMenu.calculateTotalAmount());
    }

    private DecemberDiscountEvents createDecemberEvents() {
        return new DecemberDiscountEvents(
                new ChristmasDdayDiscount(CHRISTMAS_DISCOUNT, visitDay),
                new SpecialDayDiscount(SPECIAL_DISCOUNT, visitDay),
                new WeekdayDiscount(WEEKDAY_DISCOUNT, orderedMenu.getOrderedMenu(), visitDay),
                new WeekendDiscount(WEEKEND_DISCOUNT, orderedMenu.getOrderedMenu(), visitDay)
        );
    }
}
