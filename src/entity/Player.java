package entity; // keep this if your file is in the entity folder

import java.awt.*;                // For Graphics, Color, etc.
import java.awt.image.BufferedImage;  // For sprite images
import javax.imageio.ImageIO;     // For loading images
import java.io.IOException;       // For handling image load errors
import main.GamePanel;

public class Player {
    private int x, y;
    private BufferedImage sprite;
    private int speed = 4;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        loadSprite();
    }

    private void loadSprite() {
        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/assets/player.png")); //loads the sprite for the player
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load player sprite");
        }
    }

    public void draw(Graphics g) {
        if (sprite != null) {
            g.drawImage(sprite, x, y, null);
        }
    }

    //handles changes like movement and other motions of the player
    public void update(GamePanel gp) {
        if (gp.upPressed) y -= speed;
        if (gp.downPressed) y += speed;
        if (gp.leftPressed) x -= speed;
        if (gp.rightPressed) x += speed;
    }
}
