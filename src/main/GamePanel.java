package main;

import javax.swing.*;
import java.awt.*;
import entity.Player; // import the Player class
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


public class GamePanel extends JPanel implements Runnable, KeyListener {
    private boolean running = false;
    private Thread gameThread;

    private final int WIDTH = 800;
    private final int HEIGHT = 600;

    private Player player; // our player object

    public boolean upPressed, downPressed, leftPressed, rightPressed;


    public GamePanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);

        player = new Player(100, 200); // position on screen (x=100, y=200)

        this.setFocusable(true);
        this.addKeyListener(this);
    }

    public void startGame() {
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (running) {
            update();
            repaint();
            try {
                Thread.sleep(16); // ~60 FPS (1000ms / 60 ≈ 16)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        player.update(this);
    }


    // used to detect key input
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the player
        player.draw(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = true;
            System.out.println("W pressed");
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
            System.out.println("S pressed");
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
            System.out.println("A pressed");
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
            System.out.println("D pressed");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
            System.out.println("W released");
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
            System.out.println("S released");
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
            System.out.println("A released");
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
            System.out.println("D released");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // unused, but must be here
    }
}