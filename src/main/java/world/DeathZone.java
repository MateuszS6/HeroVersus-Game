package world;

import java.util.HashSet;
import java.util.Set;

import arenas.BattleArena;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import player.Player;

public class DeathZone extends Barrier implements CollisionListener {
	private final BattleArena arena;
	private final Set<Player> coolingDown = new HashSet<>();

	public DeathZone(BattleArena arena, float halfWidth, float y) {
		super(arena, halfWidth, 0.5f, 0, y);
		this.arena = arena;
		addCollisionListener(this);
	}

	@Override
	public void collide(CollisionEvent event) {
		if (event.getOtherBody() instanceof Player player
				&& player.canAcceptInput()
				&& coolingDown.add(player)) {
			player.setHealth(0);
			player.respawn();
			arena.getTimers().after(500, () -> coolingDown.remove(player));
		}
	}
}
