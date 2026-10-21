package player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerController implements KeyListener {
    private final Player player;
    private int key;

    /** Initialise the game controller. */
    public PlayerController(Player player) {
        this.player = player;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        key = e.getKeyCode();
        if (key == player.getKeyBindings().JUMP) player.jump();
        if (key == player.getKeyBindings().LEFT) player.run(-1);
        if (key == player.getKeyBindings().RIGHT) player.run(1);
        if (key == player.getKeyBindings().ATTACK) player.attack();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        key = e.getKeyCode();
        if (key == player.getKeyBindings().LEFT ||
                key == player.getKeyBindings().RIGHT) player.idle();
    }
}