package world;

import org.jbox2d.common.Vec2;

import arenas.BattleArena;
import city.cs.engine.BoxShape;
import city.cs.engine.StaticBody;

public class Barrier extends StaticBody {
	public Barrier(BattleArena w, float halfWidth, float halfHeight, float x, float y) {
		super(w, new BoxShape(halfWidth, halfHeight));
		setPosition(new Vec2(x, y));
	}
}
