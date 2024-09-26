import java.awt.*;
import java.util.Random;

public class Ball {
    
    private int ballRadius;
    private int ballX;
    private int ballY;
    private int speedX;
    private int speedY;
    private int boxHeight;
    private int boxWidth;
    private Color ballColor;

    public Ball(int BOX_WIDTH, int BOX_HEIGHT){
        Random random = new Random();
        this.ballRadius = 20;
        this.ballX = random.nextInt(BOX_WIDTH - (this.getRadius() * 2)) + this.getRadius();
        this.ballY = random.nextInt(BOX_HEIGHT - (this.getRadius() * 2)) + this.getRadius();
        this.ballColor = generateRandomColor();
        this.speedX = 2;
        this.speedY = 3;
        this.boxHeight = BOX_HEIGHT;
        this.boxWidth = BOX_WIDTH;
    }

    // private Color generateRandomColor() {
    //     Random random = new Random();
    //     int red = random.nextInt(256);
    //     int green = random.nextInt(256);
    //     int blue = random.nextInt(256);
    //     return new Color(red, green, blue);
    // }
    
    private Color generateRandomColor() {
        // Ensure intensity is between 0 and 255
        Random random = new Random();
        int intensity = random.nextInt(256);
        int red = intensity;     // Red component (0 to 255)
        int green = 0;           // Green component (fixed at 0)
        int blue = 255 - (intensity/5); // Blue component (inversely related to red)
        return new Color(red, green, blue);
    }


    

    public Color getColor() {
        return ballColor;
    }

    public int getRadius() {
        return ballRadius;
    }

    public int getX() {
        return ballY;
    }

    public int getY() {
        return ballX;
    }

    public void updatePosition() {
        // Update position
        
        ballX += speedX;
        ballY += speedY;

        // Check boundaries and adjust
        if (ballX - ballRadius < 0) {
            speedX = -speedX;
            ballX = ballRadius;
        } else if (ballX + ballRadius >= boxWidth) {
            speedX = -speedX;
            ballX = boxWidth - ballRadius;
        }

        if (ballY - ballRadius < 0) {
            speedY = -speedY;
            ballY = ballRadius;
        } else if (ballY + ballRadius >= boxHeight) {
            speedY = -speedY;
            ballY = boxHeight - ballRadius;
        }
    }

    @Override
    public String toString() {
        return "Ball{" + "x='" + ballX + ", y="+ ballY + " color='" + ballColor.toString() + '\'' + '}';
    }

}

