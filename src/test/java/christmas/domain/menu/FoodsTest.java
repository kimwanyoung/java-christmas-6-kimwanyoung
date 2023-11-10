package christmas.domain.menu;

import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class FoodsTest {

    @ParameterizedTest(name = "입력 메뉴 : {0}")
    @MethodSource("generateFoods")
    @DisplayName("문자열 입력 시 Foods에 있는 메뉴로 변환한다.")
    void 문자열_메뉴_변환_테스트(String menuName, Foods expected) {
        //when
        Foods actual = Foods.convertStringToFoods(menuName);

        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest(name = "입력 메뉴 : {0}, 개수 : {1}")
    @MethodSource("generateFoodsAndCount")
    @DisplayName("음식의 개수를 받아 총 금액을 반환한다.")
    void 문자열_메뉴_변환_테스트(Foods food, int amount, int expected) {
        //when
        int actual = Foods.calculateFoodsAmount(food, amount);

        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> generateFoods() {
        return Stream.of(
                Arguments.of("바비큐립", Foods.BARBECUE_RIB),
                Arguments.of("티본스테이크", Foods.T_BONE_STEAK),
                Arguments.of("타파스", Foods.TAPAS)
        );
    }

    static Stream<Arguments> generateFoodsAndCount() {
        return Stream.of(
                Arguments.of(Foods.BARBECUE_RIB, 1, 54000),
                Arguments.of(Foods.T_BONE_STEAK, 2, 110000),
                Arguments.of(Foods.TAPAS, 2, 11000)
        );
    }
}
