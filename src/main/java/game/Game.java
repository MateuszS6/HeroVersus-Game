package game;

import arenas.BattleArena;
import city.cs.engine.SoundClip;
import menu.ControlsPage;
import menu.PlayScreen;
import menu.TitleScreen;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.io.IOException;

/** Main game entry point. */
public final class Game {
    public static final String MENU_MUSIC = "tension_mode.mp3";
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private final JFrame window;
    private JPanel screen;
    private SoundClip soundtrack;
    private BattleArena arena;

    /** Game constructor.
     * <p>
     * Creates a new game with the title screen and soundtrack.
     */
    public Game() {
        // TODO: 30/04/2023 Main menu book-style as imagined

        window = new JFrame("HeroVersus");

        screen = new TitleScreen(this).getMainPanel();
        // Window would have to be [816x639] to ensure the correct GameView size [800x600]
        screen.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        window.add(screen); // Add the component to the window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationByPlatform(true);
        window.setResizable(false);
        window.setVisible(true);
        window.pack(); // Resize the window to fit the preferred size
        setSoundtrack(MENU_MUSIC); // Start playing the background music
    }

    /** Run the game. */
    public static void main(String[] args) {
        new Game();
    }

    public void setSoundtrack(String fileName) {
        try {
            if (soundtrack != null) soundtrack.stop();
            soundtrack = new SoundClip("assets/sound/music/" + fileName);
            soundtrack.setVolume(0.3);
            soundtrack.loop();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void toggleSoundtrack() {
        if (arena.isPaused()) soundtrack.pause();
        else soundtrack.resume();
    }

    public void playSound(String fileName) {
        try {
            new SoundClip("assets/sound/" + fileName).play();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchToMenu(Panels p) {
        window.remove(screen);
        screen = switch (p) {
            case TITLE_SCREEN -> new TitleScreen(this).getMainPanel();
            case PLAY_SCREEN -> new PlayScreen(this).getMainPanel();
            case CONTROLS_PAGE -> new ControlsPage(this).getMainPanel();
        };
        screen.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        window.add(screen);
        window.pack();
        setSoundtrack(MENU_MUSIC);
    }

    public void goToArena(BattleArena w) {
        arena = w; // Game world
        viewArena();
        arena.start();
    }

    private void viewArena() {
        GameView view = new GameView(arena, WIDTH, HEIGHT); // Game view
        window.remove(screen);
        screen = view;
        window.add(view);
        window.pack();
    }

/*
    public void restartArena(BattleArena w) {
        arena = w;
        view = new GameView(arena, WIDTH, HEIGHT);
        switchPanel(view);
        view.repaint();
        arena.start();
    }
*/
}