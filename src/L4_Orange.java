import javax.swing.*;
import java.math.BigInteger;

public class L4_Orange extends Cat{
    L4_Orange() {
        this.kitten = new ImageIcon("res/kitten4HD.png");
        this.levelname = "Level 4: Orange Cat";
        this.level = 4;
        this.nextlevel = new BigInteger("2000000000000000");
    }
}
