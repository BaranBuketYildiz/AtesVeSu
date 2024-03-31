
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class Player extends JFrame implements ActionListener, KeyListener {


        private Color color;
        private int x;
        private int y;
        private int height;
        private int width;

        
        public Player(int x, int y, int height, int width, Color c){
                this.x = x;
                this.y = y;
                this.height = height;
                this.width = width;
                this.color = c;

                addKeyListener(this);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
        }
        @Override
        public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        int keyCode=e.getKeyCode();

        switch(keyCode){
             
            case KeyEvent.VK_W:
            System.out.println("w");
                setY(getY()+10);
                repaint();
                break;
            case KeyEvent.VK_A:
            System.out.println("a");
                setX(getX()-10);
                repaint();
                break;      
            case KeyEvent.VK_D:
            System.out.println("D");
                 setX(getX()+10);
                 repaint();
                  break;
        }
        
        }
        @Override
        public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
        }
        @Override
        public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
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