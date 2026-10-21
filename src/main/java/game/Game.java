package game;

import java.awt.Dimension;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import arenas.BattleArena;
import city.cs.engine.SoundClip;
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
    private SoundClip soundtrack;
    private String soundtrackFileName;
    private BattleArena arena;

    /**
     * Game constructor.
     * <p>
     * Creates a new game with the title screen and soundtrack.
     */
    public Game() {
        // TODO: 30/04/2023 Main menu book-style as imagined

        window = new JFrame("HeroVersus");

        screen = new TitleScreen(this);
        // Window would have to be [816x639] to ensure the correct GameView size
        // [800x600]
        screen.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        window.setContentPane(screen); // Add the component to the window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        if (soundtrack != null && fileName.equals(soundtrackFileName))
            return;

        try {
            SoundClip nextSoundtrack = new SoundClip(
                    Resources.url("assets/sound/music/" + fileName));

            nextSoundtrack.setVolume(0.3);

            if (soundtrack != null)
                soundtrack.stop();

            soundtrack = nextSoundtrack;
            soundtrackFileName = fileName;
            soundtrack.loop();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void toggleSoundtrack() {
        if (arena.isPaused())
            soundtrack.pause();
        else
            soundtrack.resume();
    }

    public void playSound(String fileName) {
        try {
            new SoundClip(Resources.url("assets/sound/" + fileName)).play();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchToMenu(Panels panel) {
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

    public void goToArena(BattleArena a) {
        arena = a; // Game world
        viewArena();
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

    // public void restartArena(BattleArena w) {
    //     arena = w;
    //     view = new GameView(arena, WIDTH, HEIGHT);
    //     switchPanel(view);
    //     view.repaint();
    //     arena.start();
    // }
}