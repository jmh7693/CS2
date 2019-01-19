/**
 * Language: Java
 * Author: Jason Hicks
 * Lab 09, ShipStatusObserver.java
 */
package view;

import controller.BattleShip;
import controller.Observer;
import controller.ShipData;
import javafx.application.Platform;
import model.Location;
import model.ShipModel;
import model.Peg;
import model.Target;

public class ShipStatusObserver implements Observer<ShipData> {

    public BattleShip battleship;
    public Location loc;
    private ShipModel val;
    private ShipHitStatusObserver hitStats;

    public ShipStatusObserver(BattleShip battleship, Location loc){
        this.battleship = battleship;
        this.loc = loc;
    }

    @Override
    public void update(ShipData pushValue) {
        this.val = (ShipModel) pushValue;

        if(val.sunk()){
            battleship.update(val);
        }else {
                for (int i = 0; i < val.getSize(); i++) {
                    hitStats = new ShipHitStatusObserver(val.getName(),
                            val.getSize(), val.getBowLocation(), val.getOrientation());

                    this.val.register(hitStats);
                }
        }
    }
}
