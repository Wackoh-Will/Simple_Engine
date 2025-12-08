package Weapons;

import main.GamePanel;
import entity.Player;

import java.awt.*;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


public class Ak_47 extends Gun {
    boolean reloading = false; // this was just to make it show the changed number after Please think of a better way
    public Ak_47() {super(30, 40, 0, 0, 20);}

    @Override
    protected void loadSprite() {
        try{
            sprite = ImageIO.read(getClass().getResourceAsStream("/assets/items/Ak_74u.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load Ak_74u sprite");
        }
    }

    @Override
    public void reload(GamePanel gp) {
        if (reload_time > 0) {
            reload_time--;
        }
        if (gp.rPressed && reload_time <= 0) {
            reload_time = 100;
            reloading = true;
            System.out.println("Reloading!!");
        }
        if (reload_time <= 0 && reloading) {
            ammo = magSize;
            reloading = false;
        }
    }
    @Override
    public void shoot(GamePanel gp, int playerX, int playerY, int mouseX, int mouseY){
        if(fire_time == 0){
            if (ammo > 0 && gp.m1Pressed && !gp.gameover && reload_time <= 0) {
                fire_time += 10;
                --ammo;

                double angle = Math.atan2(mouseY - playerY, mouseX - playerX);

                int centerX = sprite.getWidth() / 2;
                int centerY = sprite.getHeight() / 2;

                double barrelLength = 15;
                double startX = playerX + centerX + Math.cos(angle) * barrelLength;
                double startY = playerY + centerY + Math.sin(angle) * barrelLength;

                gp.bulletList.add(new Bullet(startX, startY, mouseX, mouseY, muzzle_vel));
            } else if(ammo == 0 && gp.m1Pressed) {
                System.out.println("*Click*");
            }
        }else{
            --fire_time;
        }
    }
}
