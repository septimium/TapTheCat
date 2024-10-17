import javax.swing.*;
import java.math.BigInteger;

public class L5_Siamese extends Cat{
    L5_Siamese() {
        this.kitten = new ImageIcon("res/kitten5HD.png");
        this.levelname = "Level 5: Siamese Cat";
        this.level = 5;
        this.nextlevel = new BigInteger("600000000000000000");
    }
}
