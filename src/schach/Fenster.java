package schach;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public abstract class Fenster {

    private final JFrame frame;
    private final Thread worker;

    public Fenster(MouseAdapter adapter) {
        

        frame = new JFrame("Schach");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        if(adapter != null) {
            frame.addMouseListener(adapter);
        }

        frame.setVisible(true);

        // buffered frames
        frame.createBufferStrategy(2);
        BufferStrategy bufferStrategy = frame.getBufferStrategy();

        worker = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    zeichne(bufferStrategy);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    protected final void run() {
        worker.run();
    }

    public Fenster() {
        this(null);
    }

    protected void setSize(Dimension dimension) {
        frame.setSize(dimension);
    }

    protected abstract void zeichne(BufferStrategy strategy);
}
