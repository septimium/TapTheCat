import javax.swing.*;

public class L3_Tortoiseshell {
    ImageIcon kitten;
    String levelname;
    L3_Tortoiseshell() {
        this.kitten = new ImageIcon("res/kitten3HD.png");
        this.levelname = "Level 3: Tortoiseshell Cat";
    }

    public ImageIcon getKitten() {
        return kitten;
    }

    public String getLevelName() {
        return levelname;
    }
}
