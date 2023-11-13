package christmas.domain.discount;

import static christmas.domain.EventName.WEEKDAY_DISCOUNT;

import christmas.domain.VisitDay;
import christmas.domain.menu.Foods;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class WeekdayDiscountTest {

    @ParameterizedTest(name = "입력 날짜 : {0}, 기대 값 : {1}")
    @CsvSource(value = {"3,true", "4,true", "5,true", "1,false", "2, false"})
    @DisplayName("평일 할인 날짜가 맞는지 확인한다.")
    void 평일_할인_날짜_테스트(int day, boolean expected) {
        //given
        Map<Foods, Integer> foods = new HashMap<>();
        WeekdayDiscount weekdayDiscount = new WeekdayDiscount(WEEKDAY_DISCOUNT, foods);

        //when
        boolean actual = weekdayDiscount.isDiscountDay(new VisitDay(day));

        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
