package schach;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
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

    private Position selectedPosition;

    private final MouseAdapter adapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent evt) {
            int x = evt.getX();
            int y = evt.getY();
            // if inside board
            if(x - paddingX > 0 && x < dimension.width - paddingX && y > 0 && y < dimension.height - paddingY) {
                int xPos = (x - 1) / size - 2;
                int yPos = (y - 1) / size - 1;
                 // aktueller Spieler
                Spieler aktuellerSpieler = schach.aktuellerSpieler();

                if(selectedPosition == null) {
                    Figur figur = schach.brett.getFeld()[yPos][xPos];
                    // if no position selected
                    if(figur == null) return;
                    // Figur auf der Position
                    if(figur.farbe != aktuellerSpieler.farbe) return;
                    selectedPosition = new Position(xPos, yPos);
                }
                else if(!selectedPosition.istGleich(new Position(xPos, yPos))) {
                    // Zielposition
                    Position position = new Position(xPos, yPos);

                    // Figur auf der Position
                    Figur figur = schach.brett.getFeld()[selectedPosition.y][selectedPosition.x];
                    if(figur == null) return;
                    if(figur.farbe != aktuellerSpieler.farbe) {
                        selectedPosition = position;
                        return;
                    }
                    
                    Figur ziel = schach.brett.getFeld()[position.y][position.x];
                    if(ziel == null || ziel.farbe != aktuellerSpieler.farbe) {
                        // if move is possible
                        boolean[][] moves = figur.getMoves(schach.brett.getFeld(), yPos, xPos);
                        for(int i = 0; i<moves.length; i++) {
                            for(int j = 0; j<moves[i].length; j++) {
                                System.out.print(moves[i][j] + " ");
                            }
                            System.out.println();
                            System.out.println();
                        }
                        if(!figur.getMoves(schach.brett.getFeld())[yPos][xPos]) return;

                        if(schach.brett.istBesetzt(position.y, position.x)) schach.brett.kick(position.y, position.x);
                        schach.brett.move(selectedPosition.y, selectedPosition.x, position.y, position.x);
                        schach.nächsterSpieler();
                    }
                    selectedPosition = null;
                }
            }
        }
    };

    public SpielFenster(Schach schach) {
        // click listener
        super(144);
        this.schach = schach;
        this.setSize(dimension);
        this.setAdapter(adapter);
        
        FigurType[] types = Figur.FigurType.values();
        this.images = new HashMap<String, BufferedImage>();
        for(int i = 0; i<types.length; i++) {
            char c = types[i].toKey();
            try {
                this.images.put(types[i].name() + 'w', ImageIO.read(new File("src/images/w" + c + ".png")));
                this.images.put(types[i].name() + 'b', ImageIO.read(new File("src/images/b" + c + ".png")));
            }catch(Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
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
        // underline text
        if(schach.spielerNummer() == 0) graphics.drawLine(30, 80, graphics.getFont().getSize() * spieler1.name.length(), 80);

        graphics.drawString(spieler2.name, (int) dimension.getWidth() - 100, 70);
        // underline text
        if(schach.spielerNummer() == 1) graphics.drawLine((int) dimension.getWidth() - (int) graphics.getFont().getSize() * spieler2.name.length() , 80, (int) dimension.getWidth() - 30, 80);

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

                boolean isSelected = selectedPosition != null && selectedPosition.x == i && selectedPosition.y == j;
                if(isSelected && schach.brett.istBesetzt(j, i)) {
                    graphics.setColor(new Color(122,122,122));
                    ((Graphics2D) graphics).setStroke(new java.awt.BasicStroke(3));
                    graphics.drawRect(x, y, size-2, size-2);
                }

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