import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class Menu {
    public Menu() {
        //Frame content
        JFrame res = new JFrame();
        res.setTitle("TapTheCat | Main Menu");
        res.setSize(400, 600);
        ImageIcon background = new ImageIcon(getClass().getResource("res/menubackground.png"));
        res.setContentPane(new JLabel(background));
        res.setLayout(new GridLayout(4,1));
        JLabel text = new JLabel("TapTheCat", SwingConstants.CENTER);
        text.setFont(getFont().deriveFont(Font.PLAIN, 50));
        text.setForeground(Color.white);
        res.add(text);
        //

        //First Button
        JButton b1 = new JButton("PLAY");
        b1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b1.setFont(getFont().deriveFont(Font.PLAIN, 35));
        b1.setFocusPainted(false);
        b1.setContentAreaFilled(false);
        b1.setForeground(Color.white);
        b1.setBorder(BorderFactory.createMatteBorder(4,4,2,4,Color.white));
        b1.setSize(200,50);
        b1.addActionListener(_ -> {
            res.dispose();
            new Game(1280,720);
        });
        res.add(b1);

        //Second Button
        JButton b2 = new JButton("HOW TO PLAY");
        b2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b2.setFont(getFont().deriveFont(Font.PLAIN, 35));
        b2.setBorder(BorderFactory.createMatteBorder(2,4,2,4,Color.white));
        b2.setContentAreaFilled(false);
        b2.setFocusPainted(false);
        b2.setForeground(Color.white);
        b2.setSize(200,50);
        b2.addActionListener(_ -> new HowToPlay());
        res.add(b2);

        //Third Button
        JButton b3 = new JButton("EXIT");
        b3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b3.setFont(getFont().deriveFont(Font.PLAIN, 35));
        b3.setFocusPainted(false);
        b3.setBorder(BorderFactory.createMatteBorder(2,4,4,4,Color.white));
        b3.setSize(200,50);
        b3.setForeground(Color.white);
        b3.setContentAreaFilled(false);
        b3.addActionListener(_ -> res.dispose());
        res.add(b3);
        //

        //Frame content
        res.setResizable(false);
        res.setLocationRelativeTo(null);
        res.setVisible(true);
        res.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //
    }

    //Font function
    public Font getFont(){
        Font font = null;
        try {
            InputStream is = getClass().getResourceAsStream("/FFFForward.TTF");
            font = Font.createFont(Font.TRUETYPE_FONT, is);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, is));
        } catch (IOException | FontFormatException e) {
        }
        return font;
    }
    //
}
