package world;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import arenas.BattleArena;
import org.jbox2d.common.Vec2;
import player.Player;

public class SideBorder extends Barrier implements CollisionListener {
    private final BattleArena arena;

    public SideBorder(BattleArena w, float x) {
        super(w, 0.5f, 20, x, 0);
        arena = w;
        addCollisionListener(this);
    }


    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Player player) {
            float xPos = (arena.getOtherSide(this).getPosition().x) * 0.88f;
            float yPos = player.getPosition().y;
            Vec2 velocity = e.getVelocity();
            if (!player.isAttacking()) {
                player.setPosition(new Vec2(xPos, yPos));
                player.setLinearVelocity(velocity);
            }
        }
    }
}
