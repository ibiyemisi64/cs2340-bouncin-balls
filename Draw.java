import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.util.Formatter;
import java.util.Random;

public class Draw extends JPanel {
    // Container box's width and height
    private static final int BOX_WIDTH = 640;
    private static final int BOX_HEIGHT = 480;

    // Fields
    private int ballRadius;
    private int speedX;
    private int speedY;
    private int ballX;
    private int ballY;
    private static final int UPDATE_RATE = 30; // Number of refresh per second

    public Draw(int size, int speedX, int speedY) {
        this.ballRadius = size; // Ball's radius
        this.ballX = this.ballRadius + 50; // Ball's center (x, y)
        this.ballY = this.ballRadius + 20;
        this.speedX = speedX;
        this.speedY = speedY;

        this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));

        Thread gameThread = new Thread() {
            public void run() {
                bouncingBalls();
            }
        };

        gameThread.start();
    }

    public void updatePosition(int x, int y, int boxWidth, int boxHeight) {
        // Update position
        x += speedX;
        y += speedY;

        // Check boundaries and adjust
        if (x - ballRadius < 0) {
            speedX = -speedX;
            x = ballRadius;
        } else if (x + ballRadius > boxWidth) {
            speedX = -speedX;
            x = boxWidth - ballRadius;
        }

        if (y - ballRadius < 0) {
            speedY = -speedY;
            y = ballRadius;
        } else if (y + ballRadius > boxHeight) {
            speedY = -speedY;
            y = boxHeight - ballRadius;
        }

        ballX = x;
        ballY = y;

    }

    private Color generateRandomColor() {
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        return new Color(red, green, blue);
    }

    /** Custom rendering codes for drawing the JPanel */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Paint background

        // Draw the box
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, BOX_WIDTH, BOX_HEIGHT);

        paintBall(g);
    }

    public void paintBall(Graphics g) {

        // Draw the ball
        g.setColor(generateRandomColor());
        g.fillOval((int) (ballX - ballRadius), (int) (ballY - ballRadius),
        (int) (2 * ballRadius), (int) (2 * ballRadius));
    }

    public void bouncingBalls(){

        while(true) {
            updatePosition(ballX, ballY, BOX_WIDTH, BOX_HEIGHT);
            // Refresh the display
            repaint(); // Callback paintComponent()
            // Delay for timing control and give other threads a chance
            try {
               Thread.sleep(1000 / UPDATE_RATE);  // milliseconds
            } catch (InterruptedException ex) { }
        }
    }


}
