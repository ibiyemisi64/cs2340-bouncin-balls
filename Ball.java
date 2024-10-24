import java.awt.*;
import java.util.Random;

public class Ball {
    /********************************************************************************/
    /*                                                                              */
    /*  Ball                                                                        */
    /*  Version: 1.1                                                                */
    /*  Date: 9/26/2024                                                             */
    /*  Author: Yemisi Gbenebor                                                     */
    /*                                                                              */
    /********************************************************************************/

    public int x;
    public int y;
    public int dx;
    public int dy;
    public Color color;
    private int ballRadius = 30;

    Color[] shadesOfBlue = new Color[]{
        new Color(75, 0, 130), // indigo
        new Color(135, 206, 250), // Light Sky Blue
        new Color(176, 224, 230), // Powder Blue
        new Color(0, 0, 255)      // Blue
    };

    public Ball(int boxX, int boxY) {
        Random random = new Random();
        this.x = random.nextInt(boxX);
        this.y = random.nextInt(boxY);
        this.dx = random.nextInt(5) + 2; // adjusted baseline so speeds aren't too slow
        this.dy = random.nextInt(3) + 2;
        this.color = shadesOfBlue[random.nextInt(4)];
    }

    public Color getColor() {
        return this.color;
    }

    public int getRadius() {
        return this.ballRadius;
    }

    public void updateBallPosition(int boxX, int boxY) {
        if (x - ballRadius < 0 || x + ballRadius > boxX) {
            dx = -dx;
        }

        if (y - ballRadius < 0 || y + ballRadius > boxY) {
            dy = -dy;
        }

        x += dx;
        y += dy;
    }

    @Override
    public String toString() {
        return "Ball{" + "x='" + this.x + ", y=" + this.y + ", color='" + this.color.toString() + '\'' + '}';
    }

}
