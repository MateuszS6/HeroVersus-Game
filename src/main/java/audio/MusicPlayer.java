package audio;

import java.util.concurrent.ExecutionException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.SwingWorker;

import game.Resources;

public final class MusicPlayer implements AutoCloseable {
	private Clip current;
	private String currentTrack;
	private String requestedTrack;
	private long requestId;
	private boolean paused;
	private boolean closed;

	public void play(String fileName) {
		if (closed || fileName.equals(requestedTrack))
			return;

		requestedTrack = fileName;
		paused = false;
		long id = ++requestId;

		if (current != null)
			current.stop();

		if (fileName.equals(currentTrack)) {
			current.loop(Clip.LOOP_CONTINUOUSLY);
			return;
		}

		new SwingWorker<Clip, Void>() {
			@Override
			protected Clip doInBackground() throws Exception {
				return load(fileName);
			}

			@Override
			protected void done() {
				Clip loaded;

				try {
					loaded = get();
				} catch (InterruptedException | ExecutionException exception) {
					if (exception instanceof InterruptedException) {
						Thread.currentThread().interrupt();
					}

					if (!closed && id == requestId) {
						requestedTrack = currentTrack;

						if (current != null && !paused) {
							current.loop(Clip.LOOP_CONTINUOUSLY);
						}

						System.err.println("Could not load music: " + fileName);
						exception.printStackTrace();
					}

					return;
				}

				if (closed || id != requestId) {
					loaded.close();
					return;
				}

				if (current != null) {
					current.close();
				}

				current = loaded;
				currentTrack = fileName;

				if (!paused) {
					current.loop(Clip.LOOP_CONTINUOUSLY);
				}
			}
		}.execute();
	}

	private static Clip load(String fileName) throws Exception {
		try (AudioInputStream source = AudioSystem.getAudioInputStream(
				Resources.url("assets/sound/music/" + fileName))) {
			AudioFormat format = source.getFormat();

			AudioFormat pcm = new AudioFormat(
					AudioFormat.Encoding.PCM_SIGNED,
					format.getSampleRate(),
					16,
					format.getChannels(),
					format.getChannels() * 2,
					format.getSampleRate(),
					false);

			try (AudioInputStream decoded = AudioSystem.getAudioInputStream(pcm, source)) {
				Clip clip = AudioSystem.getClip();

				try {
					clip.open(decoded);

					if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
						FloatControl gain = (FloatControl) clip.getControl(
								FloatControl.Type.MASTER_GAIN);

						float decibels = (float) (20 * Math.log10(0.3));

						gain.setValue(Math.max(gain.getMinimum(),
								Math.min(gain.getMaximum(), decibels)));
					}

					return clip;
				} catch (Exception exception) {
					clip.close();
					throw exception;
				}
			}
		}
	}

	public void setPaused(boolean pause) {
		if (closed || paused == pause)
			return;

		paused = pause;

		if (current == null || !currentTrack.equals(requestedTrack))
			return;

		if (paused)
			current.stop();
		else
			current.loop(Clip.LOOP_CONTINUOUSLY);
	}

	@Override
	public void close() throws Exception {
		closed = true;
		requestId++;

		if (current != null) {
			current.close();
			current = null;
		}

		currentTrack = null;
		requestedTrack = null;
	}
}
