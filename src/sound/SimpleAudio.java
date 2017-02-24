package sound;

import javax.sound.sampled.*;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import static javax.sound.sampled.AudioSystem.*;

/**
 * User: gsunderam
 * Date: Jul 13, 2015
 */
public class SimpleAudio {
	public static void main(String [] args) {
		try {
			InputStream stream = new BufferedInputStream(new FileInputStream("testwav.wav"));
			AudioInputStream audioInputStream = getAudioInputStream(stream);
			DataLine.Info info = new DataLine.Info( Clip.class, audioInputStream.getFormat());
        final Clip clip = (Clip) getLine(info);
        clip.addLineListener(new LineListener() {
					public void update(LineEvent e) {
							if (e.getType() == LineEvent.Type.STOP) {
									synchronized(clip) {
											clip.notify();
									}
							}
					}
        });

        clip.open(audioInputStream);
        clip.setFramePosition(0);
        clip.start();

        synchronized (clip) {
            clip.wait();
        }

        clip.drain();
        clip.close();
    } catch(Exception e) {
        e.printStackTrace();
    }
	}
}
