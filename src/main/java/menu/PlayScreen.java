package menu;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import arenas.BattleArena;
import arenas.OldHangar;
import arenas.RoyalArena;
import arenas.TheVoid;
import arenas.WildForest;
import game.Game;
import game.Panels;

public class PlayScreen extends MenuBackground {
    private static final String[] ARENAS = {
            "Select an arena...",
            "ROYAL ARENA",
            "WILD FOREST",
            "THE VOID",
            "OLD HANGAR"
    };

    public PlayScreen(Game game) {
        setLayout(new GridBagLayout());

        JPanel content = MenuStyles.verticalPanel();

        content.add(MenuStyles.label(
                "Select an arena:",
                MenuStyles.HEADING_FONT,
                42,
                Font.BOLD,
                MenuStyles.PRIMARY_TEXT));

        MenuStyles.gap(content, 30);

        JComboBox<String> arenaChooser = new JComboBox<>(ARENAS);
        arenaChooser.setFont(new Font(MenuStyles.CONTROL_FONT, Font.PLAIN, 16));
        arenaChooser.setAlignmentX(CENTER_ALIGNMENT);

        Dimension chooserSize = new Dimension(360, 44);
        arenaChooser.setPreferredSize(chooserSize);
        arenaChooser.setMaximumSize(chooserSize);

        JButton fightButton = MenuStyles.button("Fight!", true);
        JButton backButton = MenuStyles.button("Back", false);

        fightButton.setEnabled(false);

        arenaChooser.addActionListener(_ -> fightButton.setEnabled(
                arenaChooser.getSelectedIndex() > 0));

        fightButton.addActionListener(_ -> {
            BattleArena arena = createArena(game, arenaChooser.getSelectedIndex());

            if (arena != null)
                game.goToArena(arena);
        });

        backButton.addActionListener(_ -> game.switchToMenu(Panels.TITLE_SCREEN));

        content.add(arenaChooser);
        MenuStyles.gap(content, 18);
        content.add(fightButton);
        MenuStyles.gap(content, 12);
        content.add(backButton);

        add(content);
    }

    private static BattleArena createArena(Game game, int selection) {
        return switch (selection) {
            case 1 -> new RoyalArena(game);
            case 2 -> new WildForest(game);
            case 3 -> new TheVoid(game);
            case 4 -> new OldHangar(game);
            default -> null;
        };
    }
}
