package christmas.domain.discount;

import static christmas.domain.EventName.CHRISTMAS_DISCOUNT;

import christmas.domain.VisitDay;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ChristmasDdayDiscountTest {

    @ParameterizedTest(name = "입력 날짜 : {0}, 기대 값 : {1}")
    @CsvSource(value = {"1,true", "2,true", "3,true", "26,false", "27, false", "28, false"})
    @DisplayName("크리스마스 디데이 할인 날짜가 맞는지 확인한다.")
    void 크리스마스_디데이_할인_날짜_테스트(int day, boolean expected) {
        //given
        ChristmasDdayDiscount christmasDdayDiscount = new ChristmasDdayDiscount(
                CHRISTMAS_DISCOUNT,
                new VisitDay(day));

        //when
        boolean actual = christmasDdayDiscount.isDiscountDay();

        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
