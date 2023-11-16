package christmas.domain;

public enum Badge {
    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000);

    private final String name;
    private final int discountAmount;

    Badge(String name, int discountAmount) {
        this.name = name;
        this.discountAmount = discountAmount;
    }

    public static String calculateBadge(int discountAmount) {
        for (Badge badge : Badge.values()) {
            if (-discountAmount >= badge.discountAmount) {
                return badge.name;
            }
        }
        return null;
    }
}
