import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class HowToPlay {
    HowToPlay(){
        //Content
        JFrame res = new JFrame();
        res.setTitle("TapTheCat | How To Play");
        res.setSize(600, 600);
        ImageIcon background = new ImageIcon(getClass().getResource("res/htpbackground.png"));
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

        //Text
        JTextPane text2 = new JTextPane();
        text2.setEditable(false);
        text2.setText(" \n"+
                        "Click the kitten to obtain Kibbles! (1 click = 1 Kibble) "+
                        "Use Kibbles to buy click multipliers (ZOOMIES) and clicks-per-second (FEEDERS) which will help you gain Kibbles faster! \n\n"+
                        "There are 5 unlockable levels including endgame. Every unlockable level requires a number of Kibbles. Unlock all levels to win the game! \n\n"+
                        "Have fun and enjoy tapping! \n\n"+
                        "Number formatting: \n" +
                        "1K = 1000 - one thousand \n" +
                        "1M = 1000K - one million \n" +
                        "1B = 1000M - one billion \n" +
                        "1T = 1000B - one trillion \n" +
                        "1Q = 1000T - one quadrillion \n\n\n" +
                        "github.com/septimium"
                );
        centerText(text2);
        text2.setFont(getFont().deriveFont(Font.PLAIN, 15));
        text2.setForeground(Color.white);
        text2.setBackground(new Color(0,0,0,0));
        text2.setOpaque(false);
        text2.setHighlighter(null);
        res.add(text2, BorderLayout.CENTER);
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

    //Center Text Function
    public static void centerText(JTextPane pane) {
        StyledDocument doc = pane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
    }//
}
