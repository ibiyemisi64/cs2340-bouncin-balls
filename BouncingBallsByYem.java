import javax.swing.JFrame;


public class BouncingBallsByYem {
   public static void main(String[] args) {
    // Run GUI in the Event Dispatcher Thread (EDT) instead of main thread.

    javax.swing.SwingUtilities.invokeLater(new Runnable() {
       public void run() {
          // Set up main window (using Swing's Jframe)
          JFrame frame = new JFrame("Yemisi's Bouncing Balls");
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.setContentPane(new Draw());
          frame.pack();
          frame.setVisible(true);
       }
    });
 }
}