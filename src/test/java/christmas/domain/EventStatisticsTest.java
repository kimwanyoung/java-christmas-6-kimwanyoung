package christmas.domain;

import christmas.domain.dto.OrderedMenuDto;
import christmas.domain.menu.Foods;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EventStatisticsTest {

    private final EventStatistics eventStatistics = new EventStatistics();
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
    @DisplayName("구매 금액이 12만원 이상이면 증정 상품을 받는다.")
    void 증정상품_받는지_테스트() {
        //given
        OrderedMenuDto orderedMenuDto = orderedMenu.toOrderedMenuDto();

        //when
        eventStatistics.canReceiveGift(orderedMenuDto);
        boolean actual = eventStatistics.hasGift();

        //then
        Assertions.assertThat(actual).isTrue();
    }
}
