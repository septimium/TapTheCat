import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.text.*;

public class Game {
    double kibbles;
    double multiplier;
    double feeder;

    public Game(int w, int h){
        this.kibbles = 0;
        this.multiplier = 1;
        this.feeder = 1;

        //Custom Font
        Font font = null;
        try {
            InputStream is = getClass().getResourceAsStream("/FFFForward.TTF");
            font = Font.createFont(Font.TRUETYPE_FONT, is);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, is));
        } catch (IOException | FontFormatException e) {
        }
        //

        //Number formatting for better visuals
        String[] compactPatterns = {"", "", "", "0K", "00K", "000K", "0M", "00M", "000M", "0B", "00B", "000B", "0T", "00T", "000T", "0Q", "00Q", "000Q",};
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance();
        CompactNumberFormat fmt = new CompactNumberFormat(decimalFormat.toPattern(), decimalFormat.getDecimalFormatSymbols(), compactPatterns);
        fmt.setMinimumFractionDigits(1);
        //

        //Frame initialization
        JFrame ui = new JFrame();
        ui.setTitle("TapTheCat");
        ui.setLayout(new BorderLayout());
        ui.setSize(w, h);
        ui.getContentPane().setBackground(Color.black);
        //


        //Score Label
        JLabel score = new JLabel("Kibbles: " + fmt.format(getKibbles()), SwingConstants.CENTER);
        score.setBorder(new EmptyBorder(20,0,0,0));
        score.setBackground(Color.black);
        score.setForeground(Color.white);
        score.setFont(font.deriveFont(Font.PLAIN, 68));
        ui.add(score, BorderLayout.NORTH);
        //

        //Multiplier and Feeder Label
        JLabel multiplier = new JLabel("Multiplier: " + "x" + fmt.format(getMultiplier()) + "    " + "Feeder: " + fmt.format(getFeeder()) + "/s");
        multiplier.setHorizontalAlignment(SwingConstants.CENTER);
        multiplier.setBounds(0, 113, w, 45);
        multiplier.setBackground(Color.black);
        multiplier.setForeground(Color.white);
        multiplier.setFont(font.deriveFont(Font.PLAIN, 30));
        multiplier.setBorder(new EmptyBorder(10,0,5,0));
        ui.add(multiplier, BorderLayout.SOUTH);
        //

        //Initialize the levels
        L1_Tuxedo level1 = new L1_Tuxedo();
        L2_White level2 = new L2_White();
        L3_Tortoiseshell level3 = new L3_Tortoiseshell();
        L4_Orange level4 = new L4_Orange();
        L5_Siamese level5 = new L5_Siamese();
        //

        //Display the cat
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setPreferredSize(new Dimension(450,300));
        p.setOpaque(false);

        ImageIcon kittenimage = level1.getKitten();
        JButton kitten = new JButton();
        kitten.setBounds(15,100, 410, 300);
        kitten.setIcon(kittenimage);
        kitten.setFocusPainted(false);
        kitten.setBorderPainted(false);
        kitten.setContentAreaFilled(false);
        kitten.setBackground(Color.BLACK);
        p.add(kitten, BorderLayout.CENTER);
        p.setVisible(true);
        ui.add(p, BorderLayout.WEST);
        //

        kitten.addActionListener(e -> {
            setKibbles(getKibbles() + (1 * getMultiplier()));
            setFeeder(15);
            multiplier.setText("Multiplier: " + "x" + fmt.format(getMultiplier()) + "    " + "Feeder: " + fmt.format(getFeeder()) + "/s");
            score.setText("Kibbles: " + fmt.format(getKibbles()));
        });

        //Feeder function
        int delay = 1000; //milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                setKibbles(getKibbles() + getFeeder());
                multiplier.setText("Multiplier: " + "x" + fmt.format(getMultiplier()) + "    " + "Feeder: " + fmt.format(getFeeder()) + "/s");
                score.setText("Kibbles: " + fmt.format(getKibbles()));
            }
        };
        new Timer(delay, taskPerformer).start();
        //


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
