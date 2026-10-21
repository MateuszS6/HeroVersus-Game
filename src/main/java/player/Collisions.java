package player;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import arenas.BattleArena;
import world.HealthRefill;
import world.Platform;

public class Collisions implements CollisionListener {
    private final Player player;
    private final BattleArena round;

    public Collisions(Player player) {
        this.player = player;
        round = (BattleArena) this.player.getWorld();
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Platform platform) {
            // if (platform.getPosition().y < player.getPosition().y - player.getCharacter().getHeight() * 0.5)
                if (player.isMidAir()) player.land();
        }

        if (e.getOtherBody() instanceof Player otherPlayer) {
            if (otherPlayer.isAttacking()) {
                player.setHealth(player.getHealth() - otherPlayer.getCharacter().getAttackDamage());
//                System.out.println("Attacked player health: " + player.getHealth());
                otherPlayer.resetHitbox();

                player.respawn();
            }
        }

        if (e.getOtherBody() instanceof HealthRefill refill) if (player.getHealth() < Player.MAX_HEALTH) {
            player.setHealth(Player.MAX_HEALTH);
            refill.destroy();
        }
    }
}
