import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Random;

public class Draw extends JPanel {
    // Container box's width and height
    private static final int BOX_WIDTH = 640;
    private static final int BOX_HEIGHT = 480;


    // Fields
    private static final int UPDATE_RATE = 30; // Number of refresh per second

    // Balls 
    private Ball[] ballList;
    private static final int BALL_COUNT = 20;
    
    public Draw() {
        this.ballList = createBallList(BALL_COUNT);
        this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));

        Thread gameThread = new Thread() {
            public void run() {
                bouncingBalls();
            }
        };

        gameThread.start();
    }

    public Ball[] createBallList(int count){
        Ball[] balls = new Ball[BALL_COUNT];
        for (int i = 0; i < BALL_COUNT; i++) {
            // Create a new Ball instance
            balls[i] = new Ball(BOX_WIDTH, BOX_HEIGHT);
        }
        return balls;
    }

    /** Custom rendering codes for drawing the JPanel */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Paint background

        // Draw the box
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, BOX_WIDTH, BOX_HEIGHT);

        for (int i = 0; i < BALL_COUNT; i++) {
            ballList[i].updatePosition();
            paintBall(g,ballList[i]);
            }
    }

    public void paintBall(Graphics g, Ball b) {
        // Draw the ball
        g.setColor(b.getColor());
        g.fillOval((int) (b.getX() - b.getRadius()), (int) (b.getY() - b.getRadius()),
        (int) (2 * b.getRadius()), (int) (2 * b.getRadius()));
    }

    public void bouncingBalls(){
        while(true) {
            // Refresh the display
            repaint(); // Callback paintComponent()
            // Delay for timing control and give other threads a chance
            try {
               Thread.sleep(1000 / UPDATE_RATE);  // milliseconds
            } catch (InterruptedException ex) { }
        }
    }
}
