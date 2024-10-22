import javax.swing.*;
import java.math.BigDecimal;

public class L1_Tuxedo extends Cat{
    L1_Tuxedo() {
        this.kitten = new ImageIcon(getClass().getResource("res/kitten1HD.png"));
        this.levelname = "Level 1: Tuxedo Cat";
        this.level = 1;
        this.nextlevel = BigDecimal.valueOf(1000000);
    }
}
