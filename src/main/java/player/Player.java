package player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import org.jbox2d.common.Vec2;

import arenas.ArenaTimers;
import arenas.BattleArena;
import characters.Character;
import characters.Knight;
import characters.Skeleton;
import characters.Wizard;
import city.cs.engine.SolidFixture;
import city.cs.engine.Walker;

public class Player extends Walker {
	public static final int MAX_HEALTH = 100;
	public static final int MAX_RESPAWNS = 3;

	private ArenaTimers.Task attackTimer;

	private final BattleArena gameRound;
	private final int ID;
	private final Character character;
	private final Vec2 startPos;
	private KeyBindings keyBindings;
	private SolidFixture hitbox;
	private int health,
			respawns;
	private boolean isFacingRight,
			isMidAir,
			isRunning,
			isAttacking;

	/**
	 * Player constructor.
	 * <p>
	 * Creates and spawns new player in the arena.
	 * 
	 * @param w      the arena in which the player should spawn
	 * @param number the ID of the player (either 1 or 2)
	 * @param c      a character enum to bind the corresponding character object to
	 *               the player
	 * @param spawn  the spawn location for the player in the world
	 */
	public Player(BattleArena w,
			int number,
			Characters c,
			Vec2 spawn) {
		super(w);
		gameRound = w;
		ID = number;
		character = switch (c) {
			case KNIGHT -> new Knight(this);
			case SKELETON -> new Skeleton(this);
			case WIZARD -> new Wizard(this);
		};
		startPos = spawn;

		hitbox = new SolidFixture(this, character.getDefaultShape());
		setGravityScale(2);

		health = MAX_HEALTH;
		respawns = MAX_RESPAWNS;

		isFacingRight = ID == 1;
		addImage(character.getIdleImage());
		isMidAir = false;
		isAttacking = false;

		setPosition(startPos);
		addCollisionListener(new Collisions(this));
	}

	public Character getCharacter() {
		return character;
	}

	public KeyBindings getKeyBindings() {
		return keyBindings;
	}

	public void setKeyBindings(int jump, int left, int right, int attack) {
		keyBindings = new KeyBindings(jump, left, right, attack);
	}

	public void setKeyBindings() {
		keyBindings = new KeyBindings();
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getRespawns() {
		return respawns;
	}

	public boolean isFacingRight() {
		return isFacingRight;
	}

	public boolean isMidAir() {
		return isMidAir;
	}

	public boolean isAttacking() {
		return isAttacking;
	}

	public boolean canAcceptInput() {
		return respawns > 0
				&& gameRound.isRunning()
				&& !gameRound.isEnded();
	}

	public void idle() {
		stopWalking();
		isRunning = false;

		if (isAttacking)
			return;

		removeAllImages();
		if (isMidAir)
			addImage(character.getJumpImage());
		else
			addImage(character.getIdleImage());
	}

	public void jump() {
		if (!isAttacking) {
			jump(character.getJumpSpeed());
			isMidAir = true;

			removeAllImages();
			addImage(character.getJumpImage());
		}
	}

	public void land() {
		isMidAir = false;

		removeAllImages();
		if (isRunning)
			addImage(character.getRunImage());
		else
			addImage(character.getIdleImage());
	}

	public void run(int direction) {
		if (!isAttacking) {
			startWalking(character.getRunSpeed() * direction);
			isRunning = true;

			if (direction == 1)
				isFacingRight = true;
			else if (direction == -1)
				isFacingRight = false;
			else
				throw new RuntimeException("Invalid direction chosen.");

			removeAllImages();
			if (isMidAir)
				addImage(character.getJumpImage());
			else
				addImage(character.getRunImage());
		}
	}

	public void attack() {
		if (!canAcceptInput() || isMidAir || isAttacking)
			return;

		if (isRunning)
			idle();

		isAttacking = true;
		updateHitboxAndImage();

		attackTimer = gameRound.getTimers().after(
				character.getAttackDuration(), () -> {
					attackTimer = null;
					isAttacking = false;
					updateHitboxAndImage();
				});
	}

	public void updateHitboxAndImage() {
		hitbox.destroy();
		removeAllImages();
		if (isAttacking) {
			hitbox = new SolidFixture(this, character.getAttackShape());
			addImage(character.getAttackImage());
		} else {
			hitbox = new SolidFixture(this, character.getDefaultShape());
			addImage(character.getIdleImage());
		}
	}

	public void resetHitbox() {
		hitbox.destroy();
		hitbox = new SolidFixture(this, character.getDefaultShape());
	}

	public void respawn() {
		if (health > 0 || respawns == 0 || gameRound.isEnded())
			return;

		if (attackTimer != null) {
			attackTimer.cancel();
			attackTimer = null;
		}

		isAttacking = false;
		isRunning = false;
		isMidAir = false;
		stopWalking();

		if (respawns < 2) {
			respawns = 0;
			destroy();
			System.out.println("Player " + ID + " is defeated.");
			gameRound.setWinner(ID == 1 ? 2 : 1);
			gameRound.togglePause(true);
			return;
		}

		health = MAX_HEALTH;
		respawns--;
		isFacingRight = ID == 1;
		setLinearVelocity(new Vec2(0, 0));
		setPosition(startPos);
		updateHitboxAndImage();
	}

	// TODO 28/04/2023: Changeable player username
	public void drawStats(Graphics2D g, int w, int h, int x, int y) {
		// Save colour and font
		Color savedColour = g.getColor();
		Font savedFont = g.getFont();

		int arc = 10;

		// Back panel
		g.setColor(new Color(0xFFFFFF));
		g.drawRoundRect(x, y, w, h, arc, arc);
		g.setColor(new Color(255, 255, 255, 65));
		g.fillRoundRect(x, y, w, h, arc, arc);

		// Name
		g.setColor(savedColour);
		g.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		g.drawString("Player " + ID, x + 7, y + 20);

		// Health bar
		int width = w - 14;
		int height = 15;
		int xPos = x + 7;
		int yPos = y + 25;
		g.setColor(Color.RED);
		g.fillRoundRect(xPos, yPos, width, height, arc, arc);
		int factoredWidth = width * health / 100;
		g.setColor(Color.GREEN);
		g.fillRoundRect(xPos, yPos, factoredWidth, height, arc, arc);

		// Respawns
		g.setColor(savedColour);
		g.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		g.drawString("Respawns: " + respawns, x + 7, y + 55);

		// Reset to saved colour and font
		g.setColor(savedColour);
		g.setFont(savedFont);
	}
}