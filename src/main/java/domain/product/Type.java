package domain.product;

public enum Type {
    PRODUCE(2),
    ANIMAL(3);

    private final int reduceAmount;

    Type(int qtd) {
        reduceAmount = qtd;
    }

    public int getReduceAmount() {
        return reduceAmount;
    }
}
