package com.codecool.quest;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.GameMap;
import com.codecool.quest.logic.Inventory;
import com.codecool.quest.logic.MapLoader;
import com.codecool.quest.logic.actors.monsters.Monster;
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

    private void moveMonters() {
        List<Monster> monsters = map.getMonsters();

        for (Monster monster : monsters) {
            if(!monster.isDead()) monster.move();
        }
    }

    private void onKeyPressed(KeyEvent keyEvent) {

        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().changeDirection("up");
                if(changingDirection){
                    messageLabel.setText("Attacking direction: Up");
                }else{
                    map.getPlayer().move(0, -1);
                    moveMonters();
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
                    moveMonters();
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
                    moveMonters();
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
                    moveMonters();
                    messageLabel.setText("");
                    refresh();
                }
                break;
            case E:
                if(map.getPlayer().pickItem()) {
                    messageLabel.setText(String.format("Picked a %s",map.getPlayer().getLastItemPicked()));
                }else if(map.getPlayer().getNextCell().getInteractable() != null) { //check for doors
                    if( map.getPlayer().getPlayerInventory().checkForItem("key") ){
                        map.getPlayer().getNextCell().getInteractable().Use();
                        map.getPlayer().getPlayerInventory().removeItem("key");
                        messageLabel.setText("");
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
    }


    private void createScene(BorderPane borderPane, Stage primaryStage){
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Codecool Quest");
        primaryStage.show();
    }

    private void CreateUserInterfaceSideBar(GridPane ui){
        createNameField(ui);
        formatUserInterface(ui);

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
        map.getPlayer().setName(textField.getText());
        textField.setFocusTraversable(false);
        textField.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        textField.setBorder(new Border(new BorderStroke(Color.GREY,
                BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(3))));


        Button submit = new Button("«");
        formatBtn(submit);
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
        btn.setPadding(new Insets(5));
        btn.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        btn.setBorder(new Border(new BorderStroke(Color.GREY,
                BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(3))));
    }

    private void CreateUserInterfaceBottomBar(GridPane bottomPane){
        bottomPane.setBorder(new Border(new BorderStroke(Color.SANDYBROWN,
                BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(8))));

        bottomPane.setBackground(new Background(new BackgroundFill(Color.rgb(89, 58, 68), CornerRadii.EMPTY, Insets.EMPTY)));
        bottomPane.setPadding(new Insets(10));
        bottomPane.setHgap(10);

        messageLabel.setTextFill(Color.WHITESMOKE);
        bottomPane.add(messageLabel, 0, 0);

    }

    /**
     * formats border around user interface parts
     * formats background color too
     * @param pane
     */
    private void formatUserInterface(GridPane pane){
        pane.setBorder(new Border(new BorderStroke(Color.SANDYBROWN,
                BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(8))));
        pane.setPrefWidth(200);
        pane.setBackground(new Background(new BackgroundFill(Color.rgb(89, 58, 68), CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setPadding(new Insets(10));
    }

    private void showInventory(){
        Inventory inv = map.getPlayer().getPlayerInventory();
        inventoryLabel.setText(inv.toString());
    }
}
