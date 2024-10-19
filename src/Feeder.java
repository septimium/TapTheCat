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
        this.price = this.price.multiply(BigDecimal.valueOf(1.8));
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

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(BigDecimal multiplier) {
        this.multiplier = multiplier;
    }
}
