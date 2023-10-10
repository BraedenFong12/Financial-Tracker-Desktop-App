package ui;


import javax.swing.*;
import java.awt.*;


public class SplashScreen {
    public static final String DOG_IMG_PATH = "./data/splash.GIF";

    public SplashScreen() {
        JFrame splashFrame = new JFrame("Splash Screen");
        splashFrame.setUndecorated(true);
        splashFrame.setSize(400, 300);
        splashFrame.setLocationRelativeTo(null);

        ImageIcon splashIcon = new ImageIcon(getClass().getResource("splash.GIF"));
        JLabel splashLabel = new JLabel(splashIcon);

        splashFrame.add(splashLabel);
        splashFrame.setVisible(true);

        // Close the splash screen after a short duration
        Timer timer = new Timer(3000, e -> {
            splashFrame.dispose(); // Close the splash frame
            SwingUtilities.invokeLater(() -> new GUI()); // Launch the main app window
        });
        timer.setRepeats(false);
        timer.start();
    }
}
