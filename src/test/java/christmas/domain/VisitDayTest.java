package christmas.domain;

import static christmas.view.InputView.INVALID_DAY_ERROR_MESSAGE;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class VisitDayTest {

    @ParameterizedTest(name = "입력 날짜 : {0}")
    @ValueSource(ints = {32, 33, 34, 35, 0, -1, -2})
    @DisplayName("범위 밖 날짜를 입력하면 예외가 발생한다.")
    void 날짜_예외_테스트(int day) {
        //when, then
        Assertions.assertThatThrownBy(() -> new VisitDay(day))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_DAY_ERROR_MESSAGE);
    }
}
