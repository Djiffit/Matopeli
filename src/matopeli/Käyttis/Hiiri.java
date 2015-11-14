/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matopeli.Käyttis;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import matopeli.Logiikker.Keskus;

/**
 *
 * @author Konsta
 */
public class Hiiri implements MouseListener {

    private Keskus keskus;

    Hiiri(Keskus keskus) {
        this.keskus = keskus;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        int x = keskus.getResoX();
        int y = keskus.getResoY();
        System.out.println(mx + " " + my+ " " + x + " " + y);
        if (keskus.getState() == 0) {
            if ((mx > ((int) (x * 0.5 - x * 0.1)) && (mx < (x * 0.5 + x * 0.1)))) {
                if ((my > (y * 0.3) && my < (y * 0.3 + y*0.2))) {
                    keskus.setState(1);
                    System.out.println("GOOOOO");
                }
            }
            if (mx > x * 0.05 && mx < x * 0.05 + x * 0.3 && (my > y * 0.35 && my < y * 0.4 + y * 0.1)) {
                keskus.setLeveys((int) (((mx - (x * 0.05)) / (x * 0.3)) * 60));
                keskus.setKorkeus((int) ((0.52 * ((mx - x * 0.05) / (x * 0.3)) * 60)));
                System.out.println(keskus.getLeveys() + " " + keskus.getKorkeus());
            }
            if (mx > x * 0.65 && mx < x * 0.95 && (my > y * 0.35 && my < y * 0.4 + y * 0.1)) {
                keskus.setPaivitysvali((int) (((mx - x*0.65)/(x*0.3))*170 + 30));
                System.out.println(keskus.getPaivitysvali());
            }
            if (mx > (x/4 - x*0.15) && mx < (x/4 + x*0.15)) {
                if (my > 0.55*y && my < 0.75*y) {
                    if(keskus.isKakkonen()) {
                        keskus.setKakkonen(false);
                    } else {
                        keskus.setKakkonen(true);
                    }
                }
            }

//            g2.drawImage(viiva.getImage(), (int) (super.getWidth() * 0.05), (int) (super.getHeight() * 0.4), (int) (super.getWidth() * 0.3), (int) (super.getHeight() * 0.015), this);
//            g2.drawImage(viiva.getImage(), (int) (super.getWidth() * 0.95 - (super.getWidth() * 0.3)), (int) (super.getHeight() * 0.4), (int) (super.getWidth() * 0.3), (int) (super.getHeight() * 0.015), this);
//            g2.drawImage(osoitin.getImage(), (int) (((keskus.getPaivitysvali() - 30) / 170) * super.getWidth() * 0.3 + super.getWidth() * 0.05), (int) (super.getHeight() * 0.4), 10, 15, this);
//            g2.drawImage(osoitin.getImage(), (int) ((keskus.getLeveys() / 60) * super.getWidth() * 0.3 + super.getWidth() * 0.65), (int) (super.getHeight() * 0.4), 10, 15, this);
//            if (keskus.isKakkonen()) {
//                g2.drawImage(seinaON.getImage(), (int) (super.getWidth() / 4 - super.getWidth() * 0.15), (int) (super.getHeight() * 0.6), (int) (super.getWidth() * 0.2), (int) (super.getHeight() * 0.06), this);
//            } else {
//                g2.drawImage(seinaEI.getImage(), (int) (super.getWidth() / 4 - super.getWidth() * 0.15), (int) (super.getHeight() * 0.6), (int) (super.getWidth() * 0.2), (int) (super.getHeight() * 0.06), this);
//            }
//            g2.drawImage(quit.getImage(), (int) (super.getWidth() * (0.75) - super.getWidth() * 0.05), (int) (super.getHeight() * 0.6), (int) (super.getWidth() * 0.2), (int) (super.getHeight() * 0.06), this);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    void setKeskus(Keskus keskus) {

        this.keskus = keskus;
    }

    private void kysyNumerot() {
        int vaikeustaso = Integer.parseInt(JOptionPane.showInputDialog("Vaikeustaso eli kuinka usein päivittyy peli"));
        int leveys = Integer.parseInt(JOptionPane.showInputDialog("Kentän leveys"));
        int korkeus = Integer.parseInt(JOptionPane.showInputDialog("Kentän korkeus"));
        this.keskus.setPaivitysvali(vaikeustaso);
        this.keskus.setLeveys(leveys);
        this.keskus.setKorkeus(korkeus);

    }

}
