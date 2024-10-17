import javax.swing.*;

public class L5_Siamese {
    ImageIcon kitten;
    L5_Siamese(double r) {
        if(r==1.5)
            this.kitten = new ImageIcon("res/kitten5HD.png");
        if(r==2.0)
            this.kitten = new ImageIcon("res/kitten5FULLHD.png");
    }

    public ImageIcon getKitten() {
        return kitten;
    }
}
