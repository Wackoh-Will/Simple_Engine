import javax.swing.*;
import java.awt.*;
    public class GamePanel extends JPanel implements Runnable {
        private boolean running = false;
        private Thread gameThread;
        private final int WIDTH = 800;
        private final int HEIGHT = 600;

        public GamePanel() {
            this.setPreferredSize(new Dimension(WIDTH, HEIGHT)); // Important! Without this, pack() won't work
            this.setBackground(Color.BLACK); // Set background color
        }
        public void startGame() {
            running = true;
            gameThread = new Thread(this);
            gameThread.start();
        }

        @Override
        public void run() {
            while (running) {
                update();   // updates game objects
                repaint();  // renders the changing screen

            }
        }
        private void update() {
            // This is where you’ll change your game objects
            // For now, it can be empty
            //test
        }
    }

