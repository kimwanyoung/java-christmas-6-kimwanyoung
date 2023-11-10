package christmas.domain.menu;

import static christmas.domain.menu.Foods.BARBECUE_RIB;
import static christmas.domain.menu.Foods.CHOCOLATE_CAKE;
import static christmas.domain.menu.Foods.ICECREAM;
import static christmas.domain.menu.Foods.SEAFOOD_PASTA;

import java.util.Set;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class FoodCategoryTest {

    @ParameterizedTest(name = "기대 값 : {1}")
    @MethodSource("generateFoodsSet")
    @DisplayName("디저트 개수를 반환한다.")
    void 디저트_개수_구하는_기능_테스트(Set<Foods> foods, int expected) {
        //when
        int actual = FoodCategory.calculateFoodCountInCategory(FoodCategory.DESSERT, foods);

        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> generateFoodsSet() {
        return Stream.of(
                Arguments.of(Set.of(CHOCOLATE_CAKE, ICECREAM), 2),
                Arguments.of(Set.of(BARBECUE_RIB, ICECREAM), 1),
                Arguments.of(Set.of(BARBECUE_RIB, SEAFOOD_PASTA, CHOCOLATE_CAKE, ICECREAM), 2)
        );
    }
}
