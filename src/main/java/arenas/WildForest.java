package arenas;

import game.Game;
import org.jbox2d.common.Vec2;
import world.Tile;

public class WildForest extends BattleArena {
    public WildForest(Game g) {
        super(g,
                "WILD FOREST",
                "village/tiles",
                new Vec2(-17, -7),
                new Vec2(17, -8));

        setBackground("forest/forest_resized.png");
    }

    @Override
    public void populate() {
        for (int x = -19; x < 20; x += 2) {
            placeBlock(Tile.UNDER, x, -14);
            // Player spawns
            if (x < -5 || x > 5) placeBlock(Tile.TOP, x, -12);
        }
        placeBlock(Tile.JOIN_TOP_LEFT, -5, -12);
        for (int x = -3; x < 4; x += 2) placeBlock(Tile.UNDER, x, -12);
        placeBlock(Tile.JOIN_TOP_RIGHT, 5, -12);
        placeBlock(Tile.LEFT, -5, -10);
        for (int x = -3; x < 4; x += 2) placeBlock(Tile.UNDER, x, -10);
        placeBlock(Tile.RIGHT, 5, -10);
        placeBlock(Tile.TOP_LEFT, -5, -8);
        for (int x = -3; x < 4; x += 2) placeBlock(Tile.TOP, x, -8);
        placeBlock(Tile.TOP_RIGHT, 5, -8);

        for (int x = -19; x < -14; x += 2) placeBlock(Tile.ROW, x, -4);
        placeBlock(Tile.ROW_RIGHT, -13, -4);
        placeBlock(Tile.ROW_LEFT, 13, -4);
        for (int x = 15; x < 20; x += 2) placeBlock(Tile.ROW, x, -4);

        placeBlock(Tile.ROW_LEFT, 3, 0);
        placeBlock(Tile.ROW_RIGHT, 5, 0);

        placeBlock(Tile.ROW_LEFT, -11, 4);
        placeBlock(Tile.ROW, -9, 4);
        placeBlock(Tile.ROW, -7, 4);
        placeBlock(Tile.ROW_RIGHT, -5, 4);

        placeBlock(Tile.ROW_LEFT, 1, 10);
        placeBlock(Tile.ROW, 3, 10);
        placeBlock(Tile.ROW, 5, 10);
        placeBlock(Tile.ROW, 7, 10);
        placeBlock(Tile.ROW_TOP_RIGHT, 9, 10);
        placeBlock(Tile.ROW_BOTTOM_LEFT, 9, 8);
        placeBlock(Tile.ROW, 11, 8);
        placeBlock(Tile.ROW, 13, 8);
        placeBlock(Tile.ROW, 15, 8);
        placeBlock(Tile.ROW_RIGHT, 17, 8);
    }
}
