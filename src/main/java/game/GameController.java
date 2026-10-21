package game;

import arenas.BattleArena;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameController implements KeyListener {
    private final float GRID;
    private final GameView view;
    private final BattleArena arena;
    private int key;
    private boolean debugModeOn = false;
    private boolean playerOutlineOn = false;
    private boolean gridOn = false;

    public GameController(GameView v, BattleArena w, float grid) {
        view = v;
        arena = w;
        GRID = grid;
        System.out.println("[0] Debug mode: OFF");
        System.out.println("    [T] Toggle player outlines");
        System.out.println("    [G] Toggle grid");
        System.out.println("    [H] Run debug viewer");
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        key = e.getKeyCode();
        if (key == KeyEvent.VK_0) {
            debugModeOn = !debugModeOn;
            System.out.println("[0] Debug mode: " + (debugModeOn ? "ON" : "OFF"));
        }
        if (debugModeOn) {
            if (key == KeyEvent.VK_T) togglePlayerOutlines();
            if (key == KeyEvent.VK_G) toggleGrid();
            if (key == KeyEvent.VK_H) view.runDebugViewer();
        }
        if (!arena.isEnded())
            if (key == KeyEvent.VK_ESCAPE) arena.togglePause(arena.isEnded());
        if (arena.isPaused()) {
            if (arena.isEnded()) if (key == KeyEvent.VK_ESCAPE) System.out.println("Feature unavailable.");
            if (key == KeyEvent.VK_ENTER) arena.exit();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        key = e.getKeyCode();
    }

    private void togglePlayerOutlines() {
        playerOutlineOn = !playerOutlineOn;
        arena.getPlayer1().setAlwaysOutline(playerOutlineOn);
        arena.getPlayer2().setAlwaysOutline(playerOutlineOn);
    }

    private void toggleGrid() {
        gridOn = !gridOn;
        view.setGridResolution(gridOn ? GRID : 0);
    }
}
