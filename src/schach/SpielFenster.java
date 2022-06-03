package schach;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.imageio.ImageIO;

import schach.Figur.FigurFarbe;
import schach.Figur.FigurType;

public class SpielFenster extends Fenster {
    
    private final Schach schach;

    protected static final Dimension dimension = new Dimension(1200, 1000);

    private static final int paddingX = 200;
    private static final int paddingY = 100;

    private static final int size = 100;

    private final HashMap<String, BufferedImage> images;

    private final static MouseAdapter adapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent evt) {
            int x = evt.getX();
            int y = evt.getY();
            // if inside board
            if(x - paddingX > 0 && x < dimension.width - paddingX && y > 0 && y < dimension.height - paddingY) {
                int xPos = (x - 1) / size - 2;
                int yPos = (y - 1) / size - 1;
                System.out.println(xPos + " " + yPos);
            }
        }
    };

    public SpielFenster(Schach schach) {
        // click listener
        super(adapter);
        this.schach = schach;
        this.setSize(dimension);
        
        FigurType[] types = Figur.FigurType.values();
        this.images = new HashMap<String, BufferedImage>();
        for(int i = 0; i<types.length; i++) {
            char c = types[i].name().charAt(0);
            try {
                System.out.println(c);
                System.out.println(getClass().getResource("/images/w" + c + ".png"));
                this.images.put(types[i].name() + 'w', ImageIO.read(getClass().getResource("images/w" + c + ".png")));
                this.images.put(types[i].name() + 'b', ImageIO.read(getClass().getResource("images/b" + c + ".png")));
            }catch(Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
        }

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

                int x = i * size + paddingX;
                int y = j * size + paddingY;

                graphics.fillRect(x, y, size, size);

                Figur figur = figuren[j][i];

                if (figur != null) {
                    BufferedImage figurImage = null;
                    if(figur.farbe == FigurFarbe.WEISS) {
                        figurImage = this.images.get(figur.type.name() + 'w');
                    } else {
                        figurImage = this.images.get(figur.type.name() + 'b');
                    }
                    if(figurImage != null) graphics.drawImage(figurImage, x, y, size, size, null);
                }
            }
        }

        bufferStrategy.show();
    }
}