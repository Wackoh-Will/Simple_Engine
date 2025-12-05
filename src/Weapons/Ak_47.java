package Weapons;

import main.GamePanel;



public class Ak_47 extends Gun {
    public Ak_47() {super(30, 40, 0, 0);}

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

            if (ammo > 0 && gp.m1Pressed) {
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
        while(reload_time > 0) {
            reload_time--;
        }
    }
}
