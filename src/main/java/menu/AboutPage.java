package menu;

import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import game.Game;
import game.Panels;

public final class AboutPage extends MenuBackground {
	public AboutPage(Game game) {
		setLayout(new GridBagLayout());

		JPanel content = MenuStyles.verticalPanel();

		content.add(MenuStyles.label(
				"ABOUT",
				MenuStyles.HEADING_FONT,
				48,
				Font.PLAIN,
				MenuStyles.SECONDARY_TEXT));

		MenuStyles.gap(content, 60);

		content.add(MenuStyles.label(
				"HeroVersus - Battle Arena",
				24,
				Font.BOLD,
				MenuStyles.PRIMARY_TEXT));

		MenuStyles.gap(content, 16);

		content.add(MenuStyles.label(
				"A two-player fighting game.",
				17,
				Font.PLAIN,
				MenuStyles.SECONDARY_TEXT));

		content.add(MenuStyles.label(
				"Originally created as a university project.",
				17,
				Font.PLAIN,
				MenuStyles.SECONDARY_TEXT));

		content.add(MenuStyles.label(
				"Modernised with Gradle and plain Swing.",
				17,
				Font.PLAIN,
				MenuStyles.SECONDARY_TEXT));

		MenuStyles.gap(content, 60);

		content.add(MenuStyles.label(
				"Version 999999999.0",
				15,
				Font.BOLD,
				MenuStyles.PRIMARY_TEXT));

		MenuStyles.gap(content, 35);

		JButton backButton = MenuStyles.button("Back", false);
		backButton.addActionListener(_ -> {
			try {
				game.switchToMenu(Panels.TITLE_SCREEN);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		content.add(backButton);
		add(content);
	}
}
