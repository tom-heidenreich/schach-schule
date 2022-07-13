package schach;
import java.awt.Dimension;
import java.awt.image.BufferStrategy;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StartFenster extends Fenster {

    private static final Dimension dimension = new Dimension(500, 300);

    public StartFenster(Main main) {
        super(60);

        this.setSize(dimension);

        JFrame frame = this.getFrame();

        JPanel panel = new JPanel();

        JButton startButton = new JButton("Start");
        // set bounds
        startButton.setBounds(100, 100, 100, 50);

        JTextField name1 = new JTextField(20);
        JTextField name2 = new JTextField(20);

        // click listener
        startButton.addActionListener(e -> {
            String name1Text = name1.getText();
            String name2Text = name2.getText();
            main.starteSpiel(name1Text, name2Text);
        });

        panel.add(name1);
        panel.add(name2);
        panel.add(startButton);

        frame.add(panel);
    }

    @Override
    protected void zeichne(BufferStrategy strategy) {
    }
}