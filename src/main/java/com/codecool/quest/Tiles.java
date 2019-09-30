package com.codecool.quest;

import com.codecool.quest.logic.interfaces.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();

    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(1, 3));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("stairs", new Tile(3, 6));
        tileMap.put("player", new Tile(25, 0));
        tileMap.put("playerInArmor", new Tile(30, 0));
        tileMap.put("playerWithWeapon", new Tile(27, 0));
        tileMap.put("playerWithWeaponInArmor", new Tile(28, 0));
        tileMap.put("wizard", new Tile(24, 1));
        tileMap.put("guard", new Tile(28, 1));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("ghost", new Tile(26, 6));
        tileMap.put("golem", new Tile(30, 6));
        tileMap.put("doors", new Tile(3, 3));
        tileMap.put("doors_opened", new Tile(4, 3));
        tileMap.put("chest_closed", new Tile(8, 6));
        tileMap.put("chest_opened", new Tile(9, 6));
        tileMap.put("sword", new Tile(1, 30));
        tileMap.put("axe", new Tile(6, 29));
        tileMap.put("armor", new Tile(0, 23));
        tileMap.put("key", new Tile(16,23));
        tileMap.put("health_potion", new Tile(17,25));
        tileMap.put("power_potion", new Tile(18,25));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
