package arenas;

import game.Game;
import org.jbox2d.common.Vec2;
import world.HealthRefill;
import world.Platform;
import world.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoyalArena extends BattleArena implements ActionListener {
    public RoyalArena(Game g) {
        super(g,
                "ROYAL ARENA",
                "grasslands/tiles",
                new Vec2(-4, -8),
                new Vec2(4, -9));

        setBackground("arena_with_stage.jpg");

        setDeathZone(this, -20);

        // Stage (player spawns)
        Color transparent = new Color(0, true);
        Platform stage = new Platform(this, 7, 2, 0, -13.5f);
        stage.setFillColor(transparent);
        stage.setLineColor(transparent);

        // Hovering ball
//        HoveringBall annoyingBall = new HoveringBall(this, 3, 0, 8);

        Timer collectibleTimer = new Timer(10000, this);
        collectibleTimer.setRepeats(false);
        collectibleTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Collectible
        new HealthRefill(this, 0, 10);
    }

    @Override
    public void populate() {
        placeBlock(Tile.ROW_LEFT, -17, -7);
        placeBlock(Tile.ROW, -15, -7);
        placeBlock(Tile.ROW, -13, -7);
        placeBlock(Tile.ROW_RIGHT, -11, -7);

        placeBlock(Tile.ROW_LEFT, 11, -7);
        placeBlock(Tile.ROW, 13, -7);
        placeBlock(Tile.ROW, 15, -7);
        placeBlock(Tile.ROW_RIGHT, 17, -7);

        placeBlock(Tile.ROW_LEFT, -3, -2);
        placeBlock(Tile.ROW, -1, -2);
        placeBlock(Tile.ROW, 1, -2);
        placeBlock(Tile.ROW_RIGHT, 3, -2);

        for (int x = -19; x < -12; x += 2) placeBlock(Tile.ROW, x, 3);
        placeBlock(Tile.ROW_RIGHT, -11, 3);
        placeBlock(Tile.ROW_LEFT, 11, 3);
        for (int x = 13; x < 20; x += 2) placeBlock(Tile.ROW, x, 3);

        placeBlock(Tile.ROW_LEFT, -3, 8);
        placeBlock(Tile.ROW, -1, 8);
        placeBlock(Tile.ROW, 1, 8);
        placeBlock(Tile.ROW_RIGHT, 3, 8);
    }
}
