package world;

import city.cs.engine.CircleShape;
import city.cs.engine.StaticBody;
import arenas.BattleArena;

import java.awt.*;

public class HealthRefill extends StaticBody {
    public HealthRefill(BattleArena w, float x, float y) {
        super(w, new CircleShape(0.5f, x, y));
        this.setFillColor(Color.BLUE);
        this.setLineColor(new Color(0, true));
    }
}
