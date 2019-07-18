package com.codecool.quest;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.GameMap;
import com.codecool.quest.logic.Inventory;
import com.codecool.quest.logic.MapLoader;
import com.codecool.quest.logic.actors.monsters.Monster;
import com.codecool.quest.logic.items.Item;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {
    GameMap map = MapLoader.loadMap();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    private Label healthLabelText = new Label("Health: ");
    private Label inventoryLabelText = new Label("»»»INVENTORY«««");
    Label healthLabel = new Label();
    Label inventoryLabel = new Label();
    Label messageLabel = new Label();

    private boolean changingDirection = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        GridPane bottomPane = new GridPane();

        CreateUserInterfaceSideBar(ui);
        CreateUserInterfaceBottomBar(bottomPane);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(canvas);
        borderPane.setRight(ui);
        borderPane.setBottom(bottomPane);

        showInventory();
        createScene(borderPane, primaryStage);
    }

    private void moveMonsters() {
        List<Monster> monsters = map.getMonsters();

        for (Monster monster : monsters) {
            if(!monster.isDead()) monster.move();
        }
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        if(map.getPlayer().getHealth() > 0)
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().changeDirection("up");
                if(changingDirection){
                    messageLabel.setText("Attacking direction: Up");
                }else{
                    map.getPlayer().move(0, -1);
                    //moveMonsters();
                    messageLabel.setText("");
                    refresh();
                }
                break;
            case DOWN:
                map.getPlayer().changeDirection("down");
                if(changingDirection){
                    messageLabel.setText("Attacking direction: Down");
                }else{
                    map.getPlayer().move(0, 1);
                    //moveMonsters();
                    messageLabel.setText("");
                    refresh();
                }
                break;
            case LEFT:
                map.getPlayer().changeDirection("left");
                if(changingDirection){
                    messageLabel.setText("Attacking direction: Left");
                }else{
                    map.getPlayer().move(-1, 0);
                    //moveMonsters();
                    messageLabel.setText("");
                    refresh();
                }
                break;
            case RIGHT:
                map.getPlayer().changeDirection("right");
                if(changingDirection){
                    messageLabel.setText("Attacking direction: Right");
                }else {
                    map.getPlayer().move(1, 0);
                    //moveMonsters();
                    messageLabel.setText("");
                    refresh();
                }
                break;
            case E:
                if(map.getPlayer().pickItem()) {
                    messageLabel.setText(String.format("Picked a %s",map.getPlayer().getPlayerInventory().getLastItem()));
                }else if(map.getPlayer().getNextCell().getInteractable() != null) { //check for doors/chests
                    if(map.getPlayer().getNextCell().getInteractable().needsKey()) {
                        if (map.getPlayer().getPlayerInventory().checkForItem("key")) {
                            map.getPlayer().getNextCell().getInteractable().Use();
                            map.getPlayer().getPlayerInventory().removeItem("key");
                            messageLabel.setText("");
                        }
                    }else{
                        map.getPlayer().getNextCell().getInteractable().Use();
                        Item found = map.getPlayer().getNextCell().getInteractable().searchForItems();
                        if(found != null) {
                            map.getPlayer().getPlayerInventory().addItem(found);
                            messageLabel.setText(String.format("Found a %s", found));
                        }else{
                            messageLabel.setText("Found nothing.");
                        }
                    }
                }else{
                    String message = map.getPlayer().talk();
                    messageLabel.setText(message);
                }
                refresh();
                break;
            case R:
                if(!changingDirection){
                    changingDirection = true;
                    messageLabel.setText("Choose attack direction and press R to attack");
                }else{
                    String message = map.getPlayer().attack();
                    messageLabel.setText(message);
                    changingDirection = false;
                    refresh();
                }
                break;
        }
    }

    private void refresh() {
        moveMonsters();
        showInventory();
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else if(cell.getItem() != null){
                    Tiles.drawTile(context, cell.getItem(), x, y);
                } else if(cell.getInteractable() != null){
                    Tiles.drawTile(context, cell.getInteractable(), x, y);
                }
                else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
        showInventory();
        if(map.getPlayer().getHealth() <= 0){
            messageLabel.setText("YOU ARE DEAD!!");
        }
    }


    private void createScene(BorderPane borderPane, Stage primaryStage){
        Scene scene = new Scene(borderPane);
        scene.getStylesheets().add("style.css");

        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Codecool Quest");
        primaryStage.show();
    }

    private void CreateUserInterfaceSideBar(GridPane ui){
        createNameField(ui);
        ui.getStyleClass().add("ui-pane");

        healthLabelText.setTextFill(Color.INDIANRED);
        healthLabel.setTextFill(Color.WHITESMOKE);
        ui.add(healthLabelText, 0, 4);
        ui.add(healthLabel, 1, 4);

        inventoryLabelText.setTextFill(Color.INDIANRED);
        inventoryLabel.setTextFill(Color.LIGHTGOLDENRODYELLOW);
        ui.add(inventoryLabelText, 0, 6);
        ui.add(inventoryLabel, 0, 7);
        Label lab = new Label("»»»»»»»»-«««««««");
        lab.setTextFill(Color.INDIANRED);
        ui.add(lab, 0, 20);
    }

    private void createNameField(GridPane ui) {
        Label label1 = new Label("Name:");
        label1.setTextFill(Color.INDIANRED);
        TextField textField = new TextField ();
        textField.setText("Wojo69");
        map.getPlayer().setName(textField.getText()); //set name as default
        textField.setFocusTraversable(false);
        textField.getStyleClass().add("name-field");



        Button submit = new Button("«");
        submit.getStyleClass().add("btn");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                String name = textField.getText();
                map.getPlayer().setName(name);
            }
        });

        ui.add(label1, 0, 0);
        ui.add(textField, 0, 2);
        ui.add(submit, 1, 2);
    }

    private void formatBtn(Button btn){
        btn.getStyleClass().add("btn");
    }

    private void CreateUserInterfaceBottomBar(GridPane bottomPane){
        bottomPane.getStyleClass().add("ui-pane");
        bottomPane.setHgap(10);

        messageLabel.setTextFill(Color.WHITESMOKE);
        bottomPane.add(messageLabel, 0, 0);
    }

    private void showInventory(){
        Inventory inv = map.getPlayer().getPlayerInventory();
        inventoryLabel.setText(inv.toString());
    }
}
