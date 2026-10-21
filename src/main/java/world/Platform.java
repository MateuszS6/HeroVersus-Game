package world;

import city.cs.engine.*;
import arenas.BattleArena;
import org.jbox2d.common.Vec2;

public class Platform extends StaticBody {
    public Platform(BattleArena w, float halfWidth, float halfHeight, float x, float y) {
        super(w);
        SolidFixture surface = new SolidFixture(this, new BoxShape(halfWidth, halfHeight));
        surface.setFriction(70);
        setPosition(new Vec2(x, y));
    }
}
