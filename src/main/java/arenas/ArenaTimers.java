package arenas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.World;

public final class ArenaTimers implements StepListener, AutoCloseable {
	private final World world;
	private final List<Task> tasks = new ArrayList<>();
	private boolean closed;

	public ArenaTimers(World world) {
		this.world = world;
		world.addStepListener(this);
	}

	public Task after(int milliseconds, Runnable action) {
		return schedule(milliseconds, false, action);
	}

	public Task every(int milliseconds, Runnable action) {
		return schedule(milliseconds, true, action);
	}

	private Task schedule(int milliseconds, boolean repeating, Runnable action) {
		if (closed)
			throw new IllegalStateException("Arena timers are closed");

		if (milliseconds <= 0)
			throw new IllegalArgumentException("Delay must be positive");

		Task task = new Task(milliseconds / 1000.0, repeating,
				Objects.requireNonNull(action));
		tasks.add(task);
		return task;
	}

	@Override
	public void close() throws Exception {
		if (!closed) {
			closed = true;
			tasks.clear();
			world.removeStepListener(this);
		}
	}

	@Override
	public void postStep(StepEvent e) {
		if (closed || !world.isRunning())
			return;

		for (Task task : List.copyOf(tasks)) {
			if (closed || !world.isRunning())
				break;

			if (task.cancelled)
				continue;

			task.remaining -= e.getStep();
			if (task.remaining > 0.0000001)
				continue;

			if (task.repeating)
				task.remaining += task.interval;
			else
				task.cancel();

			task.action.run();
		}
	}

	@Override
	public void preStep(StepEvent e) {
	}

	public final class Task {
		private final double interval;
		private final boolean repeating;
		private final Runnable action;
		private double remaining;
		private boolean cancelled;

		private Task(double interval, boolean repeating, Runnable action) {
			this.interval = interval;
			this.remaining = interval;
			this.repeating = repeating;
			this.action = action;
		}

		public void cancel() {
			cancelled = true;
			tasks.remove(this);
		}
	}
}
