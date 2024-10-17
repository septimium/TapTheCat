import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.text.*;

public class Game {
    double kibbles;
    double zoomies;
    double feeder;

    public Game(int w, int h){
        this.kibbles = 0;
        this.zoomies = 1;
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
        JLabel multiplier = new JLabel("Zoomies: " + "x" + fmt.format(getZoomies()) + "    " + "Feeder: " + fmt.format(getFeeder()) + "/s");
        multiplier.setHorizontalAlignment(SwingConstants.CENTER);
        multiplier.setBackground(Color.black);
        multiplier.setForeground(Color.white);
        multiplier.setFont(font.deriveFont(Font.PLAIN, 42));
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
        kitten.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        kitten.setBounds(15,100, 410, 300);
        kitten.setIcon(kittenimage);
        kitten.setFocusPainted(false);
        kitten.setBorderPainted(false);
        kitten.setContentAreaFilled(false);
        kitten.setBackground(Color.BLACK);
        p.add(kitten);
        p.setVisible(true);
        ui.add(p, BorderLayout.WEST);
        //

        //Shops buttons
        JPanel shops = new JPanel();
        shops.setLayout(new GridLayout(5,1, 0, 0));
        shops.setPreferredSize(new Dimension(450,300));
        shops.setOpaque(false);
        JButton multipliers = new JButton("Get Zoomies");
        multipliers.setFont(font.deriveFont(Font.PLAIN, 40));
        multipliers.setFocusPainted(false);
        JButton feeders = new JButton("Get Feeders");
        feeders.setFocusPainted(false);
        feeders.setFont(font.deriveFont(Font.PLAIN, 40));
        JButton empty1 = new JButton();
        JButton empty2 = new JButton();
        JButton empty3 = new JButton();
        empty1.setBackground(Color.black);
        empty1.setBorderPainted(false);
        empty1.setFocusPainted(false);
        empty1.setContentAreaFilled(false);
        empty2.setBorderPainted(false);
        empty2.setFocusPainted(false);
        empty2.setContentAreaFilled(false);
        empty3.setBorderPainted(false);
        empty3.setFocusPainted(false);
        empty3.setContentAreaFilled(false);
        empty2.setBackground(Color.black);
        empty3.setBackground(Color.black);
        shops.add(empty1);
        shops.add(multipliers);
        shops.add(empty2);
        shops.add(feeders);
        shops.add(empty3);
        ui.add(shops, BorderLayout.CENTER);
        //

        //Display the cat
        JPanel p2 = new JPanel();
        p2.setLayout(null);
        p2.setPreferredSize(new Dimension(450,300));
        p2.setOpaque(false);
        ImageIcon kittenimagelocked = level1.getKitten();
        JButton kittenL = new JButton();
        kittenL.setBounds(15,100, 410, 300);
        kittenL.setIcon(kittenimage);
        kittenL.setFocusPainted(false);
        kittenL.setBorderPainted(false);
        kittenL.setContentAreaFilled(false);
        kittenL.setBackground(Color.BLACK);
        p2.add(kittenL);
        p2.setVisible(true);
        ui.add(p2, BorderLayout.EAST);

        kitten.addActionListener(e -> {
            setKibbles(getKibbles() + (1 * getZoomies()));
            setFeeder(15);
            multiplier.setText("Zoomies: " + "x" + fmt.format(getZoomies()) + "    " + "Feeder: " + fmt.format(getFeeder()) + "/s");
            score.setText("Kibbles: " + fmt.format(getKibbles()));
        });

        //Feeder function
        int delay = 1000; //milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                setKibbles(getKibbles() + getFeeder());
                multiplier.setText("Zoomies: " + "x" + fmt.format(getZoomies()) + "    " + "Feeder: " + fmt.format(getFeeder()) + "/s");
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

    public double getZoomies() {
        return zoomies;
    }

    public void setZoomies(double zoomies) {
        this.zoomies = zoomies;
    }

    public double getFeeder() {
        return feeder;
    }

    public void setFeeder(double feeder) {
        this.feeder = feeder;
    }
}
