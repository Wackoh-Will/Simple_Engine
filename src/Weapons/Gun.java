package Weapons;
import entity.Player;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Gun {
    protected int ammo;
    protected int magSize;
    protected int damage;
    protected int reload_time;
    protected int fire_time;
    protected int muzzle_vel;

    protected BufferedImage sprite;

    public Gun(int magSize, int damage, int reload_time, int fire_time, int muzzle_vel) {
        this.magSize = magSize;
        this.damage = damage;
        this.ammo = magSize;
        this.muzzle_vel = muzzle_vel;
        loadSprite();
    }
    public abstract void reload(GamePanel gp);
    public abstract void shoot(GamePanel gp, int playerX, int playerY, int mouseX, int mouseY);
    public abstract void r_count(GamePanel gp);

    protected abstract void loadSprite();

    public void draw(Graphics g, int x, int y, int mouseX, int mouseY ) {
        if (sprite == null) return;

        Graphics2D g2 = (Graphics2D) g.create();

        double angle = Math.atan2(mouseY - y, mouseX - x);

        int centerX = sprite.getWidth() / 2;
        int centerY = sprite.getHeight() / 2;
        g2.rotate(angle, x + centerX, y + centerY);

        g2.drawImage(sprite, x, y, null);

        g2.dispose();
    }


    public void update(GamePanel gp, int playerX, int playerY, int mouseX, int mouseY) {
        reload(gp);
        shoot(gp, playerX, playerY, mouseX, mouseY);
        r_count(gp);
    }
}
