package christmas.domain.menu;

import christmas.domain.OrderedMenu;
import java.util.HashMap;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FoodCategoryTest {

    @Test
    @DisplayName("디저트 개수를 반환한다.")
    void 디저트_개수_구하는_기능_테스트() {
        //given
        Map<Foods, Integer> menus = new HashMap<>();
        menus.put(Foods.BARBECUE_RIB, 1);
        menus.put(Foods.TAPAS, 1);
        menus.put(Foods.T_BONE_STEAK, 1);
        menus.put(Foods.CHOCOLATE_CAKE, 2);

        //when
        int actual = FoodCategory.calculateFoodCountInCategory(FoodCategory.DESSERT,
                new OrderedMenu(menus));

        //then
        Assertions.assertThat(actual).isEqualTo(2);
    }
}
