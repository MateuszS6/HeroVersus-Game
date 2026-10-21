package menu;

import javax.swing.*;
import java.awt.*;

public class MenuBackground extends JPanel {
    private final Image background = new ImageIcon("assets/background/rough_rhombus_surface.jpg").getImage();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
    }
}
