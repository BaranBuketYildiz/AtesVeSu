import java.awt.Color;
import java.util.List;

public class Monster extends Player {

        private int health = 100;
        private int monsterSpeedX;
        
        
        public Monster(Color color, int x, int y, int height, int width,  int monsterSpeedX) {
                super(color, x, y, height, width);
                this.health = health;
                this.monsterSpeedX = monsterSpeedX;
        }

        public void move(List<Entity> entities) {
                int newX = this.getX() + this.getMonsterSpeedX();
                this.setX(newX);
                for (Entity entity : entities) {
                        if (entity instanceof Ground && this.intersects(entity)) {
                                if ( this.getMonsterSpeedX() > 0) {
                                        setMonsterSpeedX(-1 * getMonsterSpeedX());
                                        newX = entity.getX() - this.getWidth();
                                } else if ( this.getMonsterSpeedX() < 0) {
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

        public int getHealth() {
                return health;
        }

        public void setHealth(int health) {
                this.health = health;
        }

        public int getMonsterSpeedX() {
                return monsterSpeedX;
        }

        public void setMonsterSpeedX(int monsterSpeedX) {
                this.monsterSpeedX = monsterSpeedX;
        }
}
