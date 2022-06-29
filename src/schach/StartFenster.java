package schach;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class StartFenster extends Fenster {

    private static final Dimension dimension = new Dimension(500, 300);

    public StartFenster() {
        super(60);

        this.setSize(dimension);
    }

    @Override
    protected void zeichne(BufferStrategy strategy) {
        
        Graphics g = strategy.getDrawGraphics();

        g.clearRect(0, 0, (int) dimension.getWidth(), (int) dimension.getHeight());

        g.setColor(Color.black);
        g.drawRect(150, 100, 200, 100);

        // draw text
        g.drawString("Schach", 200, 150);

        strategy.show();
    }
}