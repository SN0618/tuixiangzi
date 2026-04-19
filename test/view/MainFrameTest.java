package view;

import org.junit.jupiter.api.Test;
import javax.swing.*;
import static org.junit.jupiter.api.Assertions.*;

public class MainFrameTest {

    @Test
    public void testMainFrameCreation() {
        assertDoesNotThrow(() -> {
            MainFrame frame = new MainFrame();
            frame.dispose();
        });
    }
}