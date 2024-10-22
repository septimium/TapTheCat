import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class HowToPlay {
    HowToPlay(){
        //Content
        JFrame res = new JFrame();
        res.setTitle("TapTheCat | How To Play");
        res.setSize(600, 600);
        ImageIcon background = new ImageIcon("res/htpbackground.png");
        res.setContentPane(new JLabel(background));
        res.setResizable(false);
        res.setLocationRelativeTo(null);
        res.setVisible(true);
        res.setLayout(new BorderLayout());
        JLabel text = new JLabel("How To Play", SwingConstants.CENTER);
        text.setFont(getFont().deriveFont(Font.PLAIN, 40));
        text.setForeground(Color.white);
        res.add(text, BorderLayout.NORTH);
        text.setBorder(new EmptyBorder(10,0,0,0));
        res.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //

        //TO DO
        //

        //Go Back Button
        JButton back = new JButton("<- Go Back");
        back.setFont(getFont().deriveFont(Font.PLAIN, 20));
        back.setFocusPainted(false);
        back.setBorderPainted(false);
        back.setBackground(new Color(0,0,0,0));
        back.setForeground(Color.white);
        back.setContentAreaFilled(false);
        back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        back.addActionListener(_ -> res.dispose());
        res.add(back, BorderLayout.SOUTH);
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
