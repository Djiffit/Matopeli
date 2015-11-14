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
public class Keskus {

    private boolean havio;
    private boolean kakkonen;
    private int paivitysvali;
    private Mato mato;
    private ArrayList<Pala> palat;
    private Ruoka ruoka;
    private int state;
    private int resoX;
    private int resoY;
    private int pisteet;

    public Mato getMato() {
        return mato;
    }
    private int leveys;
    private int k;
    private int laskuri;
    private int korkeus;

    public int getLeveys() {
        return leveys;
    }

    public int getResoX() {
        return resoX;
    }

    public int getResoY() {
        return resoY;
    }

    public void setLeveys(int leveys) {
        this.leveys = leveys;
        palat.get(0).setX(16);
    }

    public void setResoX(int resoX) {
        this.resoX = resoX;
    }

    public void setResoY(int resoY) {
        this.resoY = resoY;
    }

    public int getKorkeus() {
        return korkeus;
    }

    public void setKorkeus(int korkeus) {
        this.korkeus = korkeus;
        palat.get(0).setY(5);
    }

    public int getState() {
        return state;
    }

    public Keskus(int korkeus, int leveys) {
        this.resoX = 1000;
        this.k = 0;
        this.resoY = 1920;
        this.kakkonen = true;
        this.state = 0;
        this.paivitysvali = 30;
        this.korkeus = korkeus;
        this.leveys = leveys;
        this.havio = false;
        this.mato = new Mato();
        this.palat = mato.getPalat();
        this.ruoka = new Ruoka(2, 2);
        this.pisteet = 0;
        this.laskuri = 600/paivitysvali;
        
    }

    public void setState(int state) {
        this.state = state;
        this.mato = new Mato();
        this.palat = mato.getPalat();
        this.ruoka = new Ruoka(palat, leveys, korkeus);
    }

    public void setKakkonen(boolean kakkonen) {
        this.kakkonen = kakkonen;
    }

    public int getPisteet() {
        return pisteet;
    }

    public void Paivitys() {
        if (this.laskuri == 0) {
            this.pisteet--;
            this.laskuri = 600/paivitysvali;
        } else {
            this.laskuri--;
        }
        boolean sy = false;
        Pala pala = new Pala(1, 2, null);
        if (palat.get(0).getX() == ruoka.getX() && palat.get(0).getY() == ruoka.getY()) {
            int monta = palat.size();
            pala = new Pala(palat.get(monta - 1).getX(), palat.get(monta - 1).getY(), palat.get(monta - 1));
            sy = true;
            ruoka = new Ruoka(palat, leveys, korkeus);
            pisteet += 8;
        }
        mato.liiku();
        for (int i = palat.size() - 1; i >= 1; i--) {
            palat.get(i).liiku();
        }
        if (sy) {
            palat.add(pala);
        }
        int paax = palat.get(0).getX();
        int paay = palat.get(0).getY();
        for (int i = 2; i < palat.size(); i++) {
            if (paax == palat.get(i).getX() && palat.get(i).getY() == paay) {
                this.havio = true;
                this.state = 0;
            }
        }
        if (kakkonen) {
            if (paax == 0) {
                palat.get(0).setX(leveys - 1);
                for (int j = palat.size() - 1; j >= 1; j--) {
                    palat.get(j).liiku();
                }
            } else if (paay == 0) {
                palat.get(0).setY(korkeus - 1);
                for (int j = palat.size() - 1; j >= 1; j--) {
                    palat.get(j).liiku();
                }
            } else if (paay == korkeus) {
                palat.get(0).setY(1);
                for (int j = palat.size() - 1; j >= 1; j--) {
                    palat.get(j).liiku();
                }

            } else if (paax == leveys) {
                palat.get(0).setX(1);
                for (int j = palat.size() - 1; j >= 1; j--) {
                    palat.get(j).liiku();
                }

            }
        } else {
            if (palat.size() > 3) {
                if (paax == 0 || paay == 0 || paax >= leveys || paay >= korkeus) {
                    this.havio = true;
                    this.state = 0;
                }
            }
        }
    }

    public void setHavio(boolean havio) {
        this.havio = havio;
    }

    public int getPaivitysvali() {
        return paivitysvali;
    }

    public boolean isKakkonen() {
        return kakkonen;
    }

    public void setPaivitysvali(int paivitysvali) {
        this.paivitysvali = paivitysvali;
    }

    public ArrayList<Pala> getPalat() {
        return palat;
    }

    public Ruoka getRuoka() {
        return ruoka;
    }

    public boolean havio() {
        return this.havio;
    }
}
