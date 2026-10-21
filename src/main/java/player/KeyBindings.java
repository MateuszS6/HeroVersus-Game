package player;

import java.awt.event.KeyEvent;

public class KeyBindings {
    final int JUMP;
    final int LEFT;
    final int RIGHT;
    final int ATTACK;

    public KeyBindings(int jump, int left, int right, int attack) {
        JUMP = jump;
        LEFT = left;
        RIGHT = right;
        ATTACK = attack;
    }

    public KeyBindings() {
        this(KeyEvent.VK_UP, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_PERIOD);
    }
}
