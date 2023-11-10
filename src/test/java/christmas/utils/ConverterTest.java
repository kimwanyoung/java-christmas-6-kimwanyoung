package christmas.utils;

import static christmas.constants.ExceptionMessage.INVALID_MENU_ORDER_ERROR_MESSAGE;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ConverterTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "티본스테이크-16,바비큐립-2,초코케이크-2,제로콜라-2",
            "티본스테이크-16,바비큐립-21,초코케이크-2,제로콜라-2",
            "티본스테이크-16,바비큐립-2,초코케이크-23,제로콜라-22"
    })
    @DisplayName("메뉴가 20개 초과 입력되면 예외가 발생한다.")
    void 메뉴_20개_초과_입력_예외_테스트(String input) {
        //when,then
        Assertions.assertThatThrownBy(() -> Converter.convertToMenuResult(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_MENU_ORDER_ERROR_MESSAGE);
    }
}
