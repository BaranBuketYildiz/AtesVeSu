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
    private long startTime;
    private long trackerTime = 0;
    private String formattedTime;

    private MonsterSpawner monsterSpawner;

    Thread game;

    public GamePanel() {
        startTime = System.currentTimeMillis();
        p1 = new Player(Color.RED, 15, panelHeight - 30, 20, 20);
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        addKeyListener(this);
        setFocusable(true);
        setBackground(Color.WHITE);

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

        Monster m1 = new Monster(Color.BLUE, 300, 200, 40, 40, 15, 3);
        Monster m2 = new Monster(Color.RED, 300, 600, 40, 40, 15, 3);
        Monster m3 = new Monster(Color.YELLOW, 300, 700, 40, 40, 15, 3);

        entities.add(m1);
        entities.add(m2);
        entities.add(m3);
        entities.add(p1);

        monsterSpawner = new MonsterSpawner(new Color(144, 12, 63), (panelWidth/2)-75, 15, 135, 150);
        entities.add(monsterSpawner);
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

    private void endGame() {
        JOptionPane.showMessageDialog(this, "Game Over!\nScore: " + p1.getScore() + "\nTime: " + formattedTime,
                "Game Over", JOptionPane.INFORMATION_MESSAGE);

        System.exit(0);
    }

    private String formatTime(long milliseconds) {
        long seconds = (milliseconds / 1000) % 60;
        long minutes = (milliseconds / (1000 * 60)) % 60;
        String timeString = String.format("%02d:%02d", minutes, seconds);
        return timeString;
    }

    public void update() {
        long currentTime = System.currentTimeMillis();


        formattedTime = formatTime(currentTime - startTime); 

        long timeSinceLastSpawn = currentTime - trackerTime;

        // Every 5 seconds, spawn a monster with some randomization
        if (timeSinceLastSpawn >= 5000) { // If it's been 5 seconds or more
            trackerTime = currentTime; // Update trackerTime to current time
            entities.add(monsterSpawner.spawnMonster()); // Add a new monster to the game
        }


        


        

        if (p1.getHealth() < 1) {
            stop();
            endGame();
        }
        for (Entity entity : entities) {
            if (entity instanceof Monster) {
                Monster m = ((Monster) entity);
                m.move(entities);
                m.applyGravity(4, entities);

            }

        }
        p1.applyGravity(entities);

        // m1.applyGravity(entities);
        // m1.move(entities);

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
        for (Entity entity : entities) {
            g.setColor(entity.getColor());
            g.fillRect(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight());
            // for monster health text
            if (entity instanceof Monster) {
                Monster monster = ((Monster) entity);
                int textX = monster.getX() + monster.getWidth() / 2; // Center the text horizontally
                int textY = monster.getY() + monster.getHeight() / 2; // Center the text vertically

                // Set font and color for text
                g.setColor(Color.BLACK); // White text should be visible against most backgrounds
                g.setFont(new Font("Arial", Font.BOLD, 12)); // Choose a visible font and size

                // Draw the monster's health as text
                g.drawString(String.valueOf(monster.getMonsterHealth()), textX - 5, textY + 5);
            }
        }
        Iterator<Projectile> iterator = p1.getProjectiles().iterator();
        while (iterator.hasNext()) {
            Projectile projectile = iterator.next();
            g.setColor(projectile.getColor());
            g.fillRect(projectile.getX(), projectile.getY(), projectile.getWidth(), projectile.getHeight());

            Iterator<Entity> iterator2 = entities.iterator();
            while (iterator2.hasNext()) {
                Entity entity = iterator2.next();
                if (entity instanceof Monster && projectile.intersects(entity)) {
                    iterator.remove();

                    Monster monster = (Monster) entity;

                    if (!projectile.getColor().equals(monster.getColor())) {
                        monster.setMonsterHealth(monster.getMonsterHealth() - 1);
                        System.out.println("After: " + monster.getMonsterHealth());
                        if (monster.getMonsterHealth() < 1) {
                            iterator2.remove();
                            p1.setScore(p1.getScore() + 100);
                        }
                    }
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
