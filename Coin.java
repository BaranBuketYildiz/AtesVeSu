import java.awt.*;
import java.util.Random;
public class Coin extends Entity {
        public Coin(int x, int y) {
                super(randomColor(), x, y, 10, 10);
        }

        public static Color randomColor() {

                Color[] colors = {
                                new Color(255, 176, 0),
                                new Color(248, 222, 34),
                                Color.YELLOW,
                                Color.ORANGE,
                                Color.CYAN
                };      

                Random random = new Random();

                int randomIndex = random.nextInt(colors.length);

                return colors[randomIndex];
        }
}
