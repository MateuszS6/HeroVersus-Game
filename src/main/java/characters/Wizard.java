package characters;

import city.cs.engine.BoxShape;
import city.cs.engine.PolygonShape;
import player.Player;

public final class Wizard extends Character {
    public Wizard(Player p) {
        super(p,
                new PolygonShape(
                        -0.75f, -2.2f,
                        0.75f, -2.2f,
                        1.5f, 0.2f,
                        0.0f, 1.75f,
                        -1.5f, 0.2f
                ),
                new PolygonShape(
                        0.75f, -2.2f,
                        -1.9f, -2.2f,
                        -5.6f, -0.55f,
                        -6.0f, 2.2f,
                        -4.0f, 4.4f,
                        -0.1f, 4.4f,
                        1.75f, 1.6f
                ),
                new PolygonShape(
                        -0.75f, -2.2f,
                        1.9f, -2.2f,
                        5.6f, -0.55f,
                        6.0f, 2.2f,
                        4.0f, 4.4f,
                        0.1f, 4.4f,
                        -1.75f, 1.6f
                ),
                "assets/character/Wizard/gif/",
                "l_idle.gif",
                "r_idle.gif",
                "l_jump.gif",
                "r_jump.gif",
                "l_run.gif",
                "r_run.gif",
                "l_attack.gif",
                "r_attack.gif",
                9,
                10,
                15,
                800,
                25);
    }
}