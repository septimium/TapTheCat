import javax.swing.*;
import java.math.BigDecimal;

public class L2_White extends Cat{
    L2_White() {
        this.kitten = new ImageIcon("res/kitten2HD.png");
        this.levelname = "Level 2: White Cat";
        this.level = 2;
        this.nextlevel = new BigDecimal("10000000000");
    }
}
