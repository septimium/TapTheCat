import javax.swing.*;

public class L1_Tuxedo {
    ImageIcon kitten;
    String levelname;
    L1_Tuxedo() {
        this.kitten = new ImageIcon("res/kitten1HD.png");
        this.levelname = "Level 1: Tuxedo Cat";
    }

    public ImageIcon getKitten() {
        return kitten;
    }

    public String getLevelName() {
        return levelname;
    }
}
