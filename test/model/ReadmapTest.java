package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReadmapTest {

    @Test
    public void testMapSize() {
        Readmap readmap = new Readmap(1);
        int[][] map = readmap.getmap();

        assertEquals(20, map.length, "地图高度应为20");
        assertEquals(20, map[0].length, "地图宽度应为20");
    }

    @Test
    public void testPlayerPosition() {
        Readmap readmap = new Readmap(1);
        int manX = readmap.getmanX();
        int manY = readmap.getmanY();

        assertTrue(manX >= 0 && manX < 20, "玩家X坐标有效");
        assertTrue(manY >= 0 && manY < 20, "玩家Y坐标有效");
    }

    @Test
    public void testInvalidLevel() {
        Readmap readmap = new Readmap(20);
        int[][] map = readmap.getmap();

        assertNotNull(map, "无效关卡应返回默认地图");
    }
}