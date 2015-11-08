/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matopeli.Logiikker;

/**
 *
 * @author Konsta
 */
public class Pala {

    private int x;
    private int y;
    private Pala seuraava;

    Pala(int x, int y, Pala pala) {
        this.x = x;
        this.y = y;
        this.seuraava = pala;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Pala getSeuraava() {
        return seuraava;
    }

    void liiku() {
       this.x = seuraava.getX();
       this.y = seuraava.getY();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

}
