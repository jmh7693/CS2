/**
 * Language: Java
 * Author: Jason Hicks
 * Lab 09, TargetPaneObserver.java
 */
package view;

import controller.BattleShip;
import controller.Observer;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import model.Location;
import javafx.scene.control.Button;
import view.Console;
import model.Target;

import java.awt.*;

public class TargetPaneObserver extends Button implements Observer<Boolean> {

    public BattleShip battleship;
    public Location loc;

    //creates backgrounds for specific action response updates
    private final Background blue = new Background(new BackgroundFill(Color.BLUE, null, null));
    private final Background red = new Background(new BackgroundFill(Color.RED, null, null));

    /**
     * creates the TargetPaneObserver
     * @param battleship a BattleShip
     * @param loc (int row, int col)
     */
    public TargetPaneObserver(BattleShip battleship, Location loc){

        this.battleship = battleship;
        this.loc = loc;

        battleship.registerTarget(loc, this);

        //initiates the .attack when a targetPane's button is clicked on
        this.setOnAction(e-> {
            if(battleship.isMyTurn()) {
                battleship.attack(loc);
                this.setMouseTransparent(true);
                battleship.done();
            }
        });
    }

    /**
     * updates the background value of a single button when it is 'attacked'
     * @param pushValue The value that will be pushed to the observer
     */
    @Override
    public void update(Boolean pushValue) {
        Platform.runLater(()-> {
            if (!pushValue) {
                this.setBackground(blue);
            } else {
                this.setBackground(red);
            }
        });
    }
}
