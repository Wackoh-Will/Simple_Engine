package main;

import javax.swing.*;
import java.awt.*;
import entity.Player; // import the Player class
import entity.Enemy;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

//for enemy spawning
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GamePanel extends JPanel implements Runnable, KeyListener {
    private boolean running = false;
    private Thread gameThread;

    private final int WIDTH = 800;
    private final int HEIGHT = 600;

    private Player player;// our player object

    //for enemy spawning
    private EnemySpawner enemySpawner;
    private List<Enemy> enemies = new ArrayList<>();
    private long lastSpawnTime = 0;
    private final long spawnInterval = 3000;

    public boolean upPressed, downPressed, leftPressed, rightPressed, ePressed;


    public GamePanel() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);

        //for player spawning
        player = new Player(100, 200); // position on screen (x=100, y=200)

        //for enemy spawning
        enemySpawner = new EnemySpawner(this);

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
        ePressed = false;

        //Hit detection
        Rectangle playerHitbox = player.getHitbox();

        for (Enemy enemy : enemies) {
            if (playerHitbox.intersects(enemy.getHitbox())) {
                System.out.println("COLLISION!");
                // damage or something idk yet
            }
        }

        //Enemy spawning
        long currentTime = System.currentTimeMillis();

        // Checks if it's time to spawn a new enemy
        if (currentTime - lastSpawnTime > spawnInterval) {
            enemySpawner.spawnRandomEnemy();
            lastSpawnTime = currentTime;
        }

        // Update existing enemies
        for (Enemy e : enemies) {
            e.update(this);
        }


    }

    //enemy spawning
    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the player
        player.draw(g);

        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) upPressed = true;

        if (code == KeyEvent.VK_S) downPressed = true;

        if (code == KeyEvent.VK_A) leftPressed = true;

        if (code == KeyEvent.VK_D) rightPressed = true;

        if (code == KeyEvent.VK_E) ePressed = false;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) upPressed = false;

        if (code == KeyEvent.VK_S) downPressed = false;

        if (code == KeyEvent.VK_A) leftPressed = false;

        if (code == KeyEvent.VK_D) rightPressed = false;

        if (code == KeyEvent.VK_E) ePressed = true;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // unused
    }
}