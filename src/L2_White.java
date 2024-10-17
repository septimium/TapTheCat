import javax.swing.*;

public class L2_White {
    ImageIcon kitten;
    L2_White(double r) {
        if(r==1.5)
            this.kitten = new ImageIcon("res/kitten2HD.png");
        if(r==2.0)
            this.kitten = new ImageIcon("res/kitten2FULLHD.png");
    }

    public ImageIcon getKitten() {
        return kitten;
    }
}
