import javax.swing.*;
import java.math.BigInteger;

public class L3_Tortoiseshell extends Cat{
    L3_Tortoiseshell() {
        this.kitten = new ImageIcon("res/kitten3HD.png");
        this.levelname = "Level 3: Tortoiseshell Cat";
        this.level = 3;
        this.nextlevel = new BigInteger("50000000000000");
    }
}
