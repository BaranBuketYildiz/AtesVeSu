import java.awt.*;
import javax.swing.*;

public class GamePanel extends JFrame{
     





    public GamePanel(){
        setSize(900, 1000);
        setTitle("ATES VE SU");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

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