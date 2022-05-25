public class Schach {
    public final Brett brett;

    public final Spieler spieler1;
    public final Spieler spieler2;

    public Schach(Spieler spieler1, Spieler spieler2) {
        brett = new Brett();

        this.spieler1 = spieler1;
        this.spieler2 = spieler2;
    }
}