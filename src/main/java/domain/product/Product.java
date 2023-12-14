package domain.product;

public class Product {
    public static final int INITIAL_QUALITY = 10;
    private static final int SPOILING_PRODUCT_QUALITY_LIMIT = 2;
    private final String item;
    private final Type type;
    private int quality;
    private int amount;

    public Product(String item, int amount, Type type) {
        this.item = item;
        this.amount = amount;
        this.type = type;
        this.quality = INITIAL_QUALITY;
    }

    public String getItem() {
        return item;
    }

    public boolean isEmptyStock() {
        return amount == 0;
    }

    public int getStock() {return amount;}

    public void setStock(int amount) {this.amount = amount;}

    public int getQuality() {
        return quality;
    }

    public void decreaseQuality() {
        quality -= type.getReduceAmount();
    }

    public boolean isSpoiling() {
        return quality <= SPOILING_PRODUCT_QUALITY_LIMIT;
    }
}
