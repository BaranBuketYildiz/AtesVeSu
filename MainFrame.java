import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
        GamePanel panel;
        public MainFrame() {

                panel = new GamePanel();

                setTitle("ATES VE SU");
                setVisible(true);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setBackground(Color.WHITE);


                add(panel);

        }


    public static void main(String[] args) {
        System.out.println("baran");
        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                new GamePanel();    
            }
        });
        
    }
}
