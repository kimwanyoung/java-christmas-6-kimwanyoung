package christmas.domain;

import static christmas.domain.EventName.CHRISTMAS_DISCOUNT;
import static christmas.domain.EventName.GIFT_EVENT;
import static christmas.domain.EventName.SPECIAL_DISCOUNT;
import static christmas.domain.EventName.WEEKDAY_DISCOUNT;

import christmas.domain.menu.Foods;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DiscountTest {

    private static OrderedMenu orderedMenu;

    @BeforeAll
    static void setUp() {
        Map<Foods, Integer> menus = new HashMap<>();
        menus.put(Foods.BARBECUE_RIB, 1);
        menus.put(Foods.TAPAS, 1);
        menus.put(Foods.T_BONE_STEAK, 1);
        menus.put(Foods.CHOCOLATE_CAKE, 1);
        orderedMenu = new OrderedMenu(menus);
    }

    @Test
    @DisplayName("날짜에 적용되는 할인 테스트")
    void 적용되는_할인_테스트() {
        //given
        final Discount discount = new Discount(new VisitDay(3), orderedMenu.toOrderedMenuDto());

        //when
        Map<EventName, Integer> discountsStatistics = discount.calculatePossibleDiscount();

        //then
        Assertions.assertThat(discountsStatistics.containsKey(CHRISTMAS_DISCOUNT)).isTrue();
        Assertions.assertThat(discountsStatistics.containsKey(WEEKDAY_DISCOUNT)).isTrue();
        Assertions.assertThat(discountsStatistics.containsKey(SPECIAL_DISCOUNT)).isTrue();
        Assertions.assertThat(discountsStatistics.containsKey(GIFT_EVENT)).isTrue();
    }
}
