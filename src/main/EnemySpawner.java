package main;
import java.util.Random;
import entity.Enemy;

public class EnemySpawner {
    private GamePanel gp;
    private Random random;

    public EnemySpawner(GamePanel gp){
        this.gp = gp;
        this.random = new Random();
    }
    public void spawnRandomEnemy() {
        int x = (int)(Math.random() * (gp.getWidth() - 32));
        int y = (int)(Math.random() * (gp.getHeight() - 32));
        gp.addEnemy(new Enemy(x, y));
    }
}
