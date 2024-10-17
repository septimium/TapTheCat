import javax.swing.*;

public class L4_Orange {
    ImageIcon kitten;
    L4_Orange(double r) {
        if(r==1.5)
            this.kitten = new ImageIcon("res/kitten4HD.png");
        if(r==2.0)
            this.kitten = new ImageIcon("res/kitten4FULLHD.png");
    }

    public ImageIcon getKitten() {
        return kitten;
    }
}
