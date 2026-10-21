package menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import game.Game;
import game.Panels;

public class ControlsPage extends MenuBackground {
    public ControlsPage(Game game) {
        setLayout(new GridBagLayout());

        JPanel content = MenuStyles.verticalPanel();

        content.add(MenuStyles.label(
                "Controls",
                42,
                Font.BOLD,
                MenuStyles.SECONDARY_TEXT));

        MenuStyles.gap(content, 30);

        JPanel controls = new JPanel(new GridLayout(5, 3, 12, 10));
        controls.setOpaque(false);
        controls.setAlignmentX(CENTER_ALIGNMENT);

        controls.add(textCell("Action"));
        controls.add(textCell("Player 1"));
        controls.add(textCell("Player 2"));

        addControlRow(controls, "Jump", "W", "Up");
        addControlRow(controls, "Left", "A", "Left");
        addControlRow(controls, "Right", "D", "Right");
        addControlRow(controls, "Attack", "R", ".");

        Dimension tableSize = new Dimension(520, 240);
        controls.setPreferredSize(tableSize);
        controls.setMaximumSize(tableSize);

        content.add(controls);
        MenuStyles.gap(content, 20);

        content.add(MenuStyles.label(
                "Controls are currently fixed.",
                14,
                Font.PLAIN,
                MenuStyles.PRIMARY_TEXT));

        MenuStyles.gap(content, 24);

        JButton backButton = MenuStyles.button("Back", false);
        backButton.addActionListener(_ -> game.switchToMenu(Panels.TITLE_SCREEN));

        content.add(backButton);
        add(content);
    }

    private static void addControlRow(
            JPanel panel,
            String action,
            String playerOne,
            String playerTwo) {
        panel.add(textCell(action));
        panel.add(keyCell(playerOne));
        panel.add(keyCell(playerTwo));
    }

    private static JLabel textCell(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
        label.setForeground(Color.WHITE);
        return label;
    }

    private static JLabel keyCell(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        label.setForeground(new Color(0x222222));
        label.setBackground(new Color(0xEEEEEE));
        label.setOpaque(true);
        label.setBorder(BorderFactory.createLineBorder(new Color(0x888888)));
        return label;
    }
}