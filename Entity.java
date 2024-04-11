
import java.awt.*;
import javax.swing.*;

public class Entity {
        private Color color;
        private int x;
        private int y;
        private int height;
        private int width;

        public Entity(Color color, int x, int y, int height, int width) {
                this.color = color;
                this.x = x;
                this.y = y;
                this.height = height;
                this.width = width;
        }

        public boolean intersects(Entity otherEntity) {

                Rectangle r1 = new Rectangle(x, y, width, height);
                Rectangle r2 = new Rectangle(otherEntity.getX(), otherEntity.getY(), otherEntity.getWidth(),
                                otherEntity.getHeight());
                return r1.intersects(r2);

        }

        

        public Color getColor() {
                return color;
        }

        public void setColor(Color color) {
                this.color = color;
        }

        public int getX() {
                return x;
        }

        public void setX(int x) {
                this.x = x;
        }

        public int getY() {
                return y;
        }

        public void setY(int y) {
                this.y = y;
        }

        public int getHeight() {
                return height;
        }

        public void setHeight(int height) {
                this.height = height;
        }

        public int getWidth() {
                return width;
        }

        public void setWidth(int width) {
                this.width = width;
        }

}
