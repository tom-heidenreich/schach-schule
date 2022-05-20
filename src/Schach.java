public class Schach {
    private final Brett brett;

    private final Spieler spieler1;
    private final Spieler spieler2;

    public Schach(Spieler spieler1, Spieler spieler2) {
        brett = new Brett();

        this.spieler1 = spieler1;
        this.spieler2 = spieler2;
    }
}