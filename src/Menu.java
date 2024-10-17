import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class Menu {
    public Menu() {
        //Custom Font
        Font font = null;
        try {
            InputStream is = getClass().getResourceAsStream("/FFFForward.TTF");
            font = Font.createFont(Font.TRUETYPE_FONT, is);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, is));
        } catch (IOException | FontFormatException e) {
        }

        //Content
        JFrame res = new JFrame();
        res.setTitle("TapTheCat | Main Menu");
        res.setSize(400, 600);
        res.setLayout(new GridLayout(4,1));
        JLabel text = new JLabel("<HTML><CENTER>Choose resolution:</CENTER><HTML>");
        text.setFont(font.deriveFont(Font.PLAIN, 40));
        res.add(text);
        //

        //First Button
        JButton b1 = new JButton("PLAY");
        b1.setFont(font.deriveFont(Font.PLAIN, 18));
        b1.setFocusPainted(false);
        b1.setSize(200,50);
        b1.addActionListener(e -> {
            res.dispose();
            Game g = new Game(1280,720);
        });
        res.add(b1);

        //Second Button
        JButton b2 = new JButton("HOW TO PLAY");
        b2.setFont(font.deriveFont(Font.PLAIN, 18));
        b2.setFocusPainted(false);
        b2.setSize(200,50);
        b2.addActionListener(e -> {
//            TO DO
        });
        res.add(b2);

        //Third Button
        JButton b3 = new JButton("EXIT");
        b3.setFont(font.deriveFont(Font.PLAIN, 18));
        b3.setFocusPainted(false);
        b3.setSize(200,50);
        b3.addActionListener(e -> {
            res.dispose();
        });
        res.add(b3);
        //

        //Also content
        res.setResizable(false);
        res.setLocationRelativeTo(null);
        res.setVisible(true);
        res.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
