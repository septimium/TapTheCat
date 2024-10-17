import javax.swing.*;

public class L5_Siamese {
    ImageIcon kitten;
    String levelname;
    L5_Siamese() {
        this.kitten = new ImageIcon("res/kitten5HD.png");
        this.levelname = "Level 5: Siamese Cat";
    }

    public ImageIcon getKitten() {
        return kitten;
    }

    public String getLevelName() {
        return levelname;
    }
}
