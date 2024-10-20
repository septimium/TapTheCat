import java.math.BigDecimal;

public class Zoomies {
    BigDecimal price;
    String name;
    BigDecimal multiplier;
    BigDecimal amount;

    public Zoomies(BigDecimal price, String name, BigDecimal multiplier, BigDecimal amount) {
        this.price = price;
        this.name = name;
        this.multiplier = multiplier;
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void priceIncrease() {
        this.price = this.price.multiply(BigDecimal.valueOf(1.5));
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
