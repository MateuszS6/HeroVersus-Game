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
                MenuStyles.TITLE_FONT,
                72,
                Font.BOLD | Font.ITALIC,
                MenuStyles.PRIMARY_TEXT));

        content.add(MenuStyles.label(
                "BATTLE ARENA",
                MenuStyles.HEADING_FONT,
                42,
                Font.BOLD,
                MenuStyles.SECONDARY_TEXT));

        MenuStyles.gap(content, 50);

        JButton playButton = MenuStyles.button("Play", true);
        JButton controlsButton = MenuStyles.button("Controls", false);
        JButton aboutButton = MenuStyles.button("About", false);
        JButton quitButton = MenuStyles.button("Quit", false);

        playButton.addActionListener(_ -> {
            try {
                game.switchToMenu(Panels.PLAY_SCREEN);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        controlsButton.addActionListener(_ -> {
            try {
                game.switchToMenu(Panels.CONTROLS_PAGE);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        aboutButton.addActionListener(_ -> {
            try {
                game.switchToMenu(Panels.ABOUT_PAGE);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        quitButton.addActionListener(_ -> System.exit(0));

        content.add(playButton);
        MenuStyles.gap(content, 12);
        content.add(controlsButton);
        MenuStyles.gap(content, 12);
        content.add(aboutButton);
        MenuStyles.gap(content, 12);
        content.add(quitButton);

        add(content);
    }
}
