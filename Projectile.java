
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class Projectile extends Entity {
        private int speedX;

        public Projectile(Color color, int x, int y, int width, int height, int speedX) {
                super(color, x, y, width, height);
                this.speedX = speedX;
        }

        public void move(List<Entity> entities) {
                int newX = this.getX() + speedX;
                this.setX(newX);

        }

        public int getSpeedX() {
                return speedX;
        }

        public void setSpeedX(int speedX) {
                this.speedX = speedX;
        }
        
}