package schach;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public abstract class Fenster {

    private final JFrame frame;
    private final Thread worker;

    public Fenster(int frames) {
        
        frame = new JFrame("Schach");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.setVisible(true);

        // buffered frames
        frame.createBufferStrategy(2);
        BufferStrategy bufferStrategy = frame.getBufferStrategy();

        frame.setVisible(false);

        int frameDelay = 1000 / frames;
        
        worker = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    long start = System.currentTimeMillis();
                    zeichne(bufferStrategy);
                    try {
                        long delay = frameDelay- System.currentTimeMillis() - start;
                        if(delay < 0) delay = 0;
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    protected JFrame getFrame() {
        return frame;
    }

    protected void setSize(Dimension dimension) {
        frame.setSize(dimension);
    }

    protected final void setAdapter(MouseAdapter adapter) {
        frame.addMouseListener(adapter);
    }

    protected abstract void zeichne(BufferStrategy strategy);

    public void start() {
        this.frame.setVisible(true);
        worker.start();
    }

    public void stop() {
        this.frame.setVisible(false);
        worker.interrupt();
    }
}
