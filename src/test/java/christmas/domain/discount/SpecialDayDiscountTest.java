package christmas.domain.discount;

import static christmas.domain.EventName.SPECIAL_DISCOUNT;

import christmas.domain.VisitDay;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SpecialDayDiscountTest {

    @ParameterizedTest(name = "입력 날짜 : {0}, 기대 값 : {1}")
    @CsvSource(value = {"3,true", "10,true", "17,true", "26,false", "27, false", "28, false"})
    @DisplayName("특별 할인 날짜가 맞는지 확인한다.")
    void 특별_할인_날짜_테스트(int day, boolean expected) {
        //given
        SpecialDayDiscount specialDayDiscount = new SpecialDayDiscount(SPECIAL_DISCOUNT);

        //when
        boolean actual = specialDayDiscount.isDiscountDay(new VisitDay(day));

        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
