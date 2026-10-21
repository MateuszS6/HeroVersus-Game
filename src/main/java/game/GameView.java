package game;

import arenas.BattleArena;
import city.cs.engine.DebugViewer;
import city.cs.engine.UserView;
import player.PlayerController;

import java.awt.*;

/** Game view class. */
public class GameView extends UserView {
    public static final float GRID = 1;
    private final BattleArena arena;
    private DebugViewer debugViewer;

    /** View constructor.
     * <p>
     * Creates a new view to look onto the BattleArena.
     * @param w the arena
     * @param width the width of the view in pixels
     * @param height the height of the view in pixels
     */
    public GameView(BattleArena w, int width, int height) {
        super(w, width, height);
        arena = w;

        addKeyListener(new PlayerController(arena.getPlayer1())); // Player 1 controller
        addKeyListener(new PlayerController(arena.getPlayer2())); // Player 2 controller

        addMouseListener(new GiveFocus(this)); // View focus
        addKeyListener(new GameController(this, arena, 2)); // Debug controller
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        if (arena.getBgImage() == null) {
            g.setColor(arena.getBgColour());
            g.fillRect(0, 0, getWidth(), getHeight());
        } else g.drawImage(arena.getBgImage(), 0, 0, this);
    }

    @Override
    protected void paintForeground(Graphics2D g) {
        arena.drawHUD(this, g);
        if (arena.isPaused() || arena.isEnded()) drawPauseMenu(g);
    }



    public void runDebugViewer() {
        if (debugViewer == null) debugViewer = new DebugViewer(arena, getWidth(), getHeight());
    }

    public void drawPauseMenu(Graphics2D g) {
        g.setColor(new Color(0, 0, 0, 128));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(new Color(0xFFFFFF));
        int d = 20;
        g.drawRoundRect(d, d, getWidth() - d * 2, getHeight() - d * 2, 10, 10);

        int centre = getWidth() / 2;
        int middle = getHeight() / 2;
        if (arena.isEnded()) {
            g.setFont(new Font("Eras Bold ITC", Font.PLAIN, 72));
            drawText(g, "Player " + arena.getWinner(), centre, middle - 30);
            g.setFont(new Font("Impact", Font.PLAIN, 36));
            g.setColor(new Color(0xBBBBBB));
            drawText(g, "ARENA CHAMPION", centre, middle + 20);
            g.setColor(new Color(0xFFFFFF));
            g.setFont(new Font("Bahnschrift", Font.BOLD, 20));
            drawText(g, "[ESC] Rematch", centre, middle + 60);
            drawText(g, "[ENTER] Exit", centre, middle + 100);
        } else {
            g.setFont(new Font("Impact", Font.PLAIN, 72));
            drawText(g, "Game Paused", centre, middle);
            g.setFont(new Font("Bahnschrift", Font.BOLD, 20));
            drawText(g, "[ESC] Resume", centre, middle + 20);
            drawText(g, "[ENTER] Exit", centre, middle + 60);
        }
    }

    public void drawText(Graphics2D g, String text, float x, float y) {
        int fCentre = g.getFontMetrics().stringWidth(text) / 2;
        int fMiddle = g.getFontMetrics().getHeight() / 2;
        g.drawString(text, x - fCentre, y - fMiddle);
    }
}