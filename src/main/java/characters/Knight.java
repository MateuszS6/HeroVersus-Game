package characters;

import city.cs.engine.PolygonShape;
import player.Player;

public final class Knight extends Character {
    public Knight(Player p) {
        super(p,
                new PolygonShape(
                        -0.1f, -0.1f,
                        1.45f, -2.05f,
                        0.7f, -4.0f,
                        -0.8f, -4.0f,
                        -1.6f, -2.05f
                ),
                new PolygonShape(
                        0.72f, -4.0f,
                        -1.68f, -4.0f,
                        -4.06f, -3.56f,
                        -5.52f, -1.66f,
                        -3.12f, 0.52f,
                        0.0f, -0.44f,
                        1.32f, -2.26f
                ),
                new PolygonShape(
                        -0.72f, -4.0f,
                        1.8f, -4.0f,
                        4.46f, -3.68f,
                        5.4f, -1.78f,
                        2.64f, 0.5f,
                        -0.1f, -0.34f,
                        -1.2f, -2.46f
                ),
                "assets/character/FreeKnight/Colour1/NoOutline/120x80_gifs/",
                "left_idle.gif",
                "__Idle.gif",
                "left_jump.gif",
                "__Jump.gif",
                "left_run.gif",
                "__Run.gif",
                "left_attack.gif",
                "__AttackCombo2hit.gif",
                8,
                10,
                15,
                500,
                20);
    }
}