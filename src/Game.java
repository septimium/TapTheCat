import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.*;

public class Game {
    BigDecimal kibbles;
    BigDecimal zoomies;
    BigDecimal feeder;
    int currentlevel;
    Cat currentcat;
    public Game(int w, int h){
        this.kibbles = new BigDecimal("1000000000000000000");
        this.zoomies = BigDecimal.valueOf(1);
        this.feeder = BigDecimal.valueOf(0);
        this.currentlevel = new L1_Tuxedo().getLevel();
        this.currentcat = new L1_Tuxedo();

        //Frame initialization
        JFrame ui = new JFrame();
        ui.setTitle("TapTheCat | "+currentcat.getLevelname());
        ImageIcon background = new ImageIcon("res/background.png");
        JLabel bg = new JLabel(background);
        ui.setContentPane(bg);
        ui.setLayout(new BorderLayout());
        ui.setSize(w, h);
        //

        //Score Label
        JLabel score = new JLabel("Kibbles: " + getFMT().format(getKibbles()), SwingConstants.CENTER);
        score.setBorder(new EmptyBorder(20,0,0,0));
        score.setBackground(Color.black);
        score.setForeground(Color.white);
        score.setFont(getFont().deriveFont(Font.PLAIN, 68));
        ui.add(score, BorderLayout.NORTH);
        //

        //Multiplier and Feeder Label
        JLabel multiplier = new JLabel("Zoomies: " + "x" + getFMT().format(getZoomies()) + "    " + "Feeder: " + getFMT().format(getFeeder()) + "/s");
        multiplier.setHorizontalAlignment(SwingConstants.CENTER);
        multiplier.setBackground(Color.black);
        multiplier.setForeground(Color.white);
        multiplier.setFont(getFont().deriveFont(Font.PLAIN, 42));
        multiplier.setBorder(new EmptyBorder(10,0,5,0));
        ui.add(multiplier, BorderLayout.SOUTH);
        //

        //Initialize the rest of the levels
        Cat cat2 = new L2_White();
        Cat cat3 = new L3_Tortoiseshell();
        Cat cat4 = new L4_Orange();
        Cat cat5 = new L5_Siamese();
        //

        //Initiate the zoomies
        Zoomies laser = new Zoomies(BigDecimal.valueOf(100),"Pointing Laser", BigDecimal.valueOf(10), BigDecimal.valueOf(0));
        Zoomies scratcher = new Zoomies(BigDecimal.valueOf(50000),"Scratcher", BigDecimal.valueOf(5000), BigDecimal.valueOf(0));
        Zoomies fishing = new Zoomies(BigDecimal.valueOf(2000000),"Fishing Pole", BigDecimal.valueOf(500000), BigDecimal.valueOf(0));
        Zoomies crinkle = new Zoomies(BigDecimal.valueOf(1000000000),"Crinkle Ball", BigDecimal.valueOf(50000000), BigDecimal.valueOf(0));
        Zoomies mouse = new Zoomies(new BigDecimal("1000000000000"),"Mouse Toy", new BigDecimal("100000000000"), BigDecimal.valueOf(0));
        Zoomies catnip = new Zoomies(new BigDecimal("1000000000000000"),"Cat Nip", new BigDecimal("100000000000000"), BigDecimal.valueOf(0));
        //

        //Initiate the feeders
        Feeder human = new Feeder(BigDecimal.valueOf(10000), "Human Hand", BigDecimal.valueOf(200), BigDecimal.valueOf(0));
        Feeder owner = new Feeder(BigDecimal.valueOf(500000), "Owner Hand", BigDecimal.valueOf(10000), BigDecimal.valueOf(0));
        Feeder bowl = new Feeder(BigDecimal.valueOf(50000000), "Cat Bowl", BigDecimal.valueOf(1000000), BigDecimal.valueOf(0));
        Feeder timed = new Feeder(new BigDecimal("50000000000"), "Timed Feeder", BigDecimal.valueOf(1000000000), BigDecimal.valueOf(0));
        Feeder smart = new Feeder(new BigDecimal("50000000000000"), "Smart Feeder", new BigDecimal("1000000000000"), BigDecimal.valueOf(0));
        Feeder free = new Feeder(new BigDecimal("50000000000000000"), "Free Will", new BigDecimal("1000000000000000"), BigDecimal.valueOf(0));
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

        //Shop buttons
        JPanel shops = new JPanel();
        shops.setLayout(new GridLayout(5,1, 0, 0));
        shops.setPreferredSize(new Dimension(450,300));
        shops.setOpaque(false);
        JButton multipliers = new JButton("Buy Zoomies");
        multipliers.setFont(getFont().deriveFont(Font.PLAIN, 40));
        multipliers.setFocusPainted(false);
        multipliers.setBorderPainted(false);
        multipliers.setBackground(new Color(0,0,0,0));
        multipliers.setForeground(Color.white);
        multipliers.setContentAreaFilled(false);
        multipliers.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        multipliers.addActionListener(e -> {
            JPanel zoomiesshop = new JPanel();
            zoomiesshop.setLayout(new GridLayout(0,1,0,0));
            JButton zoomies1 = buttonZoomies(laser, zoomiesshop);
            zoomies1.addActionListener(e1 -> {
                if(getKibbles().compareTo(laser.getPrice()) >= 0){
                    eventZoomiesTrue(zoomies1, laser, multiplier, score);
                }
                else{
                    eventZoomiesFalse(zoomiesshop, 50);
                }
            });
            JButton zoomies2 = buttonZoomies(scratcher, zoomiesshop);
            zoomies2.addActionListener(e2 -> {
                if(getKibbles().compareTo(scratcher.getPrice()) >= 0){
                    eventZoomiesTrue(zoomies2, scratcher, multiplier, score);
                }
                else{
                    eventZoomiesFalse(zoomiesshop, 120);
                }
            });
            JButton zoomies3 = buttonZoomies(fishing, zoomiesshop);
            zoomies3.addActionListener(e3 -> {
                if(getKibbles().compareTo(fishing.getPrice()) >= 0){
                    eventZoomiesTrue(zoomies3, fishing, multiplier, score);
                }
                else{
                    eventZoomiesFalse(zoomiesshop, 190);
                }
            });
            JButton zoomies4 = buttonZoomies(crinkle, zoomiesshop);
            zoomies4.addActionListener(e4 -> {
                if(getKibbles().compareTo(crinkle.getPrice()) >= 0){
                    eventZoomiesTrue(zoomies4, crinkle, multiplier, score);
                }
                else{
                    eventZoomiesFalse(zoomiesshop, 260);
                }
            });
            JButton zoomies5 = buttonZoomies(mouse, zoomiesshop);
            zoomies5.addActionListener(e5 -> {
                if(getKibbles().compareTo(mouse.getPrice()) >= 0){
                    eventZoomiesTrue(zoomies5, mouse, multiplier, score);
                }
                else{
                    eventZoomiesFalse(zoomiesshop, 330);
                }
            });
            JButton zoomies6 = buttonZoomies(catnip, zoomiesshop);
            zoomies6.addActionListener(e6 -> {
                if(getKibbles().compareTo(catnip.getPrice()) >= 0){
                    eventZoomiesTrue(zoomies6, catnip, multiplier, score);
                }
                else{
                    eventZoomiesFalse(zoomiesshop, 400);
                }
            });
            JButton zoomies7 = new JButton("<- Go Back");
            zoomies7.setFont(getFont().deriveFont(Font.PLAIN, 20));
            zoomies7.setFocusPainted(false);
            zoomies7.setBorderPainted(false);
            zoomies7.setBackground(new Color(0,0,0,0));
            zoomies7.setForeground(Color.white);
            zoomies7.setContentAreaFilled(false);
            zoomies7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            zoomies7.addActionListener(d -> {
                zoomiesshop.setVisible(false);
                shops.setVisible(true);
            });
            zoomiesshop.add(zoomies7);
            zoomiesshop.setOpaque(false);
            shops.setVisible(false);
            ui.add(zoomiesshop, BorderLayout.CENTER);
        });
        JButton feeders = new JButton("Buy Feeders");
        feeders.setFocusPainted(false);
        feeders.setBorderPainted(false);
        feeders.setBackground(new Color(0,0,0,0));
        feeders.setForeground(Color.white);
        feeders.setContentAreaFilled(false);
        feeders.setFont(getFont().deriveFont(Font.PLAIN, 40));
        feeders.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        feeders.addActionListener(e -> {
            JPanel feedersshop = new JPanel();
            feedersshop.setLayout(new GridLayout(0,1,0,0));
            JButton feeder1 = buttonFeeder(human, feedersshop);
            feeder1.addActionListener(e1 -> {
                if(getKibbles().compareTo(human.getPrice()) >= 0){
                    eventFeederTrue(feeder1, human, multiplier, score);
                }
                else{
                    eventFeederFalse(feedersshop, 50);
                }
            });
            JButton feeder2 = buttonFeeder(owner, feedersshop);
            feeder2.addActionListener(e2 -> {
                if(getKibbles().compareTo(owner.getPrice()) >= 0){
                    eventFeederTrue(feeder2, owner, multiplier, score);
                }
                else{
                    eventFeederFalse(feedersshop, 120);
                }
            });
            JButton feeder3 = buttonFeeder(bowl, feedersshop);
            feeder3.addActionListener(e3 -> {
                if(getKibbles().compareTo(bowl.getPrice()) >= 0){
                    eventFeederTrue(feeder3, bowl, multiplier, score);
                }
                else{
                    eventFeederFalse(feedersshop, 190);
                }
            });
            JButton feeder4 = buttonFeeder(timed, feedersshop);
            feeder4.addActionListener(e4 -> {
                if(getKibbles().compareTo(timed.getPrice()) >= 0){
                    eventFeederTrue(feeder4, timed, multiplier, score);
                }
                else{
                    eventFeederFalse(feedersshop, 260);
                }
            });
            JButton feeder5 = buttonFeeder(smart, feedersshop);
            feeder5.addActionListener(e5 -> {
                if(getKibbles().compareTo(smart.getPrice()) >= 0){
                    eventFeederTrue(feeder5, smart, multiplier, score);
                }
                else{
                    eventFeederFalse(feedersshop, 330);
                }
            });
            JButton feeder6 = buttonFeeder(free, feedersshop);
            feeder6.addActionListener(e6 -> {
                if(getKibbles().compareTo(free.getPrice()) >= 0){
                    eventFeederTrue(feeder6, free, multiplier, score);
                }
                else{
                    eventFeederFalse(feedersshop, 400);
                }
            });
            JButton feeder7 = new JButton("<- Go Back");
            feeder7.setFont(getFont().deriveFont(Font.PLAIN, 20));
            feeder7.setFocusPainted(false);
            feeder7.setBorderPainted(false);
            feeder7.setBackground(new Color(0,0,0,0));
            feeder7.setForeground(Color.white);
            feeder7.setContentAreaFilled(false);
            feeder7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            feeder7.addActionListener(d -> {
                feedersshop.setVisible(false);
                shops.setVisible(true);
            });
            feedersshop.add(feeder7);
            feedersshop.setOpaque(false);
            shops.setVisible(false);
            ui.add(feedersshop, BorderLayout.CENTER);
        });
        JButton empty1 = new JButton(); empty1.setBackground(Color.black); empty1.setBorderPainted(false); empty1.setFocusPainted(false); empty1.setContentAreaFilled(false);
        JButton empty2 = new JButton(); empty2.setBackground(Color.black); empty2.setBorderPainted(false); empty2.setFocusPainted(false); empty2.setContentAreaFilled(false);
        JButton empty3 = new JButton(); empty3.setBackground(Color.black); empty3.setBorderPainted(false); empty3.setFocusPainted(false); empty3.setContentAreaFilled(false);
        shops.add(empty1); shops.add(multipliers); shops.add(empty2); shops.add(feeders); shops.add(empty3);
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
        clevel.setFont(getFont().deriveFont(Font.PLAIN, 20));
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
        JButton unlock = new JButton("Unlock next cat: "+getFMT().format(currentcat.getNextlevel()));
        unlock.setFont(getFont().deriveFont(Font.PLAIN, 20));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        unlock.setFocusPainted(false);
        unlock.setBorderPainted(false);
        unlock.setBackground(new Color(0,0,0,0));
        unlock.setForeground(Color.white);
        unlock.setContentAreaFilled(false);
        unlock.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        unlock.addActionListener(e ->{
            if(getKibbles().compareTo(currentcat.getNextlevel()) > 0){
                switch(currentlevel) {
                    case 1:
                        setCurrentlevel(2);
                        setCurrentcat(cat2);
                        ui.setTitle("TapTheCat | "+currentcat.getLevelname());
                        clevel.setText(currentcat.getLevelname());
                        unlock.setText("Unlock next cat: "+getFMT().format(currentcat.getNextlevel()));
                        kitten.setIcon(currentcat.getKitten());
                        break;
                    case 2:
                        setCurrentlevel(3);
                        setCurrentcat(cat3);
                        ui.setTitle("TapTheCat | "+currentcat.getLevelname());
                        clevel.setText(currentcat.getLevelname());
                        unlock.setText("Unlock next cat: "+getFMT().format(currentcat.getNextlevel()));
                        kitten.setIcon(currentcat.getKitten());
                        break;
                    case 3:
                        setCurrentlevel(4);
                        setCurrentcat(cat4);
                        ui.setTitle("TapTheCat | "+currentcat.getLevelname());
                        clevel.setText(currentcat.getLevelname());
                        unlock.setText("Unlock next cat: "+getFMT().format(currentcat.getNextlevel()));
                        kitten.setIcon(currentcat.getKitten());
                        break;
                    case 4:
                        setCurrentlevel(5);
                        setCurrentcat(cat5);
                        ui.setTitle("TapTheCat | "+currentcat.getLevelname());
                        clevel.setText(currentcat.getLevelname());
                        unlock.setText("Buy Freedom: "+getFMT().format(currentcat.getNextlevel()));
                        kitten.setIcon(currentcat.getKitten());
                        break;
                    case 5:
                        setCurrentlevel(6);
                        kittenL.setIcon(new ImageIcon(""));
                        clevel.setText("Congratulations! You won!");
                        unlock.setText("Click here to start a new game!");
                        ui.setTitle("TapTheCat | Freedom!");
                        kitten.setIcon(new ImageIcon("res/kittensfinal.png"));
                        bg.setIcon(new ImageIcon("res/backgroundempty.png"));
                        unlock.addActionListener(e7 -> {
                            new Game(1280,720);
                            ui.dispose();
                        });
                        break;
                }
            }
            else{
                JPopupMenu pop = new JPopupMenu();
                JLabel l = new JLabel("INSUFFICIENT KIBBLES");
                l.setFont(getFont().deriveFont(Font.PLAIN, 20));
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
            score.setText("Kibbles: " + getFMT().format(getKibbles()));
        });
        //

        //Feeder function
        int delay = 1000; //milliseconds
        ActionListener taskPerformer = evt -> {
            setKibbles(getKibbles().add(getFeeder()));
            score.setText("Kibbles: " + getFMT().format(getKibbles()));
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

    public JButton buttonZoomies(Zoomies zoom, JPanel zoomieshop){
        JButton z = new JButton(getFMT2().format(zoom.getPrice())+" - "+zoom.getName()+" (x"+getFMT2().format(zoom.getMultiplier())+") : " +getFMT2().format(zoom.getAmount()));
        z.setFont(getFont().deriveFont(Font.PLAIN, 15));
        z.setFocusPainted(false);
        z.setBorderPainted(false);
        z.setBackground(new Color(0,0,0,0));
        z.setForeground(Color.white);
        z.setContentAreaFilled(false);
        z.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        zoomieshop.add(z);
        return z;
    }

    public void eventZoomiesTrue(JButton z, Zoomies zoom, JLabel multiplier, JLabel score){
        setKibbles(getKibbles().subtract(zoom.getPrice()));
        zoom.setAmount(zoom.getAmount().add(BigDecimal.valueOf(1)));
        setZoomies(getZoomies().add(zoom.getMultiplier()));
        multiplier.setText("Zoomies: " + "x" + getFMT().format(getZoomies()) + "    " + "Feeder: " + getFMT().format(getFeeder()) + "/s");
        zoom.priceIncrease();
        score.setText("Kibbles: " + getFMT().format(getKibbles()));
        z.setText(getFMT2().format(zoom.getPrice())+" - "+zoom.getName()+" (x"+getFMT2().format(zoom.getMultiplier())+") : " +getFMT2().format(zoom.getAmount()));
    }

    public void eventZoomiesFalse(JPanel zoomiesshop, int y){
        JPopupMenu pop = new JPopupMenu();
        JLabel l = new JLabel("INSUFFICIENT KIBBLES");
        l.setFont(getFont().deriveFont(Font.PLAIN, 15));
        l.setForeground(Color.white);
        pop.setBorderPainted(false);
        pop.setBackground(new Color(0,0,0,0));
        pop.setOpaque(false);
        pop.add(l);
        pop.show(zoomiesshop, 70,y);
    }

    public JButton buttonFeeder(Feeder feed, JPanel feedershop){
        JButton f = new JButton(getFMT2().format(feed.getPrice())+" - "+feed.getName()+" ("+getFMT2().format(feed.getMultiplier())+"/s) : " +getFMT2().format(feed.getAmount()));
        f.setFont(getFont().deriveFont(Font.PLAIN, 15));
        f.setFocusPainted(false);
        f.setBorderPainted(false);
        f.setBackground(new Color(0,0,0,0));
        f.setForeground(Color.white);
        f.setContentAreaFilled(false);
        f.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        feedershop.add(f);
        return f;
    }

    public void eventFeederTrue(JButton f, Feeder feed, JLabel multiplier, JLabel score){
        setKibbles(getKibbles().subtract(feed.getPrice()));
        feed.setAmount(feed.getAmount().add(BigDecimal.valueOf(1)));
        setFeeder(getFeeder().add(feed.getMultiplier()));
        multiplier.setText("Zoomies: " + "x" + getFMT().format(getZoomies()) + "    " + "Feeder: " + getFMT().format(getFeeder()) + "/s");
        feed.priceIncrease();
        score.setText("Kibbles: " + getFMT().format(getKibbles()));
        f.setText(getFMT2().format(feed.getPrice())+" - "+feed.getName()+" ("+getFMT2().format(feed.getMultiplier())+"/s) : " +getFMT2().format(feed.getAmount()));
    }

    public void eventFeederFalse(JPanel feedershop, int y){
        JPopupMenu pop = new JPopupMenu();
        JLabel l = new JLabel("INSUFFICIENT KIBBLES");
        l.setFont(getFont().deriveFont(Font.PLAIN, 15));
        l.setForeground(Color.white);
        pop.setBorderPainted(false);
        pop.setBackground(new Color(0,0,0,0));
        pop.setOpaque(false);
        pop.add(l);
        pop.show(feedershop, 70,y);
    }

    public CompactNumberFormat getFMT(){
        String[] compactPatterns = {"", "", "", "0K", "00K", "000K", "0M", "00M", "000M", "0B", "00B", "000B", "0T", "00T", "000T", "0Q", "00Q", "000Q","0X",};
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance();
        CompactNumberFormat fmt = new CompactNumberFormat(decimalFormat.toPattern(), decimalFormat.getDecimalFormatSymbols(), compactPatterns);
        fmt.setMinimumFractionDigits(1);
        return fmt;
    }

    public CompactNumberFormat getFMT2(){
        String[] compactPatterns = {"", "", "", "0K", "00K", "000K", "0M", "00M", "000M", "0B", "00B", "000B", "0T", "00T", "000T", "0Q", "00Q", "000Q","0X",};
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance();
        CompactNumberFormat fmt2 = new CompactNumberFormat(decimalFormat.toPattern(), decimalFormat.getDecimalFormatSymbols(), compactPatterns);
        fmt2.setMinimumFractionDigits(0);
        return fmt2;
    }

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

    public BigDecimal getKibbles() {
        return kibbles;
    }

    public void setKibbles(BigDecimal kibbles) {
        this.kibbles = kibbles;
    }

    public BigDecimal getZoomies() {
        return zoomies;
    }

    public void setZoomies(BigDecimal zoomies) {
        this.zoomies = zoomies;
    }

    public BigDecimal getFeeder() {
        return feeder;
    }

    public void setFeeder(BigDecimal feeder) {
        this.feeder = feeder;
    }

    public void setCurrentlevel(int currentlevel) {
        this.currentlevel = currentlevel;
    }

    public void setCurrentcat(Cat currentcat) {
        this.currentcat = currentcat;
    }
}
