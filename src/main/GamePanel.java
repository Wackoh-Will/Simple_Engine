package main;

import javax.swing.*;
import java.awt.*;

import Weapons.Ak_47; //import the AK
import Weapons.Gun;
import Weapons.Bullet;

import entity.Player; // import the Player class
import entity.Enemy;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

//for enemy spawning
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

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


    //might want to make private later
    public boolean gameover = false;

    public Gun currentGun = new Ak_47();

    public ArrayList<Bullet> bulletList = new ArrayList<>();

    //booleans for keystroke
    public boolean upPressed, downPressed, leftPressed, rightPressed, ePressed, rPressed, m1Pressed;


    //other booleans
    public boolean can_shoot = true;

    private int mouseX, mouseY;

    public GamePanel() {
        addMouseMotionListener(new MouseMotionAdapter() {
           @Override
           public void mouseMoved(MouseEvent e){
               mouseX = e.getX();
               mouseY = e.getY();
           }
           @Override
            public void mouseDragged(MouseEvent e){
               mouseMoved(e);
           }
        });
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);

        //for player spawning
        player = new Player(100, 200); // position on screen (x=100, y=200)

        //for enemy spawning
        enemySpawner = new EnemySpawner(this);

        this.setFocusable(true);
        this.addKeyListener(this);

        // For getting mouse input
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                if (e.getButton() == java.awt.event.MouseEvent.BUTTON1) {
                    m1Pressed = true;
                }
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                if (e.getButton() == java.awt.event.MouseEvent.BUTTON1) {
                    m1Pressed = false;
                }
            }
        });
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


        if(player.health <= 0) {
            gameover = true;
            System.out.println("GAME OVER!");
        }

        //Enemy spawning
        long currentTime = System.currentTimeMillis();


        // Update existing enemies
        for (Enemy e : enemies) {
            e.update(this);
        }

        //for enemy spawning
        enemySpawner.update();

        //for Gun actions
        if (can_shoot) {
            currentGun.update(
                    this,              // GamePanel
                    player.getX(),     // player's X
                    player.getY(),     // player's Y
                    mouseX,            // mouse X on screen
                    mouseY             // mouse Y on screen
            );
        }

        for (Bullet b : bulletList) {
            b.update();
        }

        //handles bullet collision with enemy
        for (int i = 0; i < bulletList.size(); i++) {
            Bullet b = bulletList.get(i);
            b.update();

            for (int j = 0; j < enemies.size(); j++) {
                Enemy e = enemies.get(j);
                if (b.getBounds().intersects(e.getHitbox())) {
                    e.takeDamage(currentGun.getDamage());
                    bulletList.remove(i);
                    i--;
                    break;
                }
            }
        }
        enemies.removeIf(e -> !e.alive);
        bulletList.removeIf(b -> b.offscreen(getWidth(), getHeight()));


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
        Graphics2D g2 = (Graphics2D) g;


        // Draw the player
        player.draw(g);


        // Draw the weapons
        currentGun.draw(g2, player.getX(), player.getY(), mouseX, mouseY);

        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }

        for (Bullet b : bulletList) {
            b.draw(g2);
        }

        //Health Bar
        g.setColor(Color.GREEN);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        String healthText = player.health + " / 100";
        int x = 10;
        int y = getHeight() - 10;
        g.drawString(healthText, x, y);

        //Ammo
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        String AmmoText = currentGun.getAmmo() + " / " + currentGun.getMagSize();
        int x_ammo = 10;
        int y_ammo = getHeight() - 30;
        g.drawString(AmmoText, x_ammo, y_ammo);

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) upPressed = true;

        if (code == KeyEvent.VK_S) downPressed = true;

        if (code == KeyEvent.VK_A) leftPressed = true;

        if (code == KeyEvent.VK_D) rightPressed = true;

        if (code == KeyEvent.VK_E) ePressed = false;

        if (code == KeyEvent.VK_R) rPressed = true;

     }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) upPressed = false;

        if (code == KeyEvent.VK_S) downPressed = false;

        if (code == KeyEvent.VK_A) leftPressed = false;

        if (code == KeyEvent.VK_D) rightPressed = false;

        if (code == KeyEvent.VK_E) ePressed = true;

        if (code == KeyEvent.VK_R) rPressed = false;

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // unused
    }


    public Player getPlayer() {
        return player;
    }
}