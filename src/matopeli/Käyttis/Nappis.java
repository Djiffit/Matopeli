/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matopeli.Käyttis;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javafx.scene.input.KeyCode;
import matopeli.Logiikker.*;


public class Nappis implements KeyListener{

    public void setKeskus(Keskus keskus) {
        this.keskus = keskus;
    }
    private Keskus keskus;

    public Nappis(Keskus keskus) {
        this.keskus = keskus;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == (KeyEvent.VK_UP)) {
            keskus.getMato().setSuunta(Suunta.YLOS);
            System.out.println("yl");
        }
        if(e.getKeyCode() == (KeyEvent.VK_DOWN)) {
            keskus.getMato().setSuunta(Suunta.ALAS);
            System.out.println("al");
        }
        if(e.getKeyCode() == (KeyEvent.VK_LEFT)) {
            keskus.getMato().setSuunta(Suunta.VASEN);
            System.out.println("va");
        }
        if(e.getKeyCode() == (KeyEvent.VK_RIGHT)) {
            keskus.getMato().setSuunta(Suunta.OIKEA);
            System.out.println("oi");
        }
        if(e.getKeyCode() == (KeyEvent.VK_ESCAPE)) {
            keskus.setState(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
     }
    
}
