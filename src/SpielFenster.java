import javax.swing.JFrame;

public class SpielFenster {
    
    private final JFrame frame;

    public SpielFenster() {
        frame = new JFrame("Schach");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void zeige() {
        frame.setVisible(true);
    }

    public void verstecke() {
        frame.setVisible(false);
    }

    public void zeichne(Schach schach) {
        // get players
        String spieler1 = schach.getSpieler1();
        String spieler2 = schach.getSpieler2();
    }
}