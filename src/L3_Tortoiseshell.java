import javax.swing.*;
import java.math.BigDecimal;

public class L3_Tortoiseshell extends Cat{
    L3_Tortoiseshell() {
        this.kitten = new ImageIcon(getClass().getResource("res/kitten3HD.png"));
        this.levelname = "Level 3: Tortoiseshell Cat";
        this.level = 3;
        this.nextlevel = new BigDecimal("50000000000000");
    }
}
