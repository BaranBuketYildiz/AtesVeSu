import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
    GamePanel panel;

    public MainFrame() {
        
        panel = new GamePanel();
        add(panel);
        panel.start();
        setTitle("ATES VE SU");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(Color.WHITE);

        panel.requestFocusInWindow();

        pack();

    }

    public static void main(String[] args) {
        System.out.println("baran");
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new MainFrame();
            }
        });

    }
}
