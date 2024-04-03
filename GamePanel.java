import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


public class GamePanel extends JPanel implements KeyListener {

    final int playerSpeed = 5;

    final int gravity = 3;

    Player p1;
    Player p2;
    private List<Integer> keyPressed=new ArrayList<>();
    public GamePanel() {
        setPreferredSize(new Dimension(900, 1200));
        this.addKeyListener(this);
        p1 = new Player(0, 0, 30, 30, playerSpeed, Color.red);
        p2 = new Player(0, 0, 30, 30,playerSpeed, Color.blue);

        setFocusable(true);
    }
    private void startGameLoop() {
        Timer timer = new Timer(16, new ActionListener() { // 60 frames per second
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGameState();
                repaint();
            }
        });
        timer.start();
    }

    private void updateGameState() {
        // Apply gravity to player 1
        applyGravity(p1);

        // Apply gravity to player 2
        applyGravity(p2);
        startGameLoop(); 
    }

    private void applyGravity(Player player) {

        int newY = player.getY() + gravity;
        player.setY(newY);

        // Apply gravity acceleration to vertical velocity
        // double newVerticalVelocity =  + gravity;
        // player.setVerticalVelocity(Math.min(newVerticalVelocity, MAX_FALL_SPEED));

        // Check for collisions with ground or obstacles
        // Adjust player position and velocity accordingly
        // Implement collision detection and response logic here
        // For simplicity, we assume no collisions in this example
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        System.out.println("sdasdasd");
        if(!keyPressed.contains(keyCode)){
                keyPressed.add(keyCode);
        }
        for (Integer key : keyPressed) {
            switch (key) {
                case KeyEvent.VK_W:
                    if (p1.getY() > 0)
                        p1.setY(p1.getY() - 10);
                    repaint();
                    break;
                case KeyEvent.VK_A:
                    if (p1.getX() > 0)
                        p1.setX(p1.getX() - 10);
                    repaint();
                    break;
    
                case KeyEvent.VK_S:
                  if (p1.getY() + p1.getHeight() < getHeight())
                        p1.setY(p1.getY() + 10);
                    repaint();
                    break;
                case KeyEvent.VK_D:
                if(p1.getX()+p1.getWidth()<getWidth())
                        p1.setX(p1.getX() + 10);
                    repaint();
                    break;
                case KeyEvent.VK_UP:
                    if (p2.getY() > 0)
                        p2.setY(p2.getY() - 10);
                    repaint();
                    break;
                case KeyEvent.VK_DOWN:
                if(p2.getY()+p2.getHeight()<getHeight())
                    p2.setY(p2.getY() + 10);
                    repaint();
                    break;
    
                case KeyEvent.VK_LEFT:
                if(p2.getX()>0)
                    p2.setX(p2.getX() - 10);
                    repaint();
                    break;
                case KeyEvent.VK_RIGHT:
                if(p2.getX()+p2.getWidth()<getWidth())
                    p2.setX(p2.getX() + 10);
                    repaint();
                    break;
            }
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        int keyCode=e.getKeyCode();
        keyPressed.remove(Integer.valueOf(keyCode));

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

        g.setColor(p2.getColor());
        g.fillRect(p2.getX(), p2.getY(), p2.getWidth(), p2.getHeight());
    }

}