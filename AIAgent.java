
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

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class AIAgent implements Runnable {

    BallGame game;
    Random random = new Random();

    public AIAgent(BallGame g) {
        game = g;
    }

    @Override
    public void run() {

        ArrayList<Ball> balls = game.getBallList();
        
        /* loops to keep AI agent clicking balls */
        while (!balls.isEmpty()) {

            //  ball "click"
            Ball randomBall = balls.get(random.nextInt(balls.size()));
            game.removeBall(randomBall.getPosX(), randomBall.getPosY(), false);
        

            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println(e);
            }

        }

    }

}