package schach;

import schach.Figur.FigurFarbe;
import schach.Figur.FigurType;

public class Brett {
    private final Figur[][] feld;
    FigurType[] aufstellung = {FigurType.TURM, FigurType.SPRINGER, FigurType.LÄUFER, FigurType.DAME, FigurType.KÖNIG, FigurType.LÄUFER, FigurType.SPRINGER, FigurType.TURM};

    public Brett() {
        feld = new Figur[8][8];
        feldFuellen();
    }







    private void feldFuellen() {
        for (int k = 0; k < 8; k++) {
             feld[7][k] = new Figur(k, 7, aufstellung[k], FigurFarbe.SCHWARZ);
             feld[0][k] = new Figur(k, 0, aufstellung[k], FigurFarbe.WEISS);
             feld[1][k] = new Figur(k, 1, FigurType.BAUER, FigurFarbe.WEISS);
             feld[6][k] = new Figur(k, 6, FigurType.BAUER, FigurFarbe.SCHWARZ);

        }
    }

    public void move(Figur figur, int x, int y) {      
       if (feld[x][y] == null) {
           feld[x][y] = feld[figur.getX()][figur.getY()];
       }
        
        
    }
//give positons of all figures
    public Figur[][] getFeld() {
        return feld;
    }

    public boolean istBesetzt(int x, int y) {
        return feld[x][y] != null;
    }
}