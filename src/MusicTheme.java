import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
public class MusicTheme {


        private Clip clip;

        // Inicia e toca a música em loop
        public void play(String filePath) {
            try {
                File musicFile = new File(filePath);
                AudioInputStream audio = AudioSystem.getAudioInputStream(musicFile);

                clip = AudioSystem.getClip();
                clip.open(audio);
                clip.loop(Clip.LOOP_CONTINUOUSLY); // toca em loop
                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }

        public void stop() {
            if (clip != null) clip.stop();
        }
    }


