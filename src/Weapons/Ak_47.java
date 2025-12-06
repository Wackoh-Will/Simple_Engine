package Weapons;

import main.GamePanel;
import entity.Player;

import java.awt.*;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


public class Ak_47 extends Gun {
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

        if (gp.rPressed && reload_time == 0) {
            ammo = magSize;
            reload_time += 60;


        }
    }
    @Override
    public void shoot(GamePanel gp){
        if(fire_time == 0){

            if (ammo > 0 && gp.m1Pressed && !gp.gameover) {
                fire_time += 10;

                System.out.println("*AK bang*");
                ammo--;
            } else if(ammo == 0 && gp.m1Pressed) {
                System.out.println("*Click*");
            }
        }else{
            fire_time--;
        }
    }
    @Override
    public void r_count(GamePanel gp) {
        while(reload_time > 0 && !gp.gameover) {
            reload_time--;
        }
    }
}
