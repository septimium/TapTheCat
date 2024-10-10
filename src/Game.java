import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Game {
    public static void main(String[] args){
        try {
            Font pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/FFFForward.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/FFFForward.ttf")));
        } catch (IOException | FontFormatException e) {
        }
        new Resolution();
    }
}
