import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.*;

public class Game {
    BigInteger kibbles;
    BigInteger zoomies;
    BigInteger feeder;
    int currentlevel;
    Cat currentcat;
    public Game(int w, int h){
        this.kibbles = BigInteger.valueOf(0);
        this.zoomies = BigInteger.valueOf(1);
        this.feeder = BigInteger.valueOf(0);
        this.currentlevel = new L1_Tuxedo().getLevel();
        this.currentcat = new L1_Tuxedo();

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
        ui.setTitle("TapTheCat | "+currentcat.getLevelname());
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
        Cat cat1 = new L1_Tuxedo();
        int level1 = cat1.getLevel();
        Cat cat2 = new L2_White();
        int level2 = cat2.getLevel();
        Cat cat3 = new L3_Tortoiseshell();
        int level3 = cat3.getLevel();
        Cat cat4 = new L4_Orange();
        int level4 = cat4.getLevel();
        Cat cat5 = new L5_Siamese();
        int level5 = cat5.getLevel();
        //

        //Display the cat
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setPreferredSize(new Dimension(450,300));
        p.setOpaque(false);
        ImageIcon kittenimage = currentcat.getKitten();
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
        multipliers.setBorderPainted(false);
        multipliers.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        JButton feeders = new JButton("Get Feeders");
        feeders.setFocusPainted(false);
        feeders.setBorderPainted(false);
        feeders.setFont(font.deriveFont(Font.PLAIN, 40));
        feeders.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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

        //Unlock levels
        JPanel p2 = new JPanel();
        p2.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        p2.setPreferredSize(new Dimension(450,300));
        p2.setOpaque(false);
        JLabel clevel = new JLabel(currentcat.getLevelname(),  SwingConstants.CENTER);
        clevel.setForeground(Color.white);
        clevel.setFont(font.deriveFont(Font.PLAIN, 20));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        p2.add(clevel, c);
        ImageIcon kittenimagelocked = new ImageIcon("res/nextkitten.png");
        JButton kittenL = new JButton();
        kittenL.setBounds(15,100, 410, 300);
        kittenL.setIcon(kittenimagelocked);
        kittenL.setFocusPainted(false);
        kittenL.setBorderPainted(false);
        kittenL.setContentAreaFilled(false);
        kittenL.setBackground(Color.BLACK);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        p2.add(kittenL, c);
        JButton unlock = new JButton("Unlock next cat: "+fmt.format(currentcat.getNextlevel()));
        unlock.setFont(font.deriveFont(Font.PLAIN, 20));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        unlock.setFocusPainted(false);
        unlock.setBorderPainted(false);
        unlock.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Font finalFont = font;
        unlock.addActionListener(e ->{
            if(getKibbles().compareTo(currentcat.getNextlevel()) > 0){
                switch(currentlevel) {
                    case 1:
                        setCurrentlevel(2);
                        setCurrentcat(cat2);
                        ui.setTitle("TapTheCat | "+currentcat.getLevelname());
                        clevel.setText(currentcat.getLevelname());
                        unlock.setText("Unlock next cat: "+fmt.format(currentcat.getNextlevel()));
                        kitten.setIcon(currentcat.getKitten());
                        break;
                    case 2:
                        setCurrentlevel(3);
                        setCurrentcat(cat3);
                        ui.setTitle("TapTheCat | "+currentcat.getLevelname());
                        clevel.setText(currentcat.getLevelname());
                        unlock.setText("Unlock next cat: "+fmt.format(currentcat.getNextlevel()));
                        kitten.setIcon(currentcat.getKitten());
                        break;
                    case 3:
                        setCurrentlevel(4);
                        setCurrentcat(cat4);
                        ui.setTitle("TapTheCat | "+currentcat.getLevelname());
                        clevel.setText(currentcat.getLevelname());
                        unlock.setText("Unlock next cat: "+fmt.format(currentcat.getNextlevel()));
                        kitten.setIcon(currentcat.getKitten());
                        break;
                    case 4:
                        setCurrentlevel(5);
                        setCurrentcat(cat5);
                        ui.setTitle("TapTheCat | "+currentcat.getLevelname());
                        clevel.setText(currentcat.getLevelname());
                        unlock.setText("Buy Freedom: "+fmt.format(currentcat.getNextlevel()));
                        kitten.setIcon(currentcat.getKitten());
                        break;
                    case 5:
                        //TODO
                }
            }
            else{
                JPopupMenu pop = new JPopupMenu();
                JLabel l = new JLabel("INSUFFICIENT KIBBLES");
                l.setFont(finalFont.deriveFont(Font.PLAIN, 20));
                l.setForeground(Color.white);
                pop.setBorderPainted(false);
                pop.setBackground(new Color(0,0,0,0));
                pop.add(l);
                pop.show(p2, 85,370);

            }
        });
        p2.add(unlock, c);
        p2.setVisible(true);
        ui.add(p2, BorderLayout.EAST);

        //Zoomies function
        kitten.addActionListener(e -> {
            setKibbles(getKibbles().add(getZoomies()));
            multiplier.setText("Zoomies: " + "x" + fmt.format(getZoomies()) + "    " + "Feeder: " + fmt.format(getFeeder()) + "/s");
            score.setText("Kibbles: " + fmt.format(getKibbles()));
        });
        //

        //Feeder function
        int delay = 1000; //milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                setKibbles(getKibbles().add(getFeeder()));
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

    public BigInteger getKibbles() {
        return kibbles;
    }

    public void setKibbles(BigInteger kibbles) {
        this.kibbles = kibbles;
    }

    public BigInteger getZoomies() {
        return zoomies;
    }

    public void setZoomies(BigInteger zoomies) {
        this.zoomies = zoomies;
    }

    public BigInteger getFeeder() {
        return feeder;
    }

    public void setFeeder(BigInteger feeder) {
        this.feeder = feeder;
    }

    public int getCurrentlevel() {
        return currentlevel;
    }

    public void setCurrentlevel(int currentlevel) {
        this.currentlevel = currentlevel;
    }

    public Cat getCurrentcat() {
        return currentcat;
    }

    public void setCurrentcat(Cat currentcat) {
        this.currentcat = currentcat;
    }
}
