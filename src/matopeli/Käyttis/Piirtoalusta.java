/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matopeli.Käyttis;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.PaintContext;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import matopeli.Logiikker.Keskus;
import matopeli.Logiikker.Pala;
import matopeli.Logiikker.Ruoka;

/**
 *
 * @author Konsta
 */
public class Piirtoalusta extends JPanel {

    private ImageIcon pelaa;
    private ImageIcon vaakakuva;
    private ImageIcon poikkikuva;
    private ImageIcon vasenylos;
    private ImageIcon vasenalas;
    private ImageIcon oikeaylos;
    private ImageIcon oikeaalas;
    private ImageIcon image7;
    private ImageIcon image8;
    private ImageIcon image9;
    private ImageIcon tausta;
    private ImageIcon otsikko;
    private ImageIcon vaikeus;
    private ImageIcon paakuva;
    private ImageIcon viiva;
    private ImageIcon alue;
    private ImageIcon osoitin;
    private ImageIcon seinaON;
    private ImageIcon seinaEI;
    private ImageIcon quit;

    public void setKeskus(Keskus keskus) {
        this.keskus = keskus;
    }

    private Keskus keskus;
    private int skaala;

    public Piirtoalusta(Keskus keskus) throws IOException {
        this.keskus = keskus;
        this.skaala = 20;
        try {

            alue = teePiirros("/Resources/alue.png");
            viiva = teePiirros("/Resources/viiva.png");
            vaikeus = teePiirros("/Resources/vaikeus.png");
            pelaa = teePiirros("/Resources/pelaa.png");
            tausta = teePiirros("/Resources/tausta.png");
            otsikko = teePiirros("/Resources/otsikko.png");
            paakuva = teePiirros("/Resources/paa.png");
            image9 = teePiirros("/Resources/Reuna.png");
            image8 = teePiirros("/Resources/ruoka.png");
            image7 = teePiirros("/Resources/tausta.png");
            vaakakuva = teePiirros("/Resources/vaakaMatoo.png");
            poikkikuva = teePiirros("/Resources/poikkiMato.png");
            vasenylos = teePiirros("/Resources/vasenYlos.png");
            vasenalas = teePiirros("/Resources/vasenAlas.png");
            oikeaylos = teePiirros("/Resources/oikeaYlos.png");
            oikeaalas = teePiirros("/Resources/oikeaAlas.png");    
            seinaON = teePiirros("/Resources/seinaON.png");
            seinaEI = teePiirros("/Resources/seinaEI.png");
            quit = teePiirros("/Resources/quit.png");
            osoitin = teePiirros("/Resources/osoitin.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.setBackground(Color.black);
        graphics.drawString(keskus.getPaivitysvali() + " ms interval", (int) (super.getWidth() * 0.75), (int) (super.getHeight() * 0.45));
        graphics.drawString("Leveys: " + keskus.getLeveys() + " Korkeus: " + keskus.getKorkeus(), (int) (super.getWidth() * 0.1), (int) (super.getHeight() * 0.45));
        graphics.drawImage(osoitin.getImage(), (int) (((((((double) keskus.getPaivitysvali() - 30) / 170) * super.getWidth()) * 0.3) + super.getWidth() * 0.05)), (int) (super.getHeight() * 0.4), 10, 15, this);
        graphics.drawImage(osoitin.getImage(), (int) (((double) keskus.getLeveys() / 60) * super.getWidth() * 0.3 + super.getWidth() * 0.65), (int) (super.getHeight() * 0.4), 10, 15, this);
        if (keskus.getState() == 1) {
            piirraPeli(graphics);
        } else {
            piirraValikko(graphics);
        }
    }

    private ImageIcon teePiirros(String kuvanSijainti) throws IOException {
        ImageIcon ii = null;
        try {
            ii = new ImageIcon(ImageIO.read(getClass().getResourceAsStream(kuvanSijainti)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return ii;
    }

    private void piirraPeli(Graphics graphics) {
        int xt = 1;
        int yt = 1;
        int leveys = super.getWidth();
        int korkeus = super.getHeight();
        if (leveys / keskus.getLeveys() < korkeus / keskus.getKorkeus()) {
            skaala = leveys / (keskus.getLeveys() + 3);
            xt = 1;
            yt = 1;
        } else {
            skaala = korkeus / (keskus.getKorkeus() + 4);
            xt = 1;
            yt = 1;
        }
        super.paintComponent(graphics);
        ArrayList<Pala> palat = keskus.getPalat();
        int x = 0;
        int y = 0;
        int mones = 0;
        Ruoka ruoka = keskus.getRuoka();
        x = ruoka.getX() + xt;
        y = ruoka.getY() + yt;
        graphics.drawImage(image8.getImage(), x * skaala, y * skaala, skaala, skaala, this);
        for (Pala p : palat) {
            x = p.getX() + xt;
            y = p.getY() + yt;
            graphics.drawImage(valitsekuva(p, mones).getImage(), x * skaala, y * skaala, skaala, skaala, this);
            mones++;
        }
        for (int i = 0; i <= keskus.getLeveys(); i++) {
            graphics.drawImage(image9.getImage(), (xt + i) * skaala, yt * skaala, skaala, skaala, this);

            graphics.drawImage(image9.getImage(), (xt + i) * skaala, (keskus.getKorkeus() + yt) * skaala, skaala, skaala, this);
        }
        for (int i = 0; i <= keskus.getKorkeus(); i++) {
            graphics.drawImage(image9.getImage(), xt * skaala, (i + yt) * skaala, skaala, skaala, this);

            graphics.drawImage(image9.getImage(), skaala * (keskus.getLeveys() + xt), (i + yt) * skaala, skaala, skaala, this);
        }
        Font font = new Font("Segoe UI Light", Font.PLAIN, 40);
        graphics.setColor(Color.white);
        graphics.drawString("Score: " + keskus.getPisteet(), 20, 20);
    }

    private void piirraValikko(Graphics g) {
        Font font = new Font("Segoe UI Light", Font.BOLD, 20);
        if (super.getWidth() > super.getHeight()) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setFont(font);
            g2.setColor(Color.white);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.drawImage(tausta.getImage(), 0, 0, super.getWidth(), super.getHeight(), this);
            g2.drawImage(otsikko.getImage(), (int) ((super.getWidth() / 2) - (super.getWidth() * (0.33))), (int) (super.getHeight() * (0.05)), (int) ((super.getWidth()) * (0.33 * 2)), (int) (super.getHeight() * (0.2)), this); //pelaa
            g2.drawImage(pelaa.getImage(), (int) ((super.getWidth() / 2) - (super.getWidth() * 0.1)), (int) ((super.getHeight() * 0.3)), (int) (super.getWidth() * 0.2), (int) (super.getHeight() * 0.12), this);//alue
            g2.drawImage(alue.getImage(), (int) (super.getWidth() / 4 - super.getWidth() * 0.15), (int) (super.getHeight() * 0.3), (int) (super.getWidth() * 0.2), (int) (super.getHeight() * 0.06), this);//vaikeus
            g2.drawImage(vaikeus.getImage(), (int) (super.getWidth() * (0.75) - super.getWidth() * 0.05), (int) (super.getHeight() * 0.3), (int) (super.getWidth() * 0.2), (int) (super.getHeight() * 0.06), this);
            g2.drawImage(viiva.getImage(), (int) (super.getWidth() * 0.05), (int) (super.getHeight() * 0.4), (int) (super.getWidth() * 0.3), (int) (super.getHeight() * 0.015), this);
            g2.drawImage(viiva.getImage(), (int) (super.getWidth() * 0.95 - (super.getWidth() * 0.3)), (int) (super.getHeight() * 0.4), (int) (super.getWidth() * 0.3), (int) (super.getHeight() * 0.015), this);
            g2.drawImage(osoitin.getImage(), (int) (((((((double) keskus.getPaivitysvali() - 30) / 170) * super.getWidth()) * 0.3) + super.getWidth() * 0.65)), (int) (super.getHeight() * 0.4), 10, 15, this);
            g2.drawImage(osoitin.getImage(), (int) (((double) keskus.getLeveys() / 60) * super.getWidth() * 0.3 + super.getWidth() * 0.05), (int) (super.getHeight() * 0.4), 10, 15, this);
            g2.drawString(keskus.getPaivitysvali() + " ms interval", (int) (super.getWidth() * 0.75), (int) (super.getHeight() * 0.45));
            g2.drawString("Width: " + keskus.getLeveys() + " Height: " + keskus.getKorkeus(), (int) (super.getWidth() * 0.15), (int) (super.getHeight() * 0.45));
            if (keskus.isKakkonen()) {
                g2.drawImage(seinaON.getImage(), (int) (super.getWidth() / 4 - super.getWidth() * 0.15), (int) (super.getHeight() * 0.6), (int) (super.getWidth() * 0.2), (int) (super.getHeight() * 0.06), this);
            } else {
                g2.drawImage(seinaEI.getImage(), (int) (super.getWidth() / 4 - super.getWidth() * 0.15), (int) (super.getHeight() * 0.6), (int) (super.getWidth() * 0.2), (int) (super.getHeight() * 0.06), this);
            }
            g2.drawImage(quit.getImage(), (int) (super.getWidth() * (0.75) - super.getWidth() * 0.05), (int) (super.getHeight() * 0.6), (int) (super.getWidth() * 0.2), (int) (super.getHeight() * 0.06), this);
        }
//        g.setColor(Color.black);
//        g.fillRect(0, 0, 3243, 3000);
//        g.setFont(font);
//        g.setColor(Color.green);
//        g.drawRect(super.getWidth() / 2 - 200, 100, 400, 125);
//        g.drawString("SNAKE YKSI", super.getWidth() / 2 - 175, 175);
//        g.drawRect(super.getWidth() / 2 - 200, 370, 400, 125);
//        g.drawString("SNAKE KAKSI", super.getWidth() / 2 - 200, 450);
    }

    private ImageIcon valitsekuva(Pala p, int mones) {
        ArrayList<Pala> palat = keskus.getPalat();
        if ((p.getX() == palat.get(0).getX() && p.getY() == palat.get(0).getY())) {
            return paakuva;
        } else if (mones == palat.size() - 1) {
            return paakuva;
        } else if (palat.get(mones + 1).getY() - p.getY() > 1) {
            if (palat.get(mones - 1).getX() > p.getX()) {
                return oikeaylos;
            } else if (palat.get(mones - 1).getX() < p.getX()) {
                return vasenylos;
            } else {
                return poikkikuva;
            }

        } else if (palat.get(mones + 1).getY() - p.getY() < -1) {
            if (palat.get(mones - 1).getX() > p.getX()) {
                return oikeaalas;
            } else if (palat.get(mones - 1).getX() < p.getX()) {
                return vasenalas;
            } else {
                return poikkikuva;
            }
        } else if (palat.get(mones + 1).getX() - p.getX() > 1) {
            if (palat.get(mones - 1).getY() > p.getY()) {
                return vasenalas;
            } else if (palat.get(mones - 1).getY() < p.getY()) {
                return vasenylos;
            } else {
                return vaakakuva;
            }
        } else if (palat.get(mones + 1).getX() - p.getX() < -1) {
            if (palat.get(mones - 1).getY() > p.getY()) {
                return oikeaalas;
            } else if (palat.get(mones - 1).getY() < p.getY()) {
                return oikeaylos;
            } else {
                return vaakakuva;
            }
        } else if ((palat.get(mones - 1).getX() < p.getX() && palat.get(mones + 1).getX() > p.getX()) || (palat.get(mones - 1).getX() > p.getX() && palat.get(mones + 1).getX() < p.getX())) {
            return vaakakuva;
        } else if ((palat.get(mones - 1).getY() < p.getY() && palat.get(mones + 1).getY() > p.getY()) || (palat.get(mones - 1).getY() > p.getY() && palat.get(mones + 1).getY() < p.getY())) {
            return poikkikuva;
        } else if (palat.get(mones - 1).getY() > p.getY()) {
            if (palat.get(mones + 1).getX() > p.getX()) {
                return oikeaalas;
            } else {
                return vasenalas;
            }
        } else if (palat.get(mones - 1).getY() < p.getY()) {
            if (palat.get(mones + 1).getX() > p.getX()) {
                return oikeaylos;
            } else {
                return vasenylos;
            }
        } else if (palat.get(mones + 1).getY() > p.getY()) {
            if (palat.get(mones - 1).getX() > p.getX()) {
                return oikeaalas;
            } else {
                return vasenalas;
            }
        } else if (palat.get(mones + 1).getY() < p.getY()) {
            if (palat.get(mones - 1).getX() > p.getX()) {
                return oikeaylos;
            } else {
                return vasenylos;
            }
        } else {
            return paakuva;
        }
    }

}
