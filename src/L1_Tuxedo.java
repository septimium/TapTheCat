import javax.swing.*;
import java.math.BigInteger;

public class L1_Tuxedo extends Cat{
    L1_Tuxedo() {
        this.kitten = new ImageIcon("res/kitten1HD.png");
        this.levelname = "Level 1: Tuxedo Cat";
        this.level = 1;
        this.nextlevel = BigInteger.valueOf(1000000);
    }
}
