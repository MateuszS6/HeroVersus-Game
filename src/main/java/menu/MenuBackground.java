package menu;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import game.Resources;

public class MenuBackground extends JPanel {
    private final Image background = new ImageIcon(
            Resources.url("assets/background/rough_rhombus_surface.jpg")).getImage();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
    }
}
