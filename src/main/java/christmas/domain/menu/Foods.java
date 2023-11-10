package christmas.domain.menu;

public enum Foods {

    //애피타이저
    MUSHROOM_SOUP("양송이수프", 6000),
    TAPAS("타파스", 5500),
    CAESAR_SALAD("시저샐러드", 8000),

    //메인코스
    T_BONE_STEAK("티본스테이크", 55000),
    BARBECUE_RIB("바비큐립", 54000),
    SEAFOOD_PASTA("해산물파스타", 35000),
    CHRISTMAS_PASTA("크리스마스파스타", 25000),

    //디저트
    CHOCOLATE_CAKE("초코케이크", 15000),
    ICECREAM("아이스크림", 5000),

    //음료
    ZERO_COKE("제로콜라", 3000),
    RED_WINE("레드와인", 60000),
    CHAMPAGNE("샴페인", 25000);

    private final String name;
    private final int price;

    Foods(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static Foods convertStringToFoods(String menuName) {
        for (Foods food : Foods.values()) {
            if (food.name.equals(menuName)) {
                return food;
            }
        }
        return null;
    }
}
