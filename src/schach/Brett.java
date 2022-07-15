package schach;

import java.util.LinkedList;

import schach.Figur.FigurFarbe;
import schach.Figur.FigurType;

public class Brett {
    private final Figur[][] feld;
    FigurType[] aufstellung = { FigurType.TURM, FigurType.SPRINGER, FigurType.LÄUFER, FigurType.DAME, FigurType.KÖNIG,
            FigurType.LÄUFER, FigurType.SPRINGER, FigurType.TURM };

    public final LinkedList<Figur> geschlageneFiguren = new LinkedList<Figur>();
    private boolean protectList = false;

    public Brett() {
        feld = new Figur[8][8];
        feldFuellen();
    }

    private void feldFuellen() {
        // FigurType type = FigurType.DAME;
        // feld[4][3] = new Figur(4, 3, type, FigurFarbe.SCHWARZ);
        // feld[3][4] = new Figur(4, 3, type, FigurFarbe.WEISS);

        for (int k = 0; k < 8; k++) {
            feld[7][k] = new Figur(k, 7, aufstellung[k], FigurFarbe.SCHWARZ, this);
            feld[0][k] = new Figur(k, 0, aufstellung[k], FigurFarbe.WEISS, this);
            feld[1][k] = new Figur(k, 1, FigurType.BAUER, FigurFarbe.WEISS, this);
            feld[6][k] = new Figur(k, 6, FigurType.BAUER, FigurFarbe.SCHWARZ, this);

        }
    }

    public void move(int x1, int y1, int x2, int y2) {
        if (feld[x1][y1] != null && feld[x1][y1].type == FigurType.BAUER) {
            if (x2 == 7 || x2 == 0) {
                feld[x1][y1].type = FigurType.DAME;
            }
        }
        if (feld[x2][y2] == null) {
            feld[x2][y2] = feld[x1][y1];
            feld[x1][y1] = null;
        }

    }

    // give positons of all figures
    public Figur[][] getFeld() {
        return feld;
    }

    public boolean istBesetzt(int x, int y) {
        return feld[x][y] != null;
    }

    public void kick(int x, int y) {
        if(feld[x][y] != null) {
            Figur figur = feld[x][y];
            new Thread(() -> {
                // warte bis wieder sicher
                while(protectList);
                if(figur.type != FigurType.KÖNIG) geschlageneFiguren.add(figur);
            }).start();
        }
        feld[x][y] = null;
    }

    public boolean istSpielZuEnde() {
        return false;
    }

    public void setzeProtectList(boolean protectList) {
        this.protectList = protectList;
    }
}
