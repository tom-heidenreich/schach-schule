import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;

import Figur.FigurFarbe;

public class SpielFenster extends Fenster {
    
    private final Schach schach;

    protected static final Dimension dimension = new Dimension(1200, 1000);

    private static final int paddingX = 200;
    private static final int paddingY = 100;

    private static final int size = 100;

    private final static MouseAdapter adapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent evt) {
            int x = evt.getX();
            int y = evt.getY();
            // if inside board
            if(x - paddingX > 0 && x < dimension.width - paddingX && y > 0 && y < dimension.height - paddingY) {
                int xPos = (x - 1) / size;
                int yPos = (y - 1) / size;
                System.out.println(xPos + " " + yPos);
            }
        }
    };

    public SpielFenster(Schach schach) {
        // click listener
        super(adapter);
        this.schach = schach;
        this.setSize(dimension);
        this.run();
    }

    protected void zeichne(BufferStrategy bufferStrategy) {

        Graphics graphics = bufferStrategy.getDrawGraphics();

        graphics.clearRect(0, 0, (int) dimension.getWidth(), (int) dimension.getHeight());

        // get players
        Spieler spieler1 = schach.spieler1;
        Spieler spieler2 = schach.spieler2;

        // get figures
        Figur[][] figuren = schach.brett.getFeld();

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
                graphics.fillRect(i * size + paddingX, j * size + paddingY, size, size);

                Figur figur = figuren[i][j];

                if (figur != null) {
                    if(figur.type == FigurFarbe.WEISS) {
                        graphics.setColor(Color.gray);
                    } else {
                        graphics.setColor(Color.blue);
                    }
                    graphics.fillOval(i * size + paddingX + size / 4, j * size + paddingY + size / 4, size / 2, size / 2);
                }
            }
        }

        bufferStrategy.show();
    }
}