package schach;

import schach.Figur.FigurFarbe;

public class Main {

    private Schach schach;

    public static void main(String[] args) {
        Main main = new Main();
        main.schach = new Schach();
        main.schach.setzeFenster(new StartFenster(main));
    }

    public void starteSpiel(String name1, String name2) {
        schach.setzeSpieler(new Spieler(name1, FigurFarbe.WEISS), new Spieler(name2, FigurFarbe.SCHWARZ));
        schach.setzeFenster(new SpielFenster(schach, this));
    }

    public void neustarten() {
        Spieler spieler1 = schach.spieler1;
        Spieler spieler2 = schach.spieler2;
        schach = new Schach();
        schach.setzeSpieler(spieler1, spieler2);
    }

    public void beenden() {
        schach.setzeFenster(new EndFenster(schach, this));
    }
}
