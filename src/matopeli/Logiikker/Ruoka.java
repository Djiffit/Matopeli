/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matopeli.Logiikker;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Konsta
 */
public class Ruoka {

    private int x;
    private boolean ok;
    private int y;
    private ArrayList<Pala> palat;

    Ruoka(int leveys, int korkeus) {
        this.x = leveys;
        this.y= korkeus;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    Ruoka(ArrayList<Pala> palat, int leveys, int korkeus) {
        ok = false;
        int ruokax = 0;
        this.palat = palat;
        int ruokay = 0;
        x = 0;
        while (x == 0 || y == 0 || x == leveys - 1 || y == leveys - 1) {
            laskeKoordinaatit(ruokay, ruokax, leveys, korkeus);
            for (Pala p : palat) {
                if (p.getX() == x && p.getY() == y) {
                    x =0;
                }
            }
        }

    }
    
    public void annaKoordinaatit(int x, int y){
        this.x = x;
        this.y = y;
    }

    private void laskeKoordinaatit(int ruokay, int ruokax, int leveys, int korkeus) {
        Random random = new Random();
        x = (int) ((leveys - 1) * random.nextDouble());
        y = (int) ((korkeus - 1) * random.nextDouble());
        System.out.println(x + " " + y);

    }

}
