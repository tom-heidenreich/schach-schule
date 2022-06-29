package schach;
import java.awt.Dimension;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class StartFenster extends Fenster {

    private static final Dimension dimension = new Dimension(500, 300);

    public StartFenster() {
        super(60);

        this.setSize(dimension);

        JFrame frame = this.getFrame();
    }

    @Override
    protected void zeichne(BufferStrategy strategy) {
    }
}