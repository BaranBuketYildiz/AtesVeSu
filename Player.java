
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Player extends JComponent {

        private Color color;
        private int x;
        private int y;
        private int height;
        private int width;
        private int speed;
        

      
        public Player(int x, int y, int height, int width, int speed, Color c) {
                this.x = x;
                this.y = y;
                this.height = height;
                this.width = width;
                this.color = c;
                this.speed = speed;
                setFocusable(true);

        }

        @Override
        public void paintComponents(Graphics g) {
                super.paintComponents(g);

                g.setColor(color);
                g.fillRect(x, y, width, height);
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

        public int getSpeed() {
                return speed;
        }

        public void setSpeed(int speed) {
                this.speed = speed;
        }
}