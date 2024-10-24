import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

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
    private ArrayList<Ball> ballList;
    private int userScore = 0;
    private int AIScore = 0;
    private JLabel userScoreLabel, AIScoreLabel;
    Random random = new Random();

    public BallGame() {

        this.ballList = createBallList(20);
        this.userScoreLabel = new JLabel(getUserScore());
        this.AIScoreLabel = new JLabel(getAIScore());


        add(userScoreLabel);
        add(AIScoreLabel);

        Thread gameThread = new Thread() {
            public void run() {
                bouncingBalls();
            }
        };

        Thread aiThread = new Thread() {
            public void run() {
                while (true) {
                    if (!ballList.isEmpty()) {
                        // Select a random ball and "click" on it
                        Ball randomBall = ballList.get(random.nextInt(ballList.size()));
                        handleMouseClick(randomBall.x, randomBall.y, false);
                    }

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        // Handle exception if needed
                    }
                
                }
            }
        };

        aiThread.start();

        gameThread.start();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMouseClick(e.getX(), e.getY(), true);
            }
        });
    }

    /** Create list to store balls running in the JPanel */
    public ArrayList<Ball> createBallList(int count) {
        ArrayList<Ball> balls = new ArrayList<Ball>();

        for (int i = 0; i < count; i++){
            balls.add(new Ball(640, 480));
        }

        return balls;
    }

    /** Custom rendering codes for drawing the JPanel and Balls */
    @Override
    public void paintComponent(Graphics g) {
        Color tan = new Color(210, 180, 140);
        setBackground(tan);
        setPreferredSize(new Dimension(640, 480)); // Set default size

        for (int i = 0; i < ballList.size(); i++) {
            // Only paint balls in visible state
            if (ballList.get(i).getVisibility()) {
                paintBall(g, ballList.get(i));
            }
        }
    }

    /** Helper for rendering codes for drawing the Balls */
    public void paintBall(Graphics g, Ball b) {
        g.setColor(b.getColor());
        g.fillOval((int) (b.x - b.getRadius()), (int) (b.y - b.getRadius()),
                (int) (2 * b.getRadius()), (int) (2 * b.getRadius()));
    }

    public void handleMouseClick(int mouseX, int mouseY, boolean isUser) {

        Iterator<Ball> iterator = ballList.iterator();
        while (iterator.hasNext()) {
            Ball b = iterator.next();
            if (Math.sqrt(Math.pow(mouseX - b.x, 2) + Math.pow(mouseY - b.y, 2)) <= b.getRadius()) {
                b.setVisibility(false);
                iterator.remove();
                if (isUser) {
                    userScore++;
                    userScoreLabel.setText(getUserScore());
                } else {
                    AIScore++;
                    AIScoreLabel.setText(getAIScore());
                }
                repaint();

                break;
            }
        }
    }

    public String getUserScore() {
        return "Your Score: " + this.userScore;
    }

    public String getAIScore() {
        return "AI Score: " + this.AIScore;
    }

    /* Main loops to keep bouncing balls window open and balls moving */
    public void bouncingBalls() {
        while (!ballList.isEmpty()) {
            for (int i = 0; i < ballList.size(); i++) {
                ballList.get(i).updateBallPosition(getWidth(), getHeight());
                repaint();
            }
            try {
                Thread.sleep(1000 / UPDATE_RATE); // milliseconds
            } catch (InterruptedException ex) {
                // Handle exception if needed
            }
        }
    }

}
