package schach;
public class Main {
    public static void main(String[] args) {

        Spieler spieler1 = new Spieler("Spieler 1");
        Spieler spieler2 = new Spieler("Spieler 2");

        Schach schach = new Schach(spieler1, spieler2);

        schach.setzeFenster(new SpielFenster(schach));
    }
}
