package world;

import city.cs.engine.BodyImage;
import arenas.BattleArena;
import game.GameView;

public class Tile extends Platform {
    public static final String TOP = "top.png";
    public static final String LEFT = "left.png";
    public static final String RIGHT = "right.png";
    public static final String BOTTOM = "bottom.png";
    public static final String UNDER = "under.png";
    public static final String TOP_LEFT = "top_left.png";
    public static final String TOP_RIGHT = "top_right.png";
    public static final String BOTTOM_LEFT = "bottom_left.png";
    public static final String BOTTOM_RIGHT = "bottom_right.png";
    public static final String END_TOP_LEFT = "end_top_left.png";
    public static final String END_TOP_RIGHT = "end_top_right.png";
    public static final String JOIN_TOP_LEFT = "join_top_left.png";
    public static final String JOIN_TOP_RIGHT = "join_top_right.png";
    public static final String JOIN_BOTTOM_LEFT = "join_bottom_left.png";
    public static final String JOIN_BOTTOM_RIGHT = "join_bottom_right.png";
    public static final String ROW = "row.png";
    public static final String ROW_LEFT = "row_left.png";
    public static final String ROW_RIGHT = "row_right.png";
    public static final String ROW_TOP_LEFT = "row_top_left.png";
    public static final String ROW_TOP_RIGHT = "row_top_right.png";
    public static final String ROW_BOTTOM_LEFT = "row_bottom_left.png";
    public static final String ROW_BOTTOM_RIGHT = "row_bottom_right.png";

    public Tile(BattleArena w, String type, float x, float y) {
        super(w, GameView.GRID, GameView.GRID, x, y);
        addImage(new BodyImage(type, GameView.GRID * 2));
    }
}
