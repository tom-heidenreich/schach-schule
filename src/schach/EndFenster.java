package schach;

import java.awt.Dimension;
import java.awt.image.BufferStrategy;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EndFenster extends Fenster {

    private static final Dimension dimension = new Dimension(500, 300);

    public EndFenster(Schach schach, Main main) {
        super(0);

        this.setSize(dimension);

        Spieler gewinner = schach.gewinner();
        String name = gewinner.name;

        JFrame frame = this.getFrame();

        JPanel panel = new JPanel();

        JButton neustartButton = new JButton("Neustart");
        JButton menuButton = new JButton("Hauptmenü");
        // set bounds
        neustartButton.setBounds(100, 100, 100, 50);
        menuButton.setBounds(100, 200, 100, 50);

        JLabel label = new JLabel("Spieler " + name + " hat gewonnen!");

        // click listener
        neustartButton.addActionListener(e -> {
            main.neustarten();
        });
        menuButton.addActionListener(e -> {
            main.reset();
            main.menu();
        });

        panel.add(label);
        panel.add(neustartButton);
        panel.add(menuButton);

        frame.add(panel);
    }

    @Override
    protected void zeichne(BufferStrategy strategy) { 
    }
}
