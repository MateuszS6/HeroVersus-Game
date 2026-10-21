package menu;

import arenas.*;
import game.Game;
import game.Panels;

import javax.swing.*;
import java.awt.*;

public class PlayScreen {
    private final Game game;
    private JPanel mainPanel;
    private JComboBox<String> comboBox;
    private BattleArena selection;
    private JButton fightButton;
    private JButton backButton;

    public PlayScreen(Game g) {
        game = g;
        comboBox.addActionListener(e -> {
            selection = switch (comboBox.getSelectedIndex()) {
                case 1 -> new RoyalArena(game);
                case 2 -> new WildForest(game);
                case 3 -> new TheVoid(game);
                case 4 -> new OldHangar(game);
                default -> null;
            };
        });
        fightButton.addActionListener(e -> {
            if (selection != null) game.goToArena(selection);
        });
        backButton.addActionListener(e -> game.switchToMenu(Panels.TITLE_SCREEN));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void createUIComponents() {
        mainPanel = new MenuBackground();
    }
}
