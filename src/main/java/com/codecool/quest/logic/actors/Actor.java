package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.Drawable;

public abstract class Actor implements Drawable {
    private Cell cell;
    protected int health;
    protected int attackPower;
    private String direction = "up";
    protected boolean isEnemy;
    private String[] text;
    private int counter;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);

        boolean isNextCellWall = nextCell.getType().equals(CellType.WALL);
        boolean isNextCellActor = nextCell.getActor() != null;

        if (!isNextCellWall && !isNextCellActor) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }

    }

    public void printHealth(String msg){
        System.out.println(health+msg);
    }

    public int getHealth() {
        return health;
    }

    public void changeHealth(int change){
        health += change;
    }

    public int getAttackPower(){
        return attackPower;
    }

    public String getDirection(){
        return direction;
    }

    public void changeDirection(String newDirection){
        direction = newDirection;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public void receiveAttack(int receivedDamage){
        changeHealth(-receivedDamage);
        if (health<=0){
            death();
        }
    }

    public void death(){
        getCell().setActor(null);
    }

    public void setText(String[] text){
        this.text = text;
    }

    public String getNextText(){
        //int index = (int)(Math.random() * text.length);
        String temp = text[counter];
        counter++;
        if(counter >= text.length){
            counter = 0;
        }
        return temp;
    }
}
