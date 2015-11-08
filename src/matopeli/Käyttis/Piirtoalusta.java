/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matopeli.Käyttis;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.PaintContext;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import matopeli.Logiikker.Keskus;
import matopeli.Logiikker.Pala;
import matopeli.Logiikker.Ruoka;

/**
 *
 * @author Konsta
 */
public class Piirtoalusta extends JPanel {

    public void setKeskus(Keskus keskus) {
        this.keskus = keskus;
    }

    private Keskus keskus;
    private BufferedImage kuva1;

    public Piirtoalusta(Keskus keskus) throws IOException {
        this.keskus = keskus;
        super.setBackground(Color.red);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.setBackground(Color.black);
        if (keskus.getState() == 1) {
            piirraPeli(graphics);
        } else {
            piirraValikko(graphics);
        }
    }

    private void piirraPeli(Graphics graphics) {
        super.paintComponent(graphics);
        ArrayList<Pala> palat = keskus.getPalat();
        int x = 0;
        int y = 0;
        for (Pala p : palat) {
            x = p.getX();
            y = p.getY();
            graphics.setColor(Color.red);
            graphics.fillRect(x * 20, y * 20, 20, 20);

        }
        graphics.setColor(Color.pink);
        graphics.fillRect(palat.get(0).getX() * 20, palat.get(0).getY() * 20, 20, 20);
        Ruoka ruoka = keskus.getRuoka();
        x = ruoka.getX();
        y = ruoka.getY();
        graphics.setColor(Color.white);
        graphics.fillRect(x * 20, y * 20, 20, 20);
        graphics.setColor(Color.yellow);
        for (int i = 0; i <= keskus.getLeveys(); i++) {
            graphics.fillRect(i * 20, 0, 20, 20);
            graphics.fillRect(i * 20, keskus.getKorkeus() * 20, 20, 20);
        }
        for (int i = 0; i <= keskus.getKorkeus(); i++) {
            graphics.fillRect(0, i * 20, 20, 20);
            graphics.fillRect(20 * (keskus.getLeveys()), i * 20, 20, 20);
        }
    }

    private void piirraValikko(Graphics g) {
        Font font = new Font("arial", Font.PLAIN, 60);
        super.setBackground(Color.black);
        g.setColor(Color.black);
        g.fillRect(0, 0, 3243, 3000);
        g.setFont(font);
        g.setColor(Color.green);
        g.drawRect(super.getWidth() / 2 - 200, 100, 400, 125);
        g.drawString("SNAKE YKSI", super.getWidth() / 2 - 175, 175);
        g.drawRect(super.getWidth() / 2 - 200, 370, 400, 125);
        g.drawString("SNAKE KAKSI", super.getWidth() / 2 - 200, 450);
    }
}
