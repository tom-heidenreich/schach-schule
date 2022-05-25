import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class SpielFenster {
    
    private final JFrame frame;
    private final Dimension dimension;

    private final Schach schach;

    private final Thread worker;

    private final BufferStrategy bufferStrategy;

    public SpielFenster(Schach schach) {

        this.schach = schach;

        dimension = new Dimension(1200, 1000);

        frame = new JFrame("Schach");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(dimension);

        frame.setVisible(true);

        frame.createBufferStrategy(2);
        bufferStrategy = frame.getBufferStrategy();

        worker = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    zeichne();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        worker.run();
    }

    private void zeichne() {

        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.clearRect(0, 0, (int) dimension.getWidth(), (int) dimension.getHeight());

        // get players
        Spieler spieler1 = schach.spieler1;
        Spieler spieler2 = schach.spieler2;

        // get board
        Brett brett = schach.brett;

        graphics.setColor(Color.black);

        // draw player names
        graphics.setFont(graphics.getFont().deriveFont(16f));
        graphics.drawString(spieler1.name, 30, 70);
        graphics.drawString(spieler2.name, (int) dimension.getWidth() - 100, 70);

        // draw board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    graphics.setColor(Color.white);
                } else {
                    graphics.setColor(Color.black);
                }
                graphics.fillRect(i * 100 + 200, j * 100 + 100, 100, 100);
            }
        }

        bufferStrategy.show();
    }
}