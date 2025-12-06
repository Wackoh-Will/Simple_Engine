package main;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import entity.Enemy;

public class EnemySpawner {
    private GamePanel gp;
    private Random random;


    private EnemySpawner enemySpawner;
    private List<Enemy> enemies = new ArrayList<>();
    private long lastSpawnTime = 0;
    private final long spawnInterval = 3000;



    public EnemySpawner(GamePanel gp){
        this.gp = gp;
        this.random = new Random();
    }
    public void spawnRandomEnemy() {
        int x = (int)(Math.random() * (gp.getWidth() - 32));
        int y = (int)(Math.random() * (gp.getHeight() - 32));
        gp.addEnemy(new Enemy(x, y));
    }
    // Checks if it's time to spawn a new enemy
    public void update() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastSpawnTime > spawnInterval) {
            spawnRandomEnemy();
            lastSpawnTime = currentTime;
        }
    }
}
