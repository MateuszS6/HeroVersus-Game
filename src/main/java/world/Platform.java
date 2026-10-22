package world;

import org.jbox2d.common.Vec2;

import arenas.BattleArena;
import city.cs.engine.BoxShape;
import city.cs.engine.SolidFixture;
import city.cs.engine.StaticBody;

public class Platform extends StaticBody {
	public Platform(BattleArena w, float halfWidth, float halfHeight, float x, float y) {
		super(w);
		SolidFixture surface = new SolidFixture(this, new BoxShape(halfWidth, halfHeight));
		surface.setFriction(70);
		setPosition(new Vec2(x, y));
	}
}
