package schach;

import schach.Figur.FigurFarbe;

public class Spieler {
    public final String name;

    public final FigurFarbe farbe;

    public Spieler(String name, FigurFarbe farbe) {
        this.name = name;
        this.farbe = farbe;
    }
}
