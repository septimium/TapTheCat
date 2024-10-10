import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.*;

public class UI {
    double kibbles;
    double multiplier;
    double feeder;

    public UI(double r, int w, int h){
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

        //Frame initialization
        JFrame ui = new JFrame();
        ui.setLayout(new BorderLayout());
        ui.setSize(w, h);
        ui.getContentPane().setBackground(Color.black);
        //

        //Score Label
        JLabel score = new JLabel("Kibbles: " + fmt.format(getKibbles()), SwingConstants.CENTER);
        score.setBounds(0, 20, w, (int)(r*65));
        score.setBackground(Color.black);
        score.setForeground(Color.white);
        score.setFont(font.deriveFont(Font.PLAIN, (int)(r*45)));
        //

        //Multiplier and Feeder Label
        JLabel multiplier = new JLabel("Multiplier: " + "x" + fmt.format(getMultiplier()) + "    " + "Feeder: " + fmt.format(getFeeder()) + "/s");
        multiplier.setHorizontalAlignment(SwingConstants.CENTER);
        multiplier.setBounds(0, (int)(r*75), w, (int)(r*30));
        multiplier.setBackground(Color.black);
        multiplier.setForeground(Color.white);
        multiplier.setFont(font.deriveFont(Font.PLAIN, (int)(r*20)));
        //

        ui.add(score, BorderLayout.NORTH);
        ui.add(multiplier, BorderLayout.NORTH);

        ImageIcon kittenimage = new ImageIcon(getClass().getClassLoader().getResource("kitten2.png"));
        JButton kitten = new JButton();
        kitten.setBounds(0, 200, 550, 500);
        kitten.addActionListener(e -> {
            setKibbles(getKibbles() + (1 * getMultiplier()));
            setMultiplier(getMultiplier() * 2);
            multiplier.setText("Multiplier: " + "x" + fmt.format(getMultiplier()) + "    " + "Feeder: " + fmt.format(getFeeder()) + "/s");
            score.setText("Kibbles: " + fmt.format(getKibbles()));
            System.out.println(getMultiplier());
            System.out.println(getKibbles());
        });
        kitten.setBorder(null);
        kitten.setIcon(kittenimage);
        kitten.setFocusPainted(false);
        kitten.setBorderPainted(false);
        kitten.setBorder(null);
        kitten.setContentAreaFilled(false);
        kitten.setBackground(Color.BLACK);
        kitten.setSize(550, 500);
        ui.add(kitten);

        //More Frame settings
        ui.setLayout(null);
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
