import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;

public class BallGame extends JPanel {

    /********************************************************************************/
    /*                                                                              */
    /*  BallGame Class                                                              */
    /*  Version: 1.0                                                                */
    /*  Date: 9/26/2024                                                             */
    /*  Author: Yemisi Gbenebor                                                     */
    /*                                                                              */
    /********************************************************************************/

    // Number of refresh per second
    private static final int UPDATE_RATE = 30;
    private static final int BALL_COUNT = 20;
    private Ball[] ballList;
    private int score;
    private JLabel scoreLabel;

    public BallGame() {
        this.score = 0;
        this.ballList = createBallList(BALL_COUNT);
        this.scoreLabel = new JLabel(getScore());

        Thread gameThread = new Thread() {
            public void run() {
                bouncingBalls();
            }
        };

        add(scoreLabel);
        gameThread.start();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e);
            }
        });
    }

    /** Create list to store balls running in the JPanel */
    public Ball[] createBallList(int count) {
        Ball[] balls = new Ball[count]; // Use the parameter count instead of BALL_COUNT

        for (int i = 0; i < count; i++) { // Use the parameter count instead of BALL_COUNT
            balls[i] = new Ball(640, 480);
        }
        return balls;
    }

    /** Custom rendering codes for drawing the JPanel and Balls */
    @Override
    public void paintComponent(Graphics g) {
        Color tan = new Color(210, 180, 140);
        setBackground(tan);
        setPreferredSize(new Dimension(640, 480)); // Set default size

        for (int i = 0; i < BALL_COUNT; i++) {
            // Only paint balls in visible state
            if (ballList[i].getVisibility()) {
                paintBall(g, ballList[i]);
            }
        }
    }

    /** Helper for rendering codes for drawing the Balls */
    public void paintBall(Graphics g, Ball b) {
        g.setColor(b.getColor());
        g.fillOval((int) (b.ballX - b.getRadius()), (int) (b.ballY - b.getRadius()),
                (int) (2 * b.getRadius()), (int) (2 * b.getRadius()));
    }

    public void handleMouseClick(MouseEvent e) {
        int clickX = e.getX();
        int clickY = e.getY();

        for (int i = 0; i < BALL_COUNT; i++) {
            int bX = ballList[i].ballX;
            int bY = ballList[i].ballY;
            int radius = ballList[i].getRadius();

            // If the ball is still visible, check if the mouse clicked it.
            // If the ball was clicked, increase the b.speedY
            if (ballList[i].getVisibility()) {
                if (Math.sqrt(Math.pow(clickX - bX, 2) + Math.pow(clickY - bY, 2)) <= radius) {
                    ballList[i].setVisibility(false);
                    increaseScore();
                    repaint();
                    break;
                }
            }
        }
    }

    public void updateBallPosition(Ball b) {
        // Update position
        int boxHeight = getHeight();
        int boxWidth = getWidth();
        int ballRadius = b.getRadius();

        b.ballX += b.speedX;
        b.ballY += b.speedY;

        // Check boundaries and adjust
        if (b.ballX - ballRadius < 0) {
            b.speedX = -b.speedX;
            b.ballX = ballRadius;
        } else if (b.ballX + ballRadius > boxWidth) {
            b.speedX = -b.speedX;
            b.ballX = boxWidth - ballRadius;
        }

        if (b.ballY - ballRadius < 0) {
            b.speedY = -b.speedY;
            b.ballY = ballRadius;
        } else if (b.ballY + ballRadius > boxHeight) {
            b.speedY = -b.speedY;
            b.ballY = boxHeight - ballRadius;
        }

        repaint();
    }

    private void moveBalls(Ball[] balls) {
        for (int i = 0; i < BALL_COUNT; i++) {
            updateBallPosition(balls[i]);
        }
    }

    public void increaseScore() {
        this.score = this.score + 1;
        this.scoreLabel.setText(getScore());
    }

    public String getScore() {
        return "Score: " + this.score;
    }

    /* Main loops to keep bouncing balls window open and balls moving */
    public void bouncingBalls() {
        while (true) {
            moveBalls(ballList);
            try {
                Thread.sleep(1000 / UPDATE_RATE); // milliseconds
            } catch (InterruptedException ex) {
                // Handle exception if needed
            }
        }
    }

}
