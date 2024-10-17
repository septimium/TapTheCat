import javax.swing.*;
import java.math.BigInteger;

public class L2_White extends Cat{
    L2_White() {
        this.kitten = new ImageIcon("res/kitten2HD.png");
        this.levelname = "Level 2: White Cat";
        this.level = 2;
        this.nextlevel = new BigInteger("10000000000");
    }
}
