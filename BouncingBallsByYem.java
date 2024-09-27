import javax.swing.*;

public class BouncingBallsByYem {

    /********************************************************************************/
    /*                                                                              */
    /*  Bouncing Balls Game by Yem!                                                 */
    /*  Version: 1.0                                                                */
    /*  Date: 9/26/2024                                                             */
    /*  Author: Yemisi Gbenebor                                                     */
    /*                                                                              */
    /********************************************************************************/

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BallGame draw = new BallGame();

                JFrame frame = new JFrame("Yemisi's Bouncing Balls");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setContentPane(draw);
                frame.pack();
                frame.setSize(640, 480);
                frame.setVisible(true);
            }
        });
    }
}
