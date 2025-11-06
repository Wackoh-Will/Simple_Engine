package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Enemy {
    private int x, y;
    private BufferedImage sprite;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        loadSprite();
    }
    public Rectangle getHitbox() {
        return new Rectangle(x, y, sprite.getWidth(), sprite.getHeight());
    }

    private void loadSprite() {
        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/assets/Enemy.png")); //loads the sprite for the Enemy
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load enemy sprite");
        }
    }

    public void draw(Graphics g) {
        if (sprite != null) {
            g.drawImage(sprite, x, y, null);
        }
    }
    public void update(GamePanel gp) {

    }
}
