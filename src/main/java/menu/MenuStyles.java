package menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

final class MenuStyles {
    static final String TITLE_FONT = "Segoe UI Black";
    static final String HEADING_FONT = "Impact";
    static final String CONTROL_FONT = "Bahnschrift";

    static final Color PRIMARY_TEXT = new Color(0xBBBBBB);
    static final Color SECONDARY_TEXT = Color.WHITE;

    private MenuStyles() {
    }

    static JPanel verticalPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }

    static JLabel label(
            String text,
            int size,
            int style,
            Color colour) {
        return label(text, Font.SANS_SERIF, size, style, colour);
    }

    static JLabel label(
            String text,
            String fontName,
            int size,
            int style,
            Color colour) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(fontName, style, size));
        label.setForeground(colour);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    static JButton button(String text, boolean bold) {
        JButton button = new JButton(text);
        button.setFont(new Font(
                CONTROL_FONT,
                bold ? Font.BOLD : Font.PLAIN, 20));
        button.setFocusable(false);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        Dimension size = new Dimension(280, 48);
        button.setPreferredSize(size);
        button.setMaximumSize(size);

        return button;
    }

    static void gap(JPanel panel, int height) {
        panel.add(Box.createVerticalStrut(height));
    }
}
