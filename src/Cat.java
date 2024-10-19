import javax.swing.*;
import java.math.BigDecimal;

public class Cat {
    ImageIcon kitten;
    String levelname;
    int level;
    BigDecimal nextlevel;

    public BigDecimal getNextlevel() {
        return nextlevel;
    }

    public void setNextlevel(BigDecimal nextlevel) {
        this.nextlevel = nextlevel;
    }

    public ImageIcon getKitten() {
        return kitten;
    }

    public void setKitten(ImageIcon kitten) {
        this.kitten = kitten;
    }

    public String getLevelname() {
        return levelname;
    }

    public void setLevelname(String levelname) {
        this.levelname = levelname;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
