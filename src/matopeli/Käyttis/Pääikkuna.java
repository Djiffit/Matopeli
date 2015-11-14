package matopeli.Käyttis;

import java.awt.Dimension;
import java.io.IOException;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import matopeli.Logiikker.*;

public class Pääikkuna implements Runnable {

    private JFrame frame;
    private Keskus keskus;
    private Piirtoalusta alusta;
    private Nappis nappis;
    private Hiiri hiiri;

    public Pääikkuna() throws IOException {
        keskus = new Keskus(15, 20);
        keskus.setPaivitysvali(50);
        this.nappis = new Nappis(keskus);
        this.alusta = new Piirtoalusta(keskus);
        this.hiiri = new Hiiri(keskus);
        run();
    }

    @Override
    public void run() {
        frame = new JFrame("Matopeli");
        frame.setPreferredSize(new Dimension(1920, 1020));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(0, 0));
        frame.pack();
        frame.addKeyListener(nappis);
        frame.add(alusta);
        frame.addMouseListener(hiiri);
        long aika = 0;
        long delta = 0;
        long sekunti = 0;
        int upds= 0;
        frame.setVisible(true);
        keskus.setResoX(frame.getWidth());
        alusta.setKeskus(keskus);
        keskus.setResoY(frame.getHeight());
        while (true) {

            if (keskus.havio()) {
//                boolean kakkonen = keskus.isKakkonen();
//                keskus = new Keskus(keskus.getKorkeus(), keskus.getLeveys());
//                alusta.setKeskus(keskus);
//                nappis.setKeskus(keskus);
//                hiiri.setKeskus(keskus);
//                keskus.setKakkonen(kakkonen);
                keskus.setHavio(false);

            }
            
            if (keskus.getState() == 1) {
                delta = System.currentTimeMillis();
                if (delta - sekunti >= 1000) {
                    System.out.println(upds + " /s");
                    sekunti = delta;
                    upds = 0;
                }
                if (delta - aika >= keskus.getPaivitysvali()) {
                    aika = delta;
                    keskus.Paivitys();
                    upds++;
                    alusta.repaint();
                }

            } else {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Pääikkuna.class.getName()).log(Level.SEVERE, null, ex);
                }
                alusta.repaint();
            }

        }
    }
}
