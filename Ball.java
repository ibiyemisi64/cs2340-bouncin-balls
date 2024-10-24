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


import java.awt.*;
import java.util.Random;

public class Ball {

    private int x;
    private int y;
    private int dx;
    private int dy;
    private Color color;
    private int ballRadius = 45;

    Color[] shadesOfBlue = new Color[]{
        new Color(75, 0, 100),  
        new Color(135, 206, 250),
        new Color(176, 224, 230), 
        new Color(0, 0, 255)      
    };

    public Ball(int boxX, int boxY) {
        Random random = new Random();
        x = random.nextInt(boxX);
        y = random.nextInt(boxY);
        dx = random.nextInt(10);
        dy = random.nextInt(10);
        color = shadesOfBlue[random.nextInt(4)];
    }

    public Color getColor() {
        return color;
    }

    public int getPosX() {
        return x;
    }

    public int getPosY() {
        return y;
    }

    public int getRadius() {
        return ballRadius;
    }

    public void updateBallPosition(int boxX, int boxY) {

        x += dx;
        y += dy;

        // Check boundaries and adjust
        if (x - ballRadius < 0) {
            dx = -dx;
            x = ballRadius;
        } else if (x + ballRadius > boxX) {
            dx = -dx;
            x = boxX - ballRadius;
        }

        if (y - ballRadius < 0) {
            dy = -dy;
            y = ballRadius;
        } else if (y + ballRadius > boxY) {
            dy = -dy;
            y = boxY - ballRadius;
        }

    }

    @Override
    public String toString() {
        return "Ball{" + "x='" + x + ", y=" + y + ", color='" + color.toString() + '\'' + '}';
    }

}
