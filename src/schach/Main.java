package schach;

import schach.Figur.FigurFarbe;

public class Main {
    public static void main(String[] args) {

        Spieler spieler1 = new Spieler("Spieler 1", FigurFarbe.WEISS);
        Spieler spieler2 = new Spieler("Spieler 2", FigurFarbe.SCHWARZ);

        Schach schach = new Schach(spieler1, spieler2);

        schach.setzeFenster(new SpielFenster(schach));
    }
}
