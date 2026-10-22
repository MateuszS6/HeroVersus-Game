package game;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import arenas.BattleArena;
import audio.MusicPlayer;
import menu.AboutPage;
import menu.ControlsPage;
import menu.PlayScreen;
import menu.TitleScreen;

/** Main game entry point. */
public final class Game {
	public static final String MENU_MUSIC = "tension_mode.mp3";
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	private final JFrame window;
	private JPanel screen;
	private final MusicPlayer music = new MusicPlayer();
	private BattleArena arena;

	/**
	 * Game constructor.
	 * <p>
	 * Creates a new game with the title screen and soundtrack.
	 */
	public Game() {
		window = new JFrame("HeroVersus");

		screen = new TitleScreen(this);
		// Window would have to be [816x639] to ensure the correct GameView size [800x600]
		screen.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		window.setContentPane(screen); // Add the component to the window

		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		window.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				try {
					close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		window.setResizable(false);
		window.pack(); // Resize the window to fit the preferred size
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		setSoundtrack(MENU_MUSIC); // Start playing the background music
	}

	/** Run the game. */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(Game::new);
	}

	public void setSoundtrack(String fileName) {
		music.play(fileName);
		music.setPaused(false);
	}

	public void toggleSoundtrack() {
		if (arena != null)
			music.setPaused(arena.isPaused());
	}

	public void switchToMenu(Panels panel) throws Exception {
		closeArena();

		screen = switch (panel) {
			case TITLE_SCREEN -> new TitleScreen(this);
			case PLAY_SCREEN -> new PlayScreen(this);
			case CONTROLS_PAGE -> new ControlsPage(this);
			case ABOUT_PAGE -> new AboutPage(this);
		};

		screen.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		window.setContentPane(screen);
		window.pack();
		window.revalidate();
		window.repaint();
		setSoundtrack(MENU_MUSIC);
	}

	public void goToArena(BattleArena a) throws Exception {
		a.initialise();
		closeArena();

		arena = a; // Game world
		viewArena();
		setSoundtrack("crime_is_everywhere.mp3");
		arena.start();
	}

	private void viewArena() {
		GameView view = new GameView(arena, WIDTH, HEIGHT);
		screen = view;

		window.setContentPane(screen);
		window.pack();
		window.revalidate();
		window.repaint();

		SwingUtilities.invokeLater(view::requestFocusInWindow);
	}

	public void closeArena() throws Exception {
		if (screen instanceof GameView view)
			view.close();

		if (arena != null) {
			arena.close();
			arena = null;
		}
	}

	// public void restartArena(BattleArena w) {
	// arena = w;
	// view = new GameView(arena, WIDTH, HEIGHT);
	// switchPanel(view);
	// view.repaint();
	// arena.start();
	// }

	public void close() throws Exception {
		closeArena();
		music.close();
		window.dispose();
	}
}