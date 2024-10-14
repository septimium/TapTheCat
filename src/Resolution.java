import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class Resolution {
    public Resolution() {
        //Custom Font
        Font font = null;
        try {
            InputStream is = getClass().getResourceAsStream("/FFFForward.TTF");
            font = Font.createFont(Font.TRUETYPE_FONT, is);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, is));
        } catch (IOException | FontFormatException e) {
        }

        JFrame res = new JFrame();
        res.setTitle("TapTheCat: Choose Resolution");
        res.setSize(400, 600);
        res.setLayout(new GridLayout(3,1));
        JLabel text = new JLabel("<HTML><CENTER>Choose resolution:</CENTER><HTML>");
        text.setFont(font.deriveFont(Font.PLAIN, 40));
        res.add(text);

        JButton b1 = new JButton("1280x720 | HD");
        b1.setFont(font.deriveFont(Font.PLAIN, 18));
        b1.setFocusPainted(false);
        b1.setSize(200,50);
        b1.addActionListener(e -> {
            res.dispose();
            UI u = new UI(1.5,1280,720);
        });
        res.add(b1);


        JButton b2 = new JButton("1920x1080 | FULL HD");
        b2.setFont(font.deriveFont(Font.PLAIN, 18));
        b2.setFocusPainted(false);
        b2.setSize(200,50);
        b2.addActionListener(e -> {
            res.dispose();
            UI u = new UI(2,1920,1080);
        });
        res.add(b2);


        res.setResizable(false);
        res.setLocationRelativeTo(null);
        res.setVisible(true);
        res.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
