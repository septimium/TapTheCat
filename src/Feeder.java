import java.math.BigDecimal;

public class Feeder {
    BigDecimal price;
    String name;
    BigDecimal multiplier;
    BigDecimal amount;

    public Feeder(BigDecimal price, String name, BigDecimal multiplier, BigDecimal amount) {
        this.price = price;
        this.name = name;
        this.multiplier = multiplier;
        this.amount = amount;
    }

    public void priceIncrease() {
        this.price = this.price.multiply(BigDecimal.valueOf(1.7));
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getMultiplier() {
        return multiplier;
    }

}
