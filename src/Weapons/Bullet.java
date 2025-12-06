package Weapons;

import java.awt.*;

public class Bullet {
    double x,y;
    double vx, vy;
    int width = 5, height = 5;

    public Bullet(double startX, double startY, double targetX, double targetY, double speed){
        this.x = startX;
        this.y = startY;

        double dx = targetX - startX;
        double dy = targetY - startY;
        double length = Math.sqrt(dx*dx + dy*dy);

        this.vx = (dx/length)*speed;
        this.vy = (dy/length)*speed;
    }

    public void update(){
        x += vx;
        y += vy;
    }
    public void draw(Graphics g){
        g.fillRect((int)x,(int)y,width,height);
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,width,height);
    }

}
