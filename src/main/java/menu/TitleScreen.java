package menu;

import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import game.Game;
import game.Panels;

public class TitleScreen extends MenuBackground {
    public TitleScreen(Game game) {
        setLayout(new GridBagLayout());

        JPanel content = MenuStyles.verticalPanel();

        content.add(MenuStyles.label(
                "HeroVersus",
                "Segoe UI Black",
                72,
                Font.BOLD | Font.ITALIC,
                MenuStyles.PRIMARY_TEXT));

        content.add(MenuStyles.label(
                "BATTLE ARENA",
                "Impact",
                42,
                Font.BOLD,
                MenuStyles.SECONDARY_TEXT));

        MenuStyles.gap(content, 55);

        JButton playButton = MenuStyles.button("Play", true);
        JButton controlsButton = MenuStyles.button("Controls", false);
        JButton quitButton = MenuStyles.button("Quit", false);

        playButton.addActionListener(_ -> game.switchToMenu(Panels.PLAY_SCREEN));
        controlsButton.addActionListener(_ -> game.switchToMenu(Panels.CONTROLS_PAGE));
        quitButton.addActionListener(_ -> System.exit(0));

        content.add(playButton);
        MenuStyles.gap(content, 12);
        content.add(controlsButton);
        MenuStyles.gap(content, 12);
        content.add(quitButton);

        add(content);
    }
}
