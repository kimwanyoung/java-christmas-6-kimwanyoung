package christmas.domain.discount;

import static christmas.domain.EventName.GIFT_EVENT;
import static christmas.domain.menu.Foods.BARBECUE_RIB;
import static christmas.domain.menu.Foods.CAESAR_SALAD;
import static christmas.domain.menu.Foods.CHOCOLATE_CAKE;
import static christmas.domain.menu.Foods.T_BONE_STEAK;
import static christmas.domain.menu.Foods.ZERO_COKE;

import christmas.domain.EventName;
import christmas.domain.OrderedMenu;
import christmas.domain.VisitDay;
import christmas.domain.dto.DiscountResultDto;
import christmas.domain.menu.Foods;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DecemberDiscountEventsTest {

    @Test
    @DisplayName("날짜에 적용되는 할인 테스트")
    void 적용되는_할인_테스트() {
        // given
        OrderedMenu orderedMenu = createOrderedMenu(T_BONE_STEAK, BARBECUE_RIB, ZERO_COKE,
                CHOCOLATE_CAKE);

        // when
        DecemberDiscountEvents decemberDiscountEvents = createDecemberDiscountEvents(3,
                orderedMenu);

        DiscountResultDto discountResultDto = decemberDiscountEvents.toDiscountResultDto(
                orderedMenu.calculateTotalAmount());
        Map<EventName, Integer> discountsStatistics = discountResultDto.discountResult();

        // then
        Assertions.assertThat(discountsStatistics).containsKeys(
                EventName.CHRISTMAS_DISCOUNT,
                EventName.WEEKDAY_DISCOUNT,
                EventName.SPECIAL_DISCOUNT,
                GIFT_EVENT);
    }

    @Test
    @DisplayName("10000원 미만 주문시 혜택이 적용되지 않는다.")
    void 만원_미만_주문_헤택_미적용_테스트() {
        // given
        OrderedMenu orderedMenu = createOrderedMenu(CAESAR_SALAD);

        // when
        DecemberDiscountEvents decemberDiscountEvents = createDecemberDiscountEvents(16,
                orderedMenu);

        DiscountResultDto discountResultDto = decemberDiscountEvents.toDiscountResultDto(
                orderedMenu.calculateTotalAmount());
        Map<EventName, Integer> discountsStatistics = discountResultDto.discountResult();

        // then
        Assertions.assertThat(discountsStatistics).isEmpty();
    }

    @Test
    @DisplayName("120000원 미만 주문 시 증정 상품을 제공하지 않는다.")
    void 증정_상품_제공_테스트() {
        //given
        OrderedMenu orderedMenu = createOrderedMenu(CAESAR_SALAD);

        //when
        DecemberDiscountEvents decemberDiscountEvents = createDecemberDiscountEvents(16,
                orderedMenu);

        DiscountResultDto discountResultDto = decemberDiscountEvents.toDiscountResultDto(
                orderedMenu.calculateTotalAmount());
        Map<EventName, Integer> discountsStatistics = discountResultDto.discountResult();

        //then
        Assertions.assertThat(discountsStatistics.containsKey(GIFT_EVENT)).isFalse();
    }

    @Test
    @DisplayName("120000원 이상 주문 시 증정 상품을 제공한다.")
    void 증정_상품_제공_정상_테스트() {
        //given
        OrderedMenu orderedMenu = createOrderedMenu(T_BONE_STEAK, BARBECUE_RIB, ZERO_COKE,
                CHOCOLATE_CAKE);

        //when
        DecemberDiscountEvents decemberDiscountEvents = createDecemberDiscountEvents(16,
                orderedMenu);

        DiscountResultDto discountResultDto = decemberDiscountEvents.toDiscountResultDto(
                orderedMenu.calculateTotalAmount());
        Map<EventName, Integer> discountsStatistics = discountResultDto.discountResult();

        //then
        Assertions.assertThat(discountsStatistics.containsKey(GIFT_EVENT)).isTrue();
    }

    private OrderedMenu createOrderedMenu(Foods... foods) {
        Map<Foods, Integer> menus = new HashMap<>();
        for (Foods food : foods) {
            menus.put(food, 1);
        }
        return new OrderedMenu(menus);
    }

    private DecemberDiscountEvents createDecemberDiscountEvents(int day, OrderedMenu orderedMenu) {
        return new DecemberDiscountEvents(
                new VisitDay(day),
                new ChristmasDdayDiscount(EventName.CHRISTMAS_DISCOUNT),
                new SpecialDayDiscount(EventName.SPECIAL_DISCOUNT),
                new WeekdayDiscount(EventName.WEEKDAY_DISCOUNT, orderedMenu.getOrderedMenu()),
                new WeekendDiscount(EventName.WEEKEND_DISCOUNT, orderedMenu.getOrderedMenu()));
    }
}
