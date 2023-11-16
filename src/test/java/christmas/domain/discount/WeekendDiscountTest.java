package christmas.domain.discount;

import static christmas.domain.EventName.WEEKEND_DISCOUNT;

import christmas.domain.OrderedMenu;
import christmas.domain.VisitDay;
import christmas.domain.menu.Foods;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class WeekendDiscountTest {

    @ParameterizedTest(name = "입력 날짜 : {0}, 기대 값 : {1}")
    @CsvSource(value = {"3,false", "4,false", "5,false", "1,true", "2, true"})
    @DisplayName("주말 할인 날짜가 맞는지 확인한다.")
    void 주말_할인_날짜_테스트(int day, boolean expected) {
        //given
        Map<Foods, Integer> foods = new HashMap<>();
        WeekendDiscount weekendDiscount = new WeekendDiscount(
                WEEKEND_DISCOUNT,
                new OrderedMenu(foods),
                new VisitDay(day));

        //when
        boolean actual = weekendDiscount.isDiscountDay();

        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
