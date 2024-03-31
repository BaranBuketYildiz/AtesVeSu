import java.awt.*;
import javax.swing.*;



public class GamePanel extends JPanel{
     



    Player p1;

    public GamePanel(){
        setPreferredSize(new Dimension(900, 1200));

         p1 = new Player(0, 0, 100, 75, Color.red);

    }
    @Override
    protected void paintComponent(Graphics g) {
            // TODO Auto-generated method stub
            super.paintComponent(g);

            g.setColor(p1.getColor());
            g.fillRect(p1.getX(), p1.getY(), p1.getWidth(), p1.getHeight());
    }
   

}