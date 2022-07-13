package schach;

import java.awt.image.BufferStrategy;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EndFenster extends Fenster {

    public EndFenster(Schach schach, Main main) {
        super(60);

        Spieler gewinner = schach.gewinner();
        String name = gewinner.name;

        JFrame frame = this.getFrame();

        JPanel panel = new JPanel();

        JButton neustartButton = new JButton("Neustart");
        // set bounds
        neustartButton.setBounds(100, 100, 100, 50);

        JLabel label = new JLabel("Spieler " + name + " hat gewonnen!");

        // click listener
        neustartButton.addActionListener(e -> {
            main.neustarten();
        });

        panel.add(label);
        panel.add(neustartButton);

        frame.add(panel);
    }

    @Override
    protected void zeichne(BufferStrategy strategy) { 
    }
}
