
import java.awt.*;
import javax.swing.*;


public class Player extends JFrame {


        Color color;
        int x;
        int y;
        int height;
        int width;

        

        public Player(int x, int y, int height, int width, Color c){
                this.x = x;
                this.y = y;
                this.height = height;
                this.width = width;
                this.color = c;
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
}