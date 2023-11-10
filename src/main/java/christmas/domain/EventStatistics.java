package christmas.domain;

import christmas.domain.dto.OrderedMenuDto;
import christmas.domain.menu.Foods;

public class EventStatistics {

    private Foods gift;

    public void canReceiveGift(OrderedMenuDto orderedMenuDto) {
        if (orderedMenuDto.totalAmount() >= 120000) {
            this.gift = Foods.CHAMPAGNE;
        }
    }

    public boolean hasGift() {
        return gift != null;
    }
}
