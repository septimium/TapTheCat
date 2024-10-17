import javax.swing.*;

public class L3_Tortoiseshell {
    ImageIcon kitten;
    L3_Tortoiseshell(double r) {
        if(r==1.5)
            this.kitten = new ImageIcon("res/kitten3HD.png");
        if(r==2.0)
            this.kitten = new ImageIcon("res/kitten3FULLHD.png");
    }

    public ImageIcon getKitten() {
        return kitten;
    }
}
