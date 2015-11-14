/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matopeli.Logiikker;

import java.util.ArrayList;

/**
 *
 * @author Konsta
 */
public class Mato {

    private ArrayList<Pala> palat;
    private int x;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    private int y;
    private Pala paa;
    private Suunta suunta;
    private boolean muuta;

    public Mato() {
        this.muuta = true;
        this.suunta = Suunta.OIKEA;
        this.palat = new ArrayList<>();
        paa = new Pala(1, 1, null);
        palat.add(paa);
        Pala ekapala = new Pala(palat.get(0).getX(), palat.get(0).getY()+1, paa);
        palat.add(ekapala);
        Pala tokapala = new Pala(palat.get(0).getX(), palat.get(0).getY()+2, ekapala);
        palat.add(tokapala);
        Pala kolmaspala = new Pala(palat.get(0).getX(), palat.get(0).getY()+3, tokapala);
        palat.add(kolmaspala);
    }

    public void setSuunta(Suunta su) {
        if (muuta) {
            if (this.suunta == Suunta.YLOS) {
                if (su == Suunta.VASEN || su == Suunta.OIKEA) {
                    this.suunta = su;
                    muuta=false;
                }
            } else if (this.suunta == Suunta.ALAS) {
                if (su == Suunta.VASEN || su == Suunta.OIKEA) {
                    this.suunta = su;
                    muuta=false;
                }
            } else if (this.suunta == Suunta.VASEN) {
                if (su == Suunta.YLOS || su == Suunta.ALAS) {
                    this.suunta = su;
                    muuta=false;
                }
            } else if (this.suunta == Suunta.OIKEA) {
                if (su == Suunta.ALAS || su == Suunta.YLOS) {
                    this.suunta = su;
                    muuta=false;
                }
            }
        }
    }

    public ArrayList<Pala> getPalat() {
        return palat;
    }

    public Suunta getSuunta() {
        return suunta;
    }

    void liiku() {
        muuta = true;
        if (suunta == Suunta.ALAS) {
            paa.setY(paa.getY() + 1);
        }
        if (suunta == Suunta.YLOS) {
            paa.setY(paa.getY() - 1);
        }
        if (suunta == Suunta.VASEN) {
            paa.setX(paa.getX() - 1);
        }
        if (suunta == Suunta.OIKEA) {
            paa.setX(paa.getX() + 1);
        }
    }
}
