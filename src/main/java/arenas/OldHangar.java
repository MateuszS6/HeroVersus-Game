package arenas;

import game.Game;
import org.jbox2d.common.Vec2;
import world.Platform;
import world.Tile;

import java.awt.*;

public class OldHangar extends BattleArena {
    /**
     * Initialise a game world.
     *
     * @param g The game containing this arena.
     */
    public OldHangar(Game g) {
        super(g,
                "OLD HANGAR",
                "central_city/tiles",
                new Vec2(-17, -7.5f),
                new Vec2(17, -7.5f));

        setBackground("hangar/bulkhead_walls_resized.png");

        // Stage (player spawns)
        Color transparent = new Color(0, true);
        Platform stage = new Platform(this, 20, 1, 0, -10.5f);
        stage.setFillColor(transparent);
        stage.setLineColor(transparent);
    }

    @Override
    public void populate() {}
}
