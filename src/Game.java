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
        this.kibbles = new BigDecimal("10000000000000");
        this.zoomies = BigDecimal.valueOf(1);
        this.feeder = BigDecimal.valueOf(0);
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
        String[] compactPatterns = {"", "", "", "0K", "00K", "000K", "0M", "00M", "000M", "0B", "00B", "000B", "0T", "00T", "000T", "0Q", "00Q", "000Q","0X",};
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance();
        CompactNumberFormat fmt = new CompactNumberFormat(decimalFormat.toPattern(), decimalFormat.getDecimalFormatSymbols(), compactPatterns);
        fmt.setMinimumFractionDigits(1);
        CompactNumberFormat fmt2 = new CompactNumberFormat(decimalFormat.toPattern(), decimalFormat.getDecimalFormatSymbols(), compactPatterns);
        fmt2.setMinimumFractionDigits(0);
        //

        //Frame initialization
        JFrame ui = new JFrame();
        ui.setTitle("TapTheCat | "+currentcat.getLevelname());
        ImageIcon background = new ImageIcon("res/background.png");
        ui.setContentPane(new JLabel(background));
        ui.setLayout(new BorderLayout());
        ui.setSize(w, h);
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
        Cat cat2 = new L2_White();
        Cat cat3 = new L3_Tortoiseshell();
        Cat cat4 = new L4_Orange();
        Cat cat5 = new L5_Siamese();
        //

        //Initiate the zoomies
        Zoomies laser = new Zoomies(BigDecimal.valueOf(100),"Pointing Laser", BigDecimal.valueOf(5), BigDecimal.valueOf(0));
        Zoomies scratcher = new Zoomies(BigDecimal.valueOf(10000),"Scratcher", BigDecimal.valueOf(500), BigDecimal.valueOf(0));
        Zoomies fishing = new Zoomies(BigDecimal.valueOf(1000000),"Fishing Pole", BigDecimal.valueOf(50000), BigDecimal.valueOf(0));
        Zoomies crinkle = new Zoomies(BigDecimal.valueOf(100000000),"Crinkle Ball", BigDecimal.valueOf(5000000), BigDecimal.valueOf(0));
        Zoomies mouse = new Zoomies(new BigDecimal("10000000000"),"Stuffed Mouse", new BigDecimal("500000000"), BigDecimal.valueOf(0));
        Zoomies catnip = new Zoomies(new BigDecimal("1000000000000"),"Cat Nip", new BigDecimal("100000000000"), BigDecimal.valueOf(0));
        //

        //Initiate the feeders
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

        //Shop buttons (oh god)
        JPanel shops = new JPanel();
        shops.setLayout(new GridLayout(5,1, 0, 0));
        shops.setPreferredSize(new Dimension(450,300));
        shops.setOpaque(false);
        JButton multipliers = new JButton("Buy Zoomies");
        multipliers.setFont(font.deriveFont(Font.PLAIN, 40));
        multipliers.setFocusPainted(false);
        multipliers.setBorderPainted(false);
        multipliers.setBackground(new Color(0,0,0,0));
        multipliers.setForeground(Color.white);
        multipliers.setContentAreaFilled(false);
        multipliers.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Font finalFont1 = font;
        Font finalFont2 = font;
        multipliers.addActionListener(e -> {
            JPanel zoomiesshop = new JPanel();
            zoomiesshop.setLayout(new GridLayout(0,1,0,0));
            JButton zoomies1 = new JButton(fmt2.format(laser.getPrice())+" - "+laser.getName()+" (x"+fmt2.format(laser.getMultiplier())+") : " +fmt2.format(laser.getAmount()));
            zoomies1.setFont(finalFont1.deriveFont(Font.PLAIN, 15));
            zoomies1.setFocusPainted(false);
            zoomies1.setBorderPainted(false);
            zoomies1.setBackground(new Color(0,0,0,0));
            zoomies1.setForeground(Color.white);
            zoomies1.setContentAreaFilled(false);
            zoomies1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            zoomies1.addActionListener(e1 -> {
                if(getKibbles().compareTo(laser.getPrice()) >= 0){
                    setKibbles(getKibbles().subtract(laser.getPrice()));
                    laser.setAmount(laser.getAmount().add(BigDecimal.valueOf(1)));
                    setZoomies(getZoomies().add(laser.getMultiplier()));
                    multiplier.setText("Zoomies: " + "x" + fmt.format(getZoomies()) + "    " + "Feeder: " + fmt.format(getFeeder()) + "/s");
                    laser.priceIncrease();
                    score.setText("Kibbles: " + fmt.format(getKibbles()));
                    zoomies1.setText(fmt2.format(laser.getPrice())+" - "+laser.getName()+" (x"+fmt2.format(laser.getMultiplier())+") : " +fmt2.format(laser.getAmount()));
                }
                else{
                    JPopupMenu pop = new JPopupMenu();
                    JLabel l = new JLabel("INSUFFICIENT KIBBLES");
                    l.setFont(finalFont2.deriveFont(Font.PLAIN, 15));
                    l.setForeground(Color.white);
                    pop.setBorderPainted(false);
                    pop.setBackground(new Color(0,0,0,0));
                    pop.setOpaque(false);
                    pop.add(l);
                    pop.show(zoomiesshop, 70,50);
                }
            });
            JButton zoomies2 = new JButton(fmt2.format(scratcher.getPrice())+" - "+scratcher.getName()+" (x"+fmt2.format(scratcher.getMultiplier())+") : " +fmt2.format(scratcher.getAmount()));
            zoomies2.setFont(finalFont1.deriveFont(Font.PLAIN, 15));
            zoomies2.setFocusPainted(false);
            zoomies2.setBorderPainted(false);
            zoomies2.setBackground(new Color(0,0,0,0));
            zoomies2.setForeground(Color.white);
            zoomies2.setContentAreaFilled(false);
            zoomies2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            zoomies2.addActionListener(e2 -> {
                if(getKibbles().compareTo(scratcher.getPrice()) >= 0){
                    setKibbles(getKibbles().subtract(scratcher.getPrice()));
                    scratcher.setAmount(scratcher.getAmount().add(BigDecimal.valueOf(1)));
                    setZoomies(getZoomies().add(scratcher.getMultiplier()));
                    multiplier.setText("Zoomies: " + "x" + fmt.format(getZoomies()) + "    " + "Feeder: " + fmt.format(getFeeder()) + "/s");
                    scratcher.priceIncrease();
                    score.setText("Kibbles: " + fmt.format(getKibbles()));
                    zoomies2.setText(fmt2.format(scratcher.getPrice())+" - "+scratcher.getName()+" (x"+fmt2.format(scratcher.getMultiplier())+") : " +fmt2.format(scratcher.getAmount()));
                }
                else{
                    JPopupMenu pop = new JPopupMenu();
                    JLabel l = new JLabel("INSUFFICIENT KIBBLES");
                    l.setFont(finalFont2.deriveFont(Font.PLAIN, 15));
                    l.setForeground(Color.white);
                    pop.setBorderPainted(false);
                    pop.setBackground(new Color(0,0,0,0));
                    pop.setOpaque(false);
                    pop.add(l);
                    pop.show(zoomiesshop, 70,120);
                }
            });
            JButton zoomies3 = new JButton(fmt2.format(fishing.getPrice())+" - "+fishing.getName()+" (x"+fmt2.format(fishing.getMultiplier())+") : " +fmt2.format(fishing.getAmount()));
            zoomies3.setFont(finalFont1.deriveFont(Font.PLAIN, 15));
            zoomies3.setFocusPainted(false);
            zoomies3.setBorderPainted(false);
            zoomies3.setBackground(new Color(0,0,0,0));
            zoomies3.setForeground(Color.white);
            zoomies3.setContentAreaFilled(false);
            zoomies3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            zoomies3.addActionListener(e3 -> {
                if(getKibbles().compareTo(fishing.getPrice()) >= 0){
                    setKibbles(getKibbles().subtract(fishing.getPrice()));
                    fishing.setAmount(fishing.getAmount().add(BigDecimal.valueOf(1)));
                    setZoomies(getZoomies().add(fishing.getMultiplier()));
                    multiplier.setText("Zoomies: " + "x" + fmt.format(getZoomies()) + "    " + "Feeder: " + fmt.format(getFeeder()) + "/s");
                    fishing.priceIncrease();
                    score.setText("Kibbles: " + fmt.format(getKibbles()));
                    zoomies3.setText(fmt2.format(fishing.getPrice())+" - "+fishing.getName()+" (x"+fmt2.format(fishing.getMultiplier())+") : " +fmt2.format(fishing.getAmount()));
                }
                else{
                    JPopupMenu pop = new JPopupMenu();
                    JLabel l = new JLabel("INSUFFICIENT KIBBLES");
                    l.setFont(finalFont2.deriveFont(Font.PLAIN, 15));
                    l.setForeground(Color.white);
                    pop.setBorderPainted(false);
                    pop.setBackground(new Color(0,0,0,0));
                    pop.setOpaque(false);
                    pop.add(l);
                    pop.show(zoomiesshop, 70,190);
                }
            });
            JButton zoomies4 = new JButton(fmt2.format(crinkle.getPrice())+" - "+crinkle.getName()+" (x"+fmt2.format(crinkle.getMultiplier())+") : " +fmt2.format(crinkle.getAmount()));
            zoomies4.setFont(finalFont1.deriveFont(Font.PLAIN, 15));
            zoomies4.setFocusPainted(false);
            zoomies4.setBorderPainted(false);
            zoomies4.setBackground(new Color(0,0,0,0));
            zoomies4.setForeground(Color.white);
            zoomies4.setContentAreaFilled(false);
            zoomies4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            zoomies4.addActionListener(e4 -> {
                if(getKibbles().compareTo(crinkle.getPrice()) >= 0){
                    setKibbles(getKibbles().subtract(crinkle.getPrice()));
                    crinkle.setAmount(crinkle.getAmount().add(BigDecimal.valueOf(1)));
                    setZoomies(getZoomies().add(crinkle.getMultiplier()));
                    multiplier.setText("Zoomies: " + "x" + fmt.format(getZoomies()) + "    " + "Feeder: " + fmt.format(getFeeder()) + "/s");
                    crinkle.priceIncrease();
                    score.setText("Kibbles: " + fmt.format(getKibbles()));
                    zoomies4.setText(fmt2.format(crinkle.getPrice())+" - "+crinkle.getName()+" (x"+fmt2.format(crinkle.getMultiplier())+") : " +fmt2.format(crinkle.getAmount()));
                }
                else{
                    JPopupMenu pop = new JPopupMenu();
                    JLabel l = new JLabel("INSUFFICIENT KIBBLES");
                    l.setFont(finalFont2.deriveFont(Font.PLAIN, 15));
                    l.setForeground(Color.white);
                    pop.setBorderPainted(false);
                    pop.setBackground(new Color(0,0,0,0));
                    pop.setOpaque(false);
                    pop.add(l);
                    pop.show(zoomiesshop, 70,260);
                }
            });
            JButton zoomies5 = new JButton(fmt2.format(mouse.getPrice())+" - "+mouse.getName()+" (x"+fmt2.format(mouse.getMultiplier())+") : " +fmt2.format(mouse.getAmount()));
            zoomies5.setFont(finalFont1.deriveFont(Font.PLAIN, 14));
            zoomies5.setFocusPainted(false);
            zoomies5.setBorderPainted(false);
            zoomies5.setBackground(new Color(0,0,0,0));
            zoomies5.setForeground(Color.white);
            zoomies5.setContentAreaFilled(false);
            zoomies5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            zoomies5.addActionListener(e5 -> {
                if(getKibbles().compareTo(mouse.getPrice()) >= 0){
                    setKibbles(getKibbles().subtract(mouse.getPrice()));
                    mouse.setAmount(mouse.getAmount().add(BigDecimal.valueOf(1)));
                    setZoomies(getZoomies().add(mouse.getMultiplier()));
                    multiplier.setText("Zoomies: " + "x" + fmt.format(getZoomies()) + "    " + "Feeder: " + fmt.format(getFeeder()) + "/s");
                    mouse.priceIncrease();
                    score.setText("Kibbles: " + fmt.format(getKibbles()));
                    zoomies5.setText(fmt2.format(mouse.getPrice())+" - "+mouse.getName()+" (x"+fmt2.format(mouse.getMultiplier())+") : " +fmt2.format(mouse.getAmount()));
                }
                else{
                    JPopupMenu pop = new JPopupMenu();
                    JLabel l = new JLabel("INSUFFICIENT KIBBLES");
                    l.setFont(finalFont2.deriveFont(Font.PLAIN, 15));
                    l.setForeground(Color.white);
                    pop.setBorderPainted(false);
                    pop.setBackground(new Color(0,0,0,0));
                    pop.setOpaque(false);
                    pop.add(l);
                    pop.show(zoomiesshop, 70,330);
                }
            });
            JButton zoomies6 = new JButton(fmt2.format(catnip.getPrice())+" - "+catnip.getName()+" (x"+fmt2.format(catnip.getMultiplier())+") : " +fmt2.format(catnip.getAmount()));
            zoomies6.setFont(finalFont1.deriveFont(Font.PLAIN, 15));
            zoomies6.setFocusPainted(false);
            zoomies6.setBorderPainted(false);
            zoomies6.setBackground(new Color(0,0,0,0));
            zoomies6.setForeground(Color.white);
            zoomies6.setContentAreaFilled(false);
            zoomies6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            zoomies6.addActionListener(e6 -> {
                if(getKibbles().compareTo(catnip.getPrice()) >= 0){
                    setKibbles(getKibbles().subtract(catnip.getPrice()));
                    catnip.setAmount(catnip.getAmount().add(BigDecimal.valueOf(1)));
                    setZoomies(getZoomies().add(catnip.getMultiplier()));
                    multiplier.setText("Zoomies: " + "x" + fmt.format(getZoomies()) + "    " + "Feeder: " + fmt.format(getFeeder()) + "/s");
                    catnip.priceIncrease();
                    score.setText("Kibbles: " + fmt.format(getKibbles()));
                    zoomies6.setText(fmt2.format(catnip.getPrice())+" - "+catnip.getName()+" (x"+fmt2.format(catnip.getMultiplier())+") : " +fmt2.format(catnip.getAmount()));
                }
                else{
                    JPopupMenu pop = new JPopupMenu();
                    JLabel l = new JLabel("INSUFFICIENT KIBBLES");
                    l.setFont(finalFont2.deriveFont(Font.PLAIN, 15));
                    l.setForeground(Color.white);
                    pop.setBorderPainted(false);
                    pop.setBackground(new Color(0,0,0,0));
                    pop.setOpaque(false);
                    pop.add(l);
                    pop.show(zoomiesshop, 70,400);
                }
            });
            JButton zoomies7 = new JButton("<- Go Back");
            zoomies7.setFont(finalFont1.deriveFont(Font.PLAIN, 20));
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
            zoomiesshop.add(zoomies1);
            zoomiesshop.add(zoomies2);
            zoomiesshop.add(zoomies3);
            zoomiesshop.add(zoomies4);
            zoomiesshop.add(zoomies5);
            zoomiesshop.add(zoomies6);
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
        feeders.setFont(font.deriveFont(Font.PLAIN, 40));
        feeders.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        feeders.addActionListener(e -> {
            JPanel feedersshop = new JPanel();
            feedersshop.setLayout(new GridLayout(0,1,0,0));
            JButton feeder1 = new JButton("zoomies");
            feeder1.setFont(finalFont1.deriveFont(Font.PLAIN, 20));
            feeder1.setFocusPainted(false);
            feeder1.setBorderPainted(false);
            feeder1.setBackground(new Color(0,0,0,0));
            feeder1.setForeground(Color.white);
            feeder1.setContentAreaFilled(false);
            feeder1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            JButton feeder2 = new JButton("zoomies");
            feeder2.setFont(finalFont1.deriveFont(Font.PLAIN, 20));
            feeder2.setFocusPainted(false);
            feeder2.setBorderPainted(false);
            feeder2.setBackground(new Color(0,0,0,0));
            feeder2.setForeground(Color.white);
            feeder2.setContentAreaFilled(false);
            feeder2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            JButton feeder3 = new JButton("zoomies");
            feeder3.setFont(finalFont1.deriveFont(Font.PLAIN, 20));
            feeder3.setFocusPainted(false);
            feeder3.setBorderPainted(false);
            feeder3.setBackground(new Color(0,0,0,0));
            feeder3.setForeground(Color.white);
            feeder3.setContentAreaFilled(false);
            feeder3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            JButton feeder4 = new JButton("zoomies");
            feeder4.setFont(finalFont1.deriveFont(Font.PLAIN, 20));
            feeder4.setFocusPainted(false);
            feeder4.setBorderPainted(false);
            feeder4.setBackground(new Color(0,0,0,0));
            feeder4.setForeground(Color.white);
            feeder4.setContentAreaFilled(false);
            feeder4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            JButton feeder5 = new JButton("zoomies");
            feeder5.setFont(finalFont1.deriveFont(Font.PLAIN, 20));
            feeder5.setFocusPainted(false);
            feeder5.setBorderPainted(false);
            feeder5.setBackground(new Color(0,0,0,0));
            feeder5.setForeground(Color.white);
            feeder5.setContentAreaFilled(false);
            feeder5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            JButton feeder6 = new JButton("zoomies");
            feeder6.setFont(finalFont1.deriveFont(Font.PLAIN, 20));
            feeder6.setFocusPainted(false);
            feeder6.setBorderPainted(false);
            feeder6.setBackground(new Color(0,0,0,0));
            feeder6.setForeground(Color.white);
            feeder6.setContentAreaFilled(false);
            feeder6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            JButton feeder7 = new JButton("<- Go Back");
            feeder7.setFont(finalFont1.deriveFont(Font.PLAIN, 20));
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
            feedersshop.add(feeder1);
            feedersshop.add(feeder2);
            feedersshop.add(feeder3);
            feedersshop.add(feeder4);
            feedersshop.add(feeder5);
            feedersshop.add(feeder6);
            feedersshop.add(feeder7);
            feedersshop.setOpaque(false);
            shops.setVisible(false);
            ui.add(feedersshop, BorderLayout.CENTER);
        });
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
        unlock.setBackground(new Color(0,0,0,0));
        unlock.setForeground(Color.white);
        unlock.setContentAreaFilled(false);
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
            score.setText("Kibbles: " + fmt.format(getKibbles()));
        });
        //

        //Feeder function
        int delay = 1000; //milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                setKibbles(getKibbles().add(getFeeder()));
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
