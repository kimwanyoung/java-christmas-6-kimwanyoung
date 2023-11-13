package christmas.utils;

import static christmas.constants.ExceptionMessage.INVALID_MENU_ORDER_ERROR_MESSAGE;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ConverterTest {

    @ParameterizedTest(name = "입력 값 : {0}")
    @ValueSource(strings = {"티본스테이크-1,티본스테이크-1", "바비큐립-2,바비큐립-1"})
    @DisplayName("중복된 메뉴 입력시 예외가 발생한다.")
    void 중복된_메뉴_입력_시_예외_테스트(String input) {
        //when, then
        Assertions.assertThatThrownBy(() -> Converter.convertInputToOrderedMenu(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_MENU_ORDER_ERROR_MESSAGE);
    }
}
