package arenas;

import game.Game;
import org.jbox2d.common.Vec2;
import world.Tile;

import java.awt.*;

public class TheVoid extends BattleArena {
    // TODO: 24/04/2023
    //  Pick-ups that select random spawn location from list - spawn every set time, only when previous collected
    public TheVoid(Game g) {
        super(g,
                "THE VOID",
                "witchcraft/tiles/outlined",
                new Vec2(-15, 9),
                new Vec2(15, -7.5f));

        setBackground(Color.BLACK);

        setDeathZone(this, -20);
    }

    @Override
    public void populate() {
        placeBlock(Tile.END_TOP_LEFT, -1, 9);
        for (int x = 1; x < 6; x += 2) placeBlock(Tile.TOP, x, 9);
        placeBlock(Tile.END_TOP_RIGHT, 7, 9);

        // Player 1 spawn
        for (int x = -19; x < -12; x += 2) placeBlock(Tile.TOP, x, 4);
        placeBlock(Tile.TOP_RIGHT, -11, 4);
        placeBlock(Tile.RIGHT, -11, 2);
        placeBlock(Tile.JOIN_TOP_RIGHT, -11, 0);
        placeBlock(Tile.TOP, -9, 0);
        placeBlock(Tile.END_TOP_RIGHT, -7, 0);

        placeBlock(Tile.TOP, 19, 4);
        placeBlock(Tile.END_TOP_LEFT, 17, 4);

        placeBlock(Tile.END_TOP_LEFT, 3, -7);
        placeBlock(Tile.JOIN_TOP_LEFT, 5, -7);
        placeBlock(Tile.LEFT, 5, -5);
        placeBlock(Tile.TOP_LEFT, 5, -3);
        placeBlock(Tile.TOP, 7, -3);
        placeBlock(Tile.END_TOP_RIGHT, 9, -3);

        for (int x = -19; x < -6; x += 2) placeBlock(Tile.TOP, x, -11);
        placeBlock(Tile.END_TOP_RIGHT, -5, -11);

        // Player 2 spawn
        for (int x = 13; x < 20; x += 2) placeBlock(Tile.TOP, x, -11);
        placeBlock(Tile.END_TOP_LEFT, 11, -11);
    }
}
