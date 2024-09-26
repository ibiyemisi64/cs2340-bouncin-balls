import javax.swing.JFrame;


public class BouncingBallsByYem {
   public static void main(String[] args) {
    // Run GUI in the Event Dispatcher Thread (EDT) instead of main thread.

    Draw draw = new Draw(20, 2, 3);
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
       public void run() {
          // Set up main window (using Swing's Jframe)
          JFrame frame = new JFrame("Yemisi's Bouncing Balls");
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.setContentPane(draw);
          frame.pack();
          frame.setVisible(true);
       }
    });
 }
}