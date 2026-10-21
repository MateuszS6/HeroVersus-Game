package arenas;

import city.cs.engine.World;
import game.Game;
import game.GameView;
import game.Panels;
import org.jbox2d.common.Vec2;
import player.Characters;
import player.Player;
import world.DeathZone;
import world.SideBorder;
import world.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;

public abstract class BattleArena extends World {
    // TODO: 24/04/2023
    //  1 life, best of 3, winning player chooses next arena to play in
    // TODO: 24/04/2023
    //  Add PRACTICE ARENA with grey tiles - unlimited respawns, advances to arenas on completion OR on SKIP button
    // TODO: 24/04/2023
    //  Desert arena, minimalistic/paper arena
    private final Game game;
    private final String title;
    private final String tilePath;
    private final SideBorder
            left,
            right;
    private final Player
            player1,
            player2;
    private int winner;
    private Color bgColour;
    private Image bgImage;
    private DeathZone deathZone;
    private boolean isPaused = false;
    private boolean isEnded = false;

    /**
     * World constructor.
     * <p>
     * Creates a new arena based on the calling subclass.
     * @param g the game containing the arena
     * @param title the name of the arena
     * @param localTilePath the file path for tile images of the arena under assets/tileset
     * @param spawn1 the spawn location for player 1
     * @param spawn2 the spawn location for player 2
     */
    public BattleArena(Game g,
                       String title,
                       String localTilePath,
                       Vec2 spawn1,
                       Vec2 spawn2) {
        game = g;
        this.title = title;
        tilePath = "assets/tileset/" + localTilePath + "/";

        // Player-teleporting world borders
        left = new SideBorder(this, -21);
        right = new SideBorder(this, 21);

        // Player 1
        player1 = new Player(this, 1, Characters.KNIGHT, spawn1);
        player1.setKeyBindings(KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_R);

        // Player 2
        player2 = new Player(this, 2, Characters.WIZARD, spawn2);
        player2.setKeyBindings();

        // Fill the world with tiles
        populate();

        // Change the soundtrack
        game.setSoundtrack("crime_is_everywhere.mp3");
    }

    public SideBorder getOtherSide(SideBorder thisSide) {
        return thisSide == left ? right : left;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public Color getBgColour() {
        return bgColour;
    }

    public Image getBgImage() {
        return bgImage;
    }

    public void setBackground(Color color) {
        bgColour = color;
    }

    public void setBackground(String fileName) {
        bgImage = new ImageIcon("assets/background/" + fileName).getImage();
    }

    public void setDeathZone(BattleArena w, float y) {
        if (deathZone == null) deathZone = new DeathZone(w, right.getPosition().x, y);
    }

    public abstract void populate();

    public void placeBlock(String type, float x, float y) {
        new Tile(this, tilePath + type, x, y);
    }

    public void drawHUD(GameView v, Graphics2D g) {
        int centre = Game.WIDTH / 2;
        int middle = Game.HEIGHT / 2;

        // Arena title
        if (this instanceof RoyalArena) g.setColor(Color.BLACK);
        else g.setColor(Color.WHITE);
        g.setFont(new Font("Impact", Font.PLAIN, 26));
        v.drawText(g, title, centre, 60);

        int w = 120;
        int h = 65;
        int x = 10;
        int y = 10;

        // Player positions converted from world to view coordinates
        Point2D.Float p1 = v.worldToView(player1.getPosition());
        Point2D.Float p2 = v.worldToView(player2.getPosition());

        g.setColor(new Color(255, 255, 255, 165));
        g.setFont(new Font("Bahnschrift", Font.BOLD, 15));

        // Player 1 HUD
        player1.drawStats(g, w, h, x, y);
        if (player1.getRespawns() > 0) v.drawText(g, "Player 1", p1.x, p1.y - 5);

        // Player 2 HUD
        player2.drawStats(g, w, h, v.getWidth() - (w + x), y);
        if (player2.getRespawns() > 0) v.drawText(g, "Player 2", p2.x, p2.y - 45);
    }

    public boolean isPaused() {
        return isPaused;
    }

    public boolean isEnded() {
        return isEnded;
    }

    public void togglePause(boolean roundEnded) {
        isEnded = roundEnded;
        isPaused = !isPaused;
        game.toggleSoundtrack();
        if (isPaused) stop();
        else start();
    }

/*
    public void restart() {
        BattleArena w = switch (title) {
            case "ROYAL ARENA" -> new RoyalArena(game);
            case "WILD FOREST" -> new WildForest(game);
            case "THE VOID" -> new TheVoid(game);
            case "OLD HANGAR" -> new OldHangar(game);
            default -> throw new RuntimeException("ARENA IS NULL");
        };
        game.restartArena(w);
    }
*/

    public void exit() {
        game.switchToMenu(Panels.TITLE_SCREEN);
    }
}