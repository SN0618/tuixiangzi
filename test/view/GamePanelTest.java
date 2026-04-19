package view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GamePanelTest {

    private GamePanel gamePanel;

    @BeforeEach
    public void setUp() {
        gamePanel = new GamePanel();
        gamePanel.Tuixiangzi(1);
    }

    @Test
    public void testGamePanelNotNull() {
        assertNotNull(gamePanel);
    }

    @Test
    public void testMapNotNull() {
        assertNotNull(gamePanel.map);
    }

    @Test
    public void testMaxLevel() {
        assertEquals(50, gamePanel.maxlevel());
    }

    @Test
    public void testInitialLevel() {
        assertEquals(1, gamePanel.level);
    }

    @Test
    public void testStackEmpty() {
        assertTrue(gamePanel.isMystackEmpty());
    }

    @Test
    public void testMoveUp() {
        assertDoesNotThrow(() -> gamePanel.moveup());
    }

    @Test
    public void testMoveDown() {
        assertDoesNotThrow(() -> gamePanel.movedown());
    }

    @Test
    public void testMoveLeft() {
        assertDoesNotThrow(() -> gamePanel.moveleft());
    }

    @Test
    public void testMoveRight() {
        assertDoesNotThrow(() -> gamePanel.moveright());
    }
}