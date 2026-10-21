package world;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import arenas.BattleArena;
import player.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeathZone extends Barrier implements CollisionListener, ActionListener {
    public DeathZone(BattleArena w, float halfWidth, float y) {
        super(w, halfWidth, 0.5f, 0, y);
        addCollisionListener(this);
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Player player) {
            removeAllCollisionListeners();
            player.setHealth(0);
            player.respawn();

            Timer timer = new Timer(500, this);
            timer.setRepeats(false);
            timer.start();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        addCollisionListener(this);
    }
}
