package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import entity.Player;

public class Enemy {
    private int x, y;
    private BufferedImage sprite;
    public boolean alive = true;
    private int damage_timer;

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
    private void checkPlayerCollision(Player p) {
        if (damage_timer > 0){
            --damage_timer;
        }
        if (this.getHitbox().intersects(p.getHitbox()) && damage_timer <= 0) {
            damage_timer = 40;
            System.out.println("COLLISION!");
            p.health -= 10;
        }
    }

    public void update(GamePanel gp) {
        checkPlayerCollision(gp.getPlayer());
    }

    public int health = 80; // or whatever default

    public void takeDamage(int dmg) {
        health -= dmg;
        if (health <= 0) {
            alive = false;
        }
    }

}
