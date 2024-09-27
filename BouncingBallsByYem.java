import javax.swing.*;

public class BouncingBallsByYem {

   /*
    * Main program to run Bouncing Balls program.
    */
   public static void main(String[] args) {

      javax.swing.SwingUtilities.invokeLater(new Runnable() {
         public void run() {

            BallGame draw = new BallGame();

            JFrame frame = new JFrame("Yemisi's Bouncing Balls");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(draw);
            frame.pack();
            frame.setVisible(true);
            frame.setSize(640, 480);

         }
      });
   }
}