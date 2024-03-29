package com.codecool.quest.logic;

import com.codecool.quest.logic.actors.monsters.Monster;
import com.codecool.quest.logic.actors.Player;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;

    private List<Monster> monsters = new ArrayList<>();

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Monster> getMonsters() {
        return this.monsters;
    }

    public void addMonster(Monster monster) {
        this.monsters.add(monster);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
