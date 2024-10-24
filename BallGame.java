/********************************************************************************
 *                                                                              *
 *  Copyright (c) 2024 Ibiyemisi Gbenebor                                      *
 *                                                                              *
 *  All rights reserved.                                                        *
 *                                                                              *
 *  This software is provided "as is", without any express or implied warranty, *
 *  including but not limited to the warranties of merchantability, fitness for *
 *  a particular purpose, and noninfringement. In no event shall the authors    *
 *  or copyright holders be liable for any claim, damages, or other liability,  *
 *  whether in an action of contract, tort, or otherwise, arising from, out of, *
 *  or in connection with the software or the use or other dealings in the       *
 *  software.                                                                   *
 *                                                                              *
 ********************************************************************************/

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.*;

public class BallGame extends JPanel {

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

        setBackground(new Color(210, 180, 140));
        setPreferredSize(new Dimension(640, 480)); // Set default size

        add(userScoreLabel);
        add(AIScoreLabel);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                removeBall(e.getX(), e.getY(), true);
            }
        });

        Thread gameThread = new Thread() {
            public void run() {
                game();
            }
        };

        gameThread.start();
    }

    @Override
    public void paintComponent(Graphics g) {

        for (int i = 0; i < ballList.size(); i++) {
            Ball b = ballList.get(i);
            g.setColor(b.getColor());
            g.fillOval((int) (b.getPosX() - b.getRadius()), (int) (b.getPosY() - b.getRadius()),
                    (int) (2 * b.getRadius()), (int) (2 * b.getRadius()));
        }
    }

    private ArrayList<Ball> createBallList(int count) {
        ArrayList<Ball> balls = new ArrayList<Ball>();

        for (int i = 0; i < count; i++) {
            balls.add(new Ball(640, 480));
        }

        return balls;
    }
    
    public synchronized void removeBall(int mouseX, int mouseY, boolean isUserMouseClick) {
        Iterator<Ball> iterator = ballList.iterator();
        while (iterator.hasNext()) {
            Ball b = iterator.next();
            if (Math.sqrt(Math.pow(mouseX - b.getPosX(), 2) + Math.pow(mouseY - b.getPosY(), 2)) <= b.getRadius()) {
                iterator.remove();
                if (isUserMouseClick) {
                    userScore++;
                    userScoreLabel.setText(getUserScore());
                } else {
                    AIScore++;
                    AIScoreLabel.setText(getAIScore());
                }
                repaint();
                return;
            }
        }
    }

    private String getUserScore() {
        return "Your Score: " + this.userScore;
    }

    private String getAIScore() {
        return "AI Score: " + this.AIScore;
    }

    public ArrayList<Ball> getBallList() {
        return this.ballList;
    }

    /* loops to keep bouncing balls window open and balls moving */
    private void game() {
        while (!ballList.isEmpty()) {
            for (int i = 0; i < ballList.size(); i++) {
                ballList.get(i).updateBallPosition(getWidth(), getHeight());
                repaint();
            }
            try {
                Thread.sleep(1000 / UPDATE_RATE);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        // Post winner results and close the JFrame when the ball list is empty
        SwingUtilities.invokeLater(() -> {
            if (userScore > AIScore) {
                JOptionPane.showMessageDialog(this, "You win with a score of " + userScore + "!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            } else if (AIScore > userScore) {
                JOptionPane.showMessageDialog(this, "AI wins with a score of " + AIScore + "!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "It's a tie with both scoring " + userScore + "!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
            }

            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (topFrame != null) {
                topFrame.dispose();
            }
        });
    }
}
