import java.awt.Color;
import java.util.List;

public class Monster extends Entity {

        private int monsterHealth;
        private int monsterSpeedX;

        public Monster(Color color, int x, int y, int height, int width, int monsterHealth, int monsterSpeedX) {
                super(color, x, y, height, width);
                this.monsterHealth = monsterHealth;
                this.monsterSpeedX = monsterSpeedX;
        }

        public void applyGravity(int gravity, List<Entity> entities) {
                boolean onGround = false;
                for (Entity entity : entities) {
                        if (entity instanceof Ground && this.intersects(entity)) {
                                onGround = true;
                                break;
                        }
                }
                if (!onGround) {
                        fall(gravity, entities);
                }
        }

        public void fall(int gravity, List<Entity> entities) {
                int newY = this.getY() + gravity;
                this.setY(newY);
                for (Entity entity : entities) {

                        if (entity instanceof Ground && this.intersects(entity)) {
                                setY(gravity);
                                newY = entity.getY() - this.getWidth();
                                break;
                        }
                }
                this.setY(newY);
        }

        public void move(List<Entity> entities) {
                int newX = this.getX() + this.getMonsterSpeedX();
                this.setX(newX);
                for (Entity entity : entities) {

                        if (entity instanceof Ground && this.intersects(entity)) {
                                if (this.getMonsterSpeedX() > 0) {
                                        setMonsterSpeedX(-1 * getMonsterSpeedX());
                                        newX = entity.getX() - this.getWidth();
                                } else if (this.getMonsterSpeedX() < 0) {
                                        setMonsterSpeedX(-1 * getMonsterSpeedX());
                                        newX = entity.getX() + entity.getWidth();
                                }
                                // if (y > 0) {
                                // newY = entity.getY() - this.getHeight();
                                // } else if (y < 0) {
                                // newY = entity.getY() + entity.getHeight();
                                // }
                                break;
                        }
                }
                this.setX(newX);
        }

        public int getMonsterSpeedX() {
                return monsterSpeedX;
        }

        public void setMonsterSpeedX(int monsterSpeedX) {
                System.out.println("call");
                this.monsterSpeedX = monsterSpeedX;
        }

        public int getMonsterHealth() {
                return monsterHealth;
        }

        public void setMonsterHealth(int monsterHealth) {
                this.monsterHealth = monsterHealth;
        }
}
