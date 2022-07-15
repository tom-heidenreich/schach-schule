package schach;
public class Schach {
    public final Brett brett;

    public Spieler spieler1;
    public Spieler spieler2;

    private int aktuellerSpieler = 0;

    private Fenster fenster;

    public Schach() {
        brett = new Brett();
    }

    public void setzeSpieler(Spieler spieler1, Spieler spieler2) {
        this.spieler1 = spieler1;
        this.spieler2 = spieler2;
    }

    public void setzeFenster(Fenster fenster) {
        if(this.fenster != null) this.fenster.stop();
        this.fenster = fenster;
        if(fenster != null) this.fenster.start();
    }

    public Spieler aktuellerSpieler() {
        switch(aktuellerSpieler) {
            case 0: return spieler1;
            case 1: return spieler2;
            default: throw new RuntimeException("Spieler nicht gefunden");
        }
    }

    public void nächsterSpieler() {
        switch(aktuellerSpieler) {
            case 0: aktuellerSpieler = 1; break;
            case 1: aktuellerSpieler = 0; break;
            default: throw new RuntimeException("Spieler nicht gefunden");
        }
    }

    public int spielerNummer() {
        return aktuellerSpieler;
    }

    public Spieler gewinner() {
        return spieler1;
    }
}