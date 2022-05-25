import javax.swing.JFrame;;

public class SpielFenster {
    
    private final JFrame frame;
    private final Dimension size;

    public SpielFenster() {
        frame = new JFrame("Schach");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    public void zeige() {
        frame.setVisible(true);
    }

    public void verstecke() {
        frame.setVisible(false);
    }

    public void zeichne(Schach schach) {

        // get players
        Spieler spieler1 = schach.spieler1;
        Spieler spieler2 = schach.spieler2;

        // get board
        Brett brett = schach.brett;

        BufferedImage image = new BufferedImage(, , BufferedImage.TYPE_INT_RGB);
    }
}