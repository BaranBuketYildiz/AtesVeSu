import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class GamePanel extends JPanel implements KeyListener {

    Player p1;
    Player p2;


    public GamePanel() {
        setPreferredSize(new Dimension(900, 1200));
        this.addKeyListener(this);
        p1 = new Player(0, 0, 100, 75, Color.red);
        p2 = new Player(0, 0, 100, 75, Color.blue);

        setFocusable(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        System.out.println("sdasdasd");
        switch (keyCode) {
            case KeyEvent.VK_W:
                p1.setY(p1.getY() - 10);
                repaint();
                break;
            case KeyEvent.VK_A:
                p1.setX(p1.getX() - 10);
                repaint();
                break;

            case KeyEvent.VK_S:
                p1.setY(p1.getY() + 10);
                repaint();
                break;
            case KeyEvent.VK_D:
                p1.setX(p1.getX() + 10);
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
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);

        g.setColor(p1.getColor());
        g.fillRect(p1.getX(), p1.getY(), p1.getWidth(), p1.getHeight());
    }

 

}