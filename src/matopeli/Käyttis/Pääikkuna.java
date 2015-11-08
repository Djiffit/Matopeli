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
        this.nappis = new Nappis(keskus);
        this.alusta = new Piirtoalusta(keskus);
        this.hiiri = new Hiiri(keskus);
        run();
    }

    @Override
    public void run() {
        frame = new JFrame("Matopeli");
        frame.setPreferredSize(new Dimension(1920, 1000));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(0, 0));
        frame.pack();
        frame.addKeyListener(nappis);
        frame.add(alusta);
        frame.addMouseListener(hiiri);

        frame.setVisible(true);
        while (true) {
            keskus.setResoX(frame.getWidth());
            keskus.setResoY(frame.getHeight());
            if (keskus.havio()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Pääikkuna.class.getName()).log(Level.SEVERE, null, ex);
                }
                keskus = new Keskus(keskus.getKorkeus(), keskus.getLeveys());
                alusta.setKeskus(keskus);
                nappis.setKeskus(keskus);
                hiiri.setKeskus(keskus);

            }
            frame.repaint();
            try {
                Thread.sleep(keskus.getPaivitysvali());
            } catch (InterruptedException ex) {
                Logger.getLogger(Pääikkuna.class.getName()).log(Level.SEVERE, null, ex);
            }
            keskus.Paivitys();
            

        }
    }
}
