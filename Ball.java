import java.awt.*;
import java.util.Random;

public class Ball {
    /********************************************************************************/
    /*                                                                              */
    /*  Ball                                                                        */
    /*  Version: 1.0                                                                */
    /*  Date: 9/26/2024                                                             */
    /*  Author: Yemisi Gbenebor                                                     */
    /*                                                                              */
    /********************************************************************************/

    public int ballX;
    public int ballY;
    public int speedX;
    public int speedY;
    private boolean visible;
    public Color color;
    private int ballRadius = 60;

    public Ball(int boxX, int boxY) {
        Random random = new Random();
        this.ballX = random.nextInt(boxX);
        this.ballY = random.nextInt(boxY);
        this.speedX = random.nextInt(5); // adjusted baseline so speeds aren't too slow
        this.speedY = random.nextInt(3);
        this.visible = true;
        this.color = generateRandomBlue();
    }

    private Color generateRandomBlue() {
        Color[] shadesOfBlues = new Color[4];
        shadesOfBlues[0] = new Color(173, 216, 230); // Light Blue
        shadesOfBlues[1] = new Color(135, 206, 250); // Light Sky Blue
        shadesOfBlues[2] = new Color(176, 224, 230); // Powder Blue
        shadesOfBlues[3] = new Color(0, 0, 255); // Blue

        Random random = new Random();
        int i = random.nextInt(4);

        return shadesOfBlues[i];
    }

    public Color getColor() {
        return this.color;
    }

    public int getRadius() {
        return this.ballRadius;
    }

    public boolean getVisibility() {
        return this.visible;
    }

    public boolean setVisibility(boolean v) {
        visible = v;
        return visible;
    }

    @Override
    public String toString() {
        return "Ball{" + "x='" + this.ballX + ", y=" + this.ballY + ", color='" + this.color.toString() + '\'' + '}';
    }

}
