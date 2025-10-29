import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {  // Run Swing code on the Event Dispatch Thread
            JFrame window = new JFrame("Elfbound Zero"); // Create a window
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);

            GamePanel gamePanel = new GamePanel(); // Create our custom panel
            window.add(gamePanel); // Add it to the window
            window.pack(); // Resize window to fit panel
            window.setLocationRelativeTo(null); // Center the window
            window.setVisible(true); // Show it

        });
    }
}
