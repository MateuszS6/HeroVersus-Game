package menu;

import game.Game;
import game.Panels;

import javax.swing.*;
import java.awt.*;

public class ControlsPage {
    Game game;
    private JPanel mainPanel;
    private JButton backButton;
    private JButton jump1;
    private JButton jump2;
    private JButton left1;
    private JButton left2;
    private JButton right1;
    private JButton right2;
    private JButton attack1;
    private JButton attack2;
    private JPanel controlsPanel;

    public ControlsPage(Game g) {
        game = g;
        backButton.addActionListener(e -> game.switchToMenu(Panels.TITLE_SCREEN));
        /*KeyAdapter listener = new KeyAdapter() {
            int key;
            @Override
            public void keyPressed(KeyEvent e) {
                key = e.getKeyCode();
                System.out.println(KeyEvent.getKeyText(key));
            }
        };
        left1.addKeyListener(listener);
        jump1.addKeyListener(listener);
        right1.addKeyListener(listener);
        attack1.addKeyListener(listener);
        left2.addKeyListener(listener);
        jump2.addKeyListener(listener);
        right2.addKeyListener(listener);
        attack2.addKeyListener(listener);*/
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void createUIComponents() {
        mainPanel = new MenuBackground();
    }
}
