import javax.swing.*;

public class L4_Orange {
    ImageIcon kitten;
    String levelname;
    L4_Orange() {
        this.kitten = new ImageIcon("res/kitten4HD.png");
        this.levelname = "Level 4: Orange Cat";
    }

    public ImageIcon getKitten() {
        return kitten;
    }

    public String getLevelName() {
        return levelname;
    }
}
