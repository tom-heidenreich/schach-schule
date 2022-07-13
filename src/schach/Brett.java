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
        FigurType type = FigurType.DAME;
        // feld[4][3] = new Figur(4, 3, type, FigurFarbe.SCHWARZ);
        // feld[3][4] = new Figur(4, 3, type, FigurFarbe.WEISS);
        
        for (int k = 0; k < 8; k++) {
             feld[7][k] = new Figur(k, 7, aufstellung[k], FigurFarbe.SCHWARZ);
             feld[0][k] = new Figur(k, 0, aufstellung[k], FigurFarbe.WEISS);
             feld[1][k] = new Figur(k, 1, FigurType.BAUER, FigurFarbe.WEISS);
             feld[6][k] = new Figur(k, 6, FigurType.BAUER, FigurFarbe.SCHWARZ);

        }
    }

    public void move(int x1, int y1, int x2, int y2) {      
       if (feld[x2][y2] == null) {
            feld[x2][y2] = feld[x1][y1];
            feld[x1][y1] = null;
       }
        
        
    }
//give positons of all figures
    public Figur[][] getFeld() {
        return feld;
    }

    public boolean istBesetzt(int x, int y) {
        return feld[x][y] != null;
    }

    public void kick(int x, int y) {
        feld[x][y] = null;
    }
}