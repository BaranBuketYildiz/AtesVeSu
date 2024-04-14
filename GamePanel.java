import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GamePanel extends JPanel implements KeyListener, Runnable {

    final static int panelWidth = 1200;
    final static int panelHeight = 800;
    final static int groundHeight = 10;
    Player p1;
    List<Entity> entities = new ArrayList<>();
    private boolean[] keyPressed = new boolean[256]; // Using boolean array to track key states

    Thread game;
    Monster m1;

    public GamePanel() {

        p1 = new Player(Color.RED, 15, panelHeight - 30, 20, 20);
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        addKeyListener(this);
        setFocusable(true);
        setBackground(Color.WHITE);
        setDoubleBuffered(true);

        // maze
        entities.add(new Ground(Color.BLACK, 0, panelHeight - 60, groundHeight, panelWidth - 80));

        entities.add(new Ground(Color.BLACK, 80, panelHeight - 110, groundHeight, panelWidth - 80));

        entities.add(new Ground(Color.BLACK, 0, panelHeight - 160, groundHeight, panelWidth - 80));

        entities.add(new Ground(Color.BLACK, 80, panelHeight - 210, groundHeight, panelWidth - 80));

        entities.add(new Ground(Color.BLACK, 0, panelHeight - 260, groundHeight, panelWidth - 80));

        entities.add(new Ground(Color.BLACK, 80, panelHeight - 310, groundHeight, panelWidth - 80));

        entities.add(new Ground(Color.BLACK, 0, panelHeight - 360, groundHeight, panelWidth - 80));

        entities.add(new Ground(Color.BLACK, 80, panelHeight - 410, groundHeight, panelWidth - 80));

        entities.add(new Ground(Color.BLACK, 0, panelHeight - 460, groundHeight, panelWidth - 80));

        entities.add(new Ground(Color.BLACK, 80, panelHeight - 510, groundHeight, panelWidth - 80));
        entities.add(new Ground(Color.BLACK, 0, panelHeight - 560, groundHeight, panelWidth - 80));

        List<Entity> coinsToAdd = new ArrayList<>();

        for (Entity entity : entities) {
            if (entity instanceof Ground) {
                int startX = entity.getX() - 13;
                int startY = entity.getY() - 13;
                int groundWidth = entity.getWidth();
                for (int i = 0; i < groundWidth / 60; i++) {
                    coinsToAdd.add(new Coin(startX, startY));
                    startX += 60;
                }
            }
        }

        entities.addAll(coinsToAdd);

        // 4 ground object for sides
        entities.add(new Ground(Color.BLACK, 0, 0, 15, panelWidth));
        entities.add(new Ground(Color.BLACK, 0, panelHeight - 10, 15, panelWidth));
        entities.add(new Ground(Color.BLACK, 0, 0, panelHeight, 15));
        entities.add(new Ground(Color.BLACK, panelWidth - 10, 0, panelHeight, 15));

        entities.add(new Ground(Color.red, 160, 150, 15, panelWidth - 300));

        m1 = new Monster(Color.DARK_GRAY, 300, 200, 40, 40, 3);

        entities.add(m1);

        entities.add(p1);

    }

    @Override
    public void run() {
        while (game != null) {
            update();
            repaint();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        if (game == null) {
            game = new Thread(this);
            game.start();
        }
    }

    public void stop() {
        if (game != null) {
            game.interrupt();
            game = null;
        }
    }

    public void update() {
        p1.applyGravity(entities);

        m1.applyGravity(entities);
        m1.move(entities);

        if (keyPressed[KeyEvent.VK_W]) {
            p1.move(0, -1 * p1.getSpeedY(), entities);
        }
        if (keyPressed[KeyEvent.VK_A]) {
            p1.move(-1 * p1.getSpeedX(), 0, entities);
            p1.setFacingRight(false);
        }
        if (keyPressed[KeyEvent.VK_S]) {
            p1.move(0, p1.getSpeedY(), entities);
        }
        if (keyPressed[KeyEvent.VK_D]) {
            p1.move(p1.getSpeedX(), 0, entities);
            p1.setFacingRight(true);
        }
        if (keyPressed[KeyEvent.VK_1]) {
            p1.switchChar(1);
        }
        if (keyPressed[KeyEvent.VK_2]) {
            p1.switchChar(2);
        }
        if (keyPressed[KeyEvent.VK_3]) {
            p1.switchChar(3);
        }
        if (keyPressed[KeyEvent.VK_SPACE]) {
            if (!p1.getColor().equals(Color.magenta))
                p1.shoot(p1.isFacingRight());

        }

        for (Projectile projectile : p1.getProjectiles()) {
            projectile.move(entities);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Entity entity : entities) {
            g.setColor(entity.getColor());
            g.fillRect(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
        }
        Iterator<Projectile> iterator = p1.getProjectiles().iterator();
        while (iterator.hasNext()) {
            Projectile projectile = iterator.next();
            g.setColor(projectile.getColor());
            g.fillRect(projectile.getX(), projectile.getY(), projectile.getWidth(), projectile.getHeight());

            for (Entity entity : entities) {
                if (entity instanceof Monster && projectile.intersects(entity)) {
                    // when projectile hits a monster
                    Monster monster = ((Monster) entity);
                    iterator.remove();
                    monster.setHealth(monster.getHealth() - 1);
                    if (monster.getHealth() < 1) {
                        entities.remove(entity);
                    }
                    break;
                }
            }
            // Remove the projectile
            if (projectile.getX() > panelWidth || projectile.getX() < 0) {
                iterator.remove();
            }
        }

        // Draw score
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Score: " + p1.getScore(), 20, 30);

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < 256) {
            keyPressed[keyCode] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < 256) {
            keyPressed[keyCode] = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
