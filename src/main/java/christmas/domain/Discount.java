package christmas.domain;

import christmas.domain.dto.OrderedMenuDto;

public class Discount {

    private VisitDay visitDay;
    private OrderedMenuDto orderedMenuDto;

    public Discount(VisitDay visitDay, OrderedMenuDto orderedMenuDto) {
        this.visitDay = visitDay;
        this.orderedMenuDto = orderedMenuDto;
    }

    private int christmasDdayDiscount() {
        if (visitDay.isChristmasDay()) {

        }
        return 0;
    }

    private int specialDiscount() {
        if (visitDay.isSpecialDay()) {

        }
        return 0;
    }

    private int weekdayDiscount() {
        if (visitDay.isWeekday()) {

        }
        return 0;
    }

    private int weekendDiscount() {
        if (visitDay.isWeekend()) {

        }
        return 0;
    }
}

