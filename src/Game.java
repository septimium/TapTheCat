import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.text.*;

public class Game {
    double kibbles;
    double multiplier;
    double feeder;

    public Game(double r, int w, int h){
        this.kibbles = 0;
        this.multiplier = 1;
        this.feeder = 0.0;

        //Custom Font
        Font font = null;
        try {
            InputStream is = getClass().getResourceAsStream("/FFFForward.TTF");
            font = Font.createFont(Font.TRUETYPE_FONT, is);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, is));
        } catch (IOException | FontFormatException e) {
        }

        //Number formatting for better visuals
        String[] compactPatterns
                = {"", "", "", "0K", "00K", "000K", "0M", "00M", "000M",
                "0B", "00B", "000B", "0T", "00T", "000T", "0Q", "00Q", "000Q",};
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance();
        CompactNumberFormat fmt
                = new CompactNumberFormat(decimalFormat.toPattern(),
                decimalFormat.getDecimalFormatSymbols(), compactPatterns);
        fmt.setMinimumFractionDigits(1);
        //

        //Layout initialization
        BorderLayout l = new BorderLayout();
        //

        //Frame initialization
        JFrame ui = new JFrame();
        ui.setTitle("TapTheCat");
        ui.setLayout(l);
        ui.setSize(w, h);
        ui.getContentPane().setBackground(Color.black);
        //


        //Score Label
        JLabel score = new JLabel("Kibbles: " + fmt.format(getKibbles()), SwingConstants.CENTER);
        score.setBorder(new EmptyBorder(20,0,0,0));
        score.setBackground(Color.black);
        score.setForeground(Color.white);
        score.setFont(font.deriveFont(Font.PLAIN, (int)(r*45)));
        ui.add(score, BorderLayout.NORTH);
        //

        //Multiplier and Feeder Label
        JLabel multiplier = new JLabel("Multiplier: " + "x" + fmt.format(getMultiplier()) + "    " + "Feeder: " + fmt.format(getFeeder()) + "/s");
        multiplier.setHorizontalAlignment(SwingConstants.CENTER);
        multiplier.setBounds(0, (int)(r*75), w, (int)(r*30));
        multiplier.setBackground(Color.black);
        multiplier.setForeground(Color.white);
        multiplier.setFont(font.deriveFont(Font.PLAIN, (int)(r*20)));
        multiplier.setBorder(new EmptyBorder((int)(r*6),0,(int)(r*4),0));
        ui.add(multiplier, BorderLayout.SOUTH);
        //

        JPanel p = new JPanel();
        p.setLayout(null);
        p.setPreferredSize(new Dimension(450,300));
        p.setOpaque(false);
        L1_Tuxedo level1 = new L1_Tuxedo(r);
        ImageIcon kittenimage = level1.getKitten();
        JButton kitten = new JButton();
        kitten.setBounds((int)(r*10),(int)(r*65), 410, 300);
        kitten.addActionListener(e -> {
            setKibbles(getKibbles() + (1 * getMultiplier()));
            multiplier.setText("Multiplier: " + "x" + fmt.format(getMultiplier()) + "    " + "Feeder: " + fmt.format(getFeeder()) + "/s");
            score.setText("Kibbles: " + fmt.format(getKibbles()));
            System.out.println(getMultiplier());
            System.out.println(getKibbles());
        });
        kitten.setIcon(kittenimage);
        kitten.setFocusPainted(false);
        kitten.setBorderPainted(false);
        kitten.setContentAreaFilled(false);
        kitten.setBackground(Color.BLACK);
        p.add(kitten, BorderLayout.CENTER);
        p.setVisible(true);
        ui.add(p, BorderLayout.WEST);

        //More Frame settings
        ui.setResizable(false);
        ui.setVisible(true);
        ui.setLocationRelativeTo(null);
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //

    }

    public double getKibbles() {
        return kibbles;
    }

    public void setKibbles(double kibbles) {
        this.kibbles = kibbles;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }

    public double getFeeder() {
        return feeder;
    }

    public void setFeeder(double feeder) {
        this.feeder = feeder;
    }
}
