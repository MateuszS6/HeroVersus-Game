package world;

import org.jbox2d.common.Vec2;

import arenas.ArenaTimers;
import arenas.BattleArena;
import city.cs.engine.CircleShape;
import city.cs.engine.DynamicBody;

public class HoveringBall extends DynamicBody {
	private final BattleArena arena;
	private ArenaTimers.Task hoverTimer;

	public HoveringBall(BattleArena arena, float radius, float x, float y) {
		super(arena, new CircleShape(radius, x, y));
		this.arena = arena;
		setGravityScale(0);
		// SolidFixture surface = new SolidFixture(this, new CircleShape(radius, x, y));
		// surface.setRestitution(1);

		hover();
	}

	public void hover() {
		if (hoverTimer != null)
			hoverTimer.cancel();

		setLinearVelocity(new Vec2(0, -0.5f));

		hoverTimer = arena.getTimers().every(1_000,
				() -> setLinearVelocity(new Vec2(0, -getLinearVelocity().y)));
	}

	@Override
	public void destroy() {
		if (hoverTimer != null)
			hoverTimer.cancel();

		super.destroy();
	}
}
