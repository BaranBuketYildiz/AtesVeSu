import java.awt.Color;
import java.util.Random;

public class MonsterSpawner extends Entity {

        public MonsterSpawner(Color color, int x, int y, int height, int width) {
                super(color, x, y, height, width);
                // TODO Auto-generated constructor stub
        }

        final static int MAX_HEALTH = 50;
        final static int MIN_HEALTH = 15;

        public static Color randomColor() {

                Color[] colors = {
                                Color.YELLOW,
                                Color.RED,
                                Color.MAGENTA
                };

                Random random = new Random();

                int randomIndex = random.nextInt(colors.length);

                return colors[randomIndex];
        }

        public Monster spawnMonster() {
                Color monsterColor = randomColor();
                int monsterHealth = randomHealth();
                Monster monster = new Monster(monsterColor, this.getX(), this.getY(), 20, 20,
                                monsterHealth, 3);

                // random direction
                Random random = new Random();
                int r = random.nextInt(2);

                if (r == 1) {
                        monster.setMonsterSpeedX(monster.getMonsterSpeedX() * -1);
                }

                return monster;
        }

        public static int randomHealth() {
                Random random = new Random();
                return random.nextInt(MAX_HEALTH - MIN_HEALTH + 1) + MIN_HEALTH;
        }

}
