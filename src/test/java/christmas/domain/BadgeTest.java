package christmas.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BadgeTest {

    @ParameterizedTest(name = "할인 금액 : {0}")
    @CsvSource(value = {"-20000,산타", "-10000,트리", "-5000,별"})
    @DisplayName("할인 금액에 맞는 배지를 반환한다.")
    void 배지_반환_테스트(int discountAmount, String expected) {
        //when
        String actual = Badge.calculateBadge(discountAmount);

        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
