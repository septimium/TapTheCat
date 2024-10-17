import javax.swing.*;

public class L2_White {
    ImageIcon kitten;
    String levelname;
    L2_White() {
        this.kitten = new ImageIcon("res/kitten2HD.png");
        this.levelname = "Level 2: White Cat";
    }

    public ImageIcon getKitten() {
        return kitten;
    }

    public String getLevelName() {
        return levelname;
    }
}
