package Weapons;
import main.GamePanel;

public abstract class Gun {
    protected int ammo;
    protected int magSize;
    protected int damage;
    protected int reload_time;
    protected int fire_time;

    public Gun(int magSize, int damage, int reload_time, int fire_time) {
        this.magSize = magSize;
        this.damage = damage;
        this.ammo = magSize;
    }
    public abstract void reload(GamePanel gp);
    public abstract void shoot(GamePanel gp);
    public abstract void r_count(GamePanel gp);

    public void update(GamePanel gp) {
        //Still need to finish this
    }
}
