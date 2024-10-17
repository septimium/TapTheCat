import javax.swing.*;

public class L1_Tuxedo {
    ImageIcon kitten;
    L1_Tuxedo(double r) {
        if(r==1.5)
            this.kitten = new ImageIcon("res/kitten1HD.png");
        if(r==2.0)
            this.kitten = new ImageIcon("res/kitten1FULLHD.png");
    }

    public ImageIcon getKitten() {
        return kitten;
    }
}
