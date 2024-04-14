
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.util.Iterator;

public class Player extends Entity {

        private int speedX = 3;
        private int speedY = 5;
        private boolean onGround = false;
        private int gravity = 4;
        private int score = 0;
        

        private boolean isFacingRight = true;

        private boolean jumping;

        public Player(Color color, int x, int y, int height, int width) {
                super(color, x, y, height, width);

        }

        private List<Projectile> projectiles = new ArrayList<>();
        private int projectileSpeed = 10;

        public void shoot(boolean isFacingRight) {
                int direction = isFacingRight ? 1 : -1;

                if (projectiles.size() < 5) {
                        Projectile projectile = new Projectile(this.getColor(), this.getX(), this.getY(), 5, 5,
                                        projectileSpeed * direction);
                        projectiles.add(projectile);
                }

        }

        public void switchChar(int charCode) {
                if (charCode == 1) {
                        setColor(Color.RED);
                        setSpeedY(5);

                } else if (charCode == 2) {
                        setColor(Color.BLUE);
                        setSpeedY(5);

                } else if (charCode == 3) {
                        setColor(Color.MAGENTA);
                        setSpeedY(8);
                }

        }

        public void applyGravity(List<Entity> entities) {
                boolean onGround = false;
                for (Entity entity : entities) {
                        if (entity instanceof Ground && this.intersects(entity)) {
                                onGround = true;
                                break;
                        }
                }
                if (!onGround) {
                        move(0, gravity, entities);
                }
        }

        public void move(int x, int y, List<Entity> entities) {
                int newX = this.getX() + x;
                int newY = this.getY() + y;
                this.setX(newX);
                this.setY(newY);

                Iterator<Entity> iterator = entities.iterator();
                while (iterator.hasNext()) {
                        Entity entity = iterator.next();
                        if (this.intersects(entity)) {
                                if (entity instanceof Ground) {
                                        if (x > 0) {
                                                newX = entity.getX() - this.getWidth();
                                        } else if (x < 0) {
                                                newX = entity.getX() + entity.getWidth();
                                        }
                                        if (y > 0) {
                                                newY = entity.getY() - this.getHeight();
                                        } else if (y < 0) {
                                                newY = entity.getY() + entity.getHeight();
                                        }
                                } else if (entity instanceof Coin && !(this instanceof Monster)
                                                && this.intersects(entity)) {
                                        iterator.remove();
                                        this.score++;
                                }
                        }
                }

                this.setX(newX);
                this.setY(newY);
        }

        public int getSpeedX() {
                return speedX;
        }

        public void setSpeedX(int speedX) {
                this.speedX = speedX;
        }

        public int getSpeedY() {
                return speedY;
        }

        public void setSpeedY(int speedY) {
                this.speedY = speedY;
        }

        public boolean isOnGround() {
                return onGround;
        }

        public void setOnGround(boolean onGround) {
                this.onGround = onGround;
        }

        public int getGravity() {
                return gravity;
        }

        public void setGravity(int gravity) {
                this.gravity = gravity;
        }

        public boolean isJumping() {
                return jumping;
        }

        public void setJumping(boolean jumping) {
                this.jumping = jumping;
        }

        public List<Projectile> getProjectiles() {
                return projectiles;
        }

        public void setProjectiles(List<Projectile> projectiles) {
                this.projectiles = projectiles;
        }

        public int getProjectileSpeed() {
                return projectileSpeed;
        }

        public void setProjectileSpeed(int projectileSpeed) {
                this.projectileSpeed = projectileSpeed;
        }

        public boolean isFacingRight() {
                return isFacingRight;
        }

        public void setFacingRight(boolean isFacingRight) {
                this.isFacingRight = isFacingRight;
        }

        public int getScore() {
                return score;
        }

        public void setScore(int score) {
                this.score = score;
        }

}