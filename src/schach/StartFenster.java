package schach;
import java.awt.Dimension;
import java.awt.image.BufferStrategy;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StartFenster extends Fenster {

    private static final Dimension dimension = new Dimension(500, 300);

    public StartFenster(Schach schach, Main main) {
        super(0);

        String name1Init = "Spieler 1";
        String name2Init = "Spieler 2";

        this.setSize(dimension);

        JFrame frame = this.getFrame();

        JPanel panel = new JPanel();

        JButton startButton = new JButton("Start");
        // set bounds
        startButton.setBounds(100, 100, 100, 50);

        JTextField name1 = new JTextField(20);
        JTextField name2 = new JTextField(20);

        panel.add(name1);
        panel.add(name2);
        panel.add(startButton);

        if(schach.spieler1 != null && schach.spieler2 != null) {
            name1Init = schach.spieler1.name;
            name2Init = schach.spieler2.name;

            JButton weiterspielenButton = new JButton("Spiel fortsetzen");
            weiterspielenButton.setBounds(100, 200, 100, 50);
            panel.add(weiterspielenButton);

            weiterspielenButton.addActionListener(e -> {
                main.spielFortsetzen(schach.spieler1.name, schach.spieler2.name);
            });
        }

        name1.setText(name1Init);
        name2.setText(name2Init);

        // click listener
        startButton.addActionListener(e -> {
            String name1Text = name1.getText();
            String name2Text = name2.getText();
            main.starteSpiel(name1Text, name2Text);
        });


        frame.add(panel);
    }

    @Override
    protected void zeichne(BufferStrategy strategy) {
    }
}