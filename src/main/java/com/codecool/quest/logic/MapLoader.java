package com.codecool.quest.logic;

import com.codecool.quest.logic.actors.NPC;
import com.codecool.quest.logic.actors.NPCType;
import com.codecool.quest.logic.actors.monsters.Ghost;
import com.codecool.quest.logic.actors.monsters.Golem;
import com.codecool.quest.logic.actors.Player;
import com.codecool.quest.logic.actors.monsters.Skeleton;
import com.codecool.quest.logic.items.Sword;
import com.codecool.quest.logic.items.Key;
import com.codecool.quest.logic.interactable.Doors;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    private static GameMap map;

    public static GameMap getCurrentMap() {
        return MapLoader.map;
    }

    public static GameMap loadMap() {
        InputStream is = MapLoader.class.getResourceAsStream("/map.txt");
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            map.addMonster(new Skeleton(cell));
                            break;
                        case 'o':
                            cell.setType(CellType.FLOOR);
                            map.addMonster(new Golem(cell));
                            break;
                        case 'g':
                            cell.setType(CellType.FLOOR);
                            map.addMonster(new Ghost(cell));
                            break;
                        case '%':
                            cell.setType(CellType.DOORS);
                            new Doors(cell);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'W':
                            cell.setType(CellType.FLOOR);
                            new NPC(cell, NPCType.WIZARD);
                            break;
                        case 'G':
                            cell.setType(CellType.FLOOR);
                            new NPC(cell, NPCType.GUARD);
                            break;
                        case '!':
                            cell.setType(CellType.FLOOR);
                            new Sword(cell);
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            new Key(cell);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }

        MapLoader.map = map;

        return map;
    }

}
