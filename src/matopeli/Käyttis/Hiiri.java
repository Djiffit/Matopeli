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
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if (mx >= keskus.getResoX() / 2 - 200 && mx < keskus.getResoX() / 2 + 200) {
            if (my >= 100 && my <= 225) {
                keskus.setKakkonen(false);
                kysyNumerot();
                keskus.setState(1);
            }
        }
        if (mx >= keskus.getResoX() / 2 - 200 && mx < keskus.getResoX() / 2 + 200) {
            if (my >= 370 && my <= 495) {
                keskus.setKakkonen(true);
                kysyNumerot();
                keskus.setState(1);
            }
        }
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
