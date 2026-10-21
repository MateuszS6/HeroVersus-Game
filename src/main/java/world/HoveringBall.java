package world;

import city.cs.engine.*;
import arenas.BattleArena;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HoveringBall extends DynamicBody implements ActionListener {
    public HoveringBall(BattleArena w, float radius, float x, float y) {
        super(w, new CircleShape(radius, x, y));
        setGravityScale(0);
        SolidFixture surface = new SolidFixture(this, new CircleShape(radius, x, y));
        // surface.setRestitution(1);

        hover();
    }

    public void hover() {
        Timer timer = new Timer(1000, this);
        // timer.setInitialDelay(5000);
        timer.start();
        setLinearVelocity(new Vec2(0, -0.5f));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setLinearVelocity(new Vec2(0, -getLinearVelocity().y));
    }
}
