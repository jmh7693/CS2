/**
 * Language: Java
 * Author: Jason Hicks
 * Lab 09, GameBoard.java
 */
package view;

import controller.BattleShip;
import controller.Observer;
import controller.ShipData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.geometry.Insets;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import model.Location;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The provided javadocs only includes items that are
 * inherited from Application. All of which you will
 * not need to implement except for start which is
 * already provided below. Please replace this comment
 * block with something useful when you create your
 * implementation.
 */
public class GameBoard extends Application implements Observer<ShipData>{

    public int cols;
    public int rows;
    public Location loc;
    public BattleShip battleship;
    private GridPane grid; //not used

    public Label consoleLabel;
    public TargetPaneObserver target;

    public void start(Stage stage) {

        VBox vBox = new VBox();
        Scene scene = new Scene(vBox);
        GridPane targetPane = new GridPane();
        GridPane playerPane = new GridPane();
        consoleLabel = new Label();

        targetPane.setPadding(new Insets(10,100,0,100));
        playerPane.setPadding(new Insets(0,100,0,100));
        targetPane.setPrefHeight(375);
        targetPane.setPrefWidth(400);
        playerPane.setPrefHeight(375);
        playerPane.setPrefWidth(400);

        Parameters params = getParameters();
        List<String> args = params.getRaw();

        //creates the console being written out to
        Console console = new Console(consoleLabel);
        battleship = new BattleShip(console);

        battleship.addShips(this);

        //creates gridpane of buttons for targetPane
        for(int cols = 0; cols < BattleShip.NUM_COLS; cols++){
            for(int rows = 0; rows < BattleShip.NUM_ROWS; rows++){

                loc = new Location(rows, cols);
                target = new TargetPaneObserver(battleship, loc);
                //battleship.registerTarget(loc, target);
                target.setPrefHeight(30);
                target.setPrefWidth(35);

                targetPane.add(target, cols, rows);
                //setConstraints(targetPane);
            }
        }

        //creates gridpane of buttons for playerPane
        for(int cols = 0; cols < BattleShip.NUM_COLS; cols++){
            for(int rows = 0; rows < BattleShip.NUM_ROWS; rows++){

                loc = new Location(rows, cols);
                target = new TargetPaneObserver(battleship, loc);
                //battleship.registerTarget(loc, target);
                target.setPrefHeight(30);
                target.setPrefWidth(35);

                playerPane.add(target, cols, rows);
                //setConstraints(playerPane);
            }
        }

        targetPane.setGridLinesVisible(true);
        playerPane.setGridLinesVisible(true);

        vBox.getChildren().addAll(targetPane, playerPane, consoleLabel);
        stage.setScene(scene);
        stage.setTitle("BATTLESHIP");
        //stage.sizeToScene();
        stage.setHeight(800);
        stage.setWidth(500);
        stage.show();
    }

    //not used
    private void setConstraints(GridPane grid){
        for(int c=0; c<10; c++){
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(10);
            grid.getColumnConstraints().add(cc);
        }
        for(int r=0; r<10; r++){
            RowConstraints rc = new RowConstraints();
            rc.setPercentHeight(10);
            grid.getRowConstraints().add(rc);
        }
    }

    public static void main(String[] args) {
        Application.launch(view.GameBoard.class, args);
    }

    @Override
    public void update(ShipData pushValue) {

    }
}
