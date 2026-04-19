package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SoundTest {

    @Test
    public void testSetMusic() {
        Sound sound = new Sound();
        assertDoesNotThrow(() -> sound.setMusic("nor.mid"));
    }

    @Test
    public void testLoadSound() {
        Sound sound = new Sound();
        sound.setMusic("nor.mid");
        assertDoesNotThrow(() -> sound.loadSound());
    }

    @Test
    public void testStopSound() {
        Sound sound = new Sound();
        sound.setMusic("nor.mid");
        sound.loadSound();
        assertDoesNotThrow(() -> sound.mystop());
    }
}