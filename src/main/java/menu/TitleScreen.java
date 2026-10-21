package menu;

import game.Game;
import game.Panels;

import javax.swing.*;
import java.awt.*;

public class TitleScreen {
    private final Game game;
    private JPanel mainPanel;
    private JButton playButton;
    private JButton controlsButton;
    private JButton quitButton;

    public TitleScreen(Game g) {
        game = g;
        playButton.addActionListener(_ -> game.switchToMenu(Panels.PLAY_SCREEN));
        controlsButton.addActionListener(_ -> game.switchToMenu(Panels.CONTROLS_PAGE));
        quitButton.addActionListener(e -> System.exit(0));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void createUIComponents() {
        mainPanel = new MenuBackground();
    }
}
