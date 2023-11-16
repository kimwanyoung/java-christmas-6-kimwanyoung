package christmas.domain;

import static christmas.constants.ExceptionMessage.INVALID_MENU_ORDER_ERROR_MESSAGE;

import christmas.domain.menu.Foods;
import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class OrderedMenuTest {

    @ParameterizedTest
    @MethodSource("generateOrderFoods")
    @DisplayName("음료만 주만하면 예외가 발생한다.")
    void 음료만_주문_시_예외_테스트(Map<Foods, Integer> orderedMenu) {
        //when, then
        Assertions.assertThatThrownBy(() -> new OrderedMenu(orderedMenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_MENU_ORDER_ERROR_MESSAGE);
    }

    @ParameterizedTest
    @MethodSource("generateOrderFoodsOverTwenty")
    @DisplayName("음료만 주만하면 예외가 발생한다.")
    void 음식_주문_20개_초과_예외_테스트(Map<Foods, Integer> orderedMenu) {
        //when, then
        Assertions.assertThatThrownBy(() -> new OrderedMenu(orderedMenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_MENU_ORDER_ERROR_MESSAGE);
    }


    static Stream<Arguments> generateOrderFoods() {
        return Stream.of(
                Arguments.of(
                        Map.of(Foods.CHAMPAGNE, 1, Foods.ZERO_COKE, 2)
                ),
                Arguments.of(
                        Map.of(Foods.RED_WINE, 1, Foods.ZERO_COKE, 2)
                ),
                Arguments.of(
                        Map.of(Foods.RED_WINE, 1, Foods.ZERO_COKE, 2, Foods.CHAMPAGNE, 1)
                )
        );
    }

    static Stream<Arguments> generateOrderFoodsOverTwenty() {
        return Stream.of(
                Arguments.of(
                        Map.of(Foods.TAPAS, 16, Foods.ZERO_COKE, 6)
                ),
                Arguments.of(
                        Map.of(Foods.T_BONE_STEAK, 11, Foods.ZERO_COKE, 21)
                ),
                Arguments.of(
                        Map.of(Foods.CHAMPAGNE, 12, Foods.CAESAR_SALAD, 21, Foods.CHRISTMAS_PASTA,
                                13)
                )
        );
    }
}
