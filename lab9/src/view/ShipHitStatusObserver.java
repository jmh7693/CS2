/**
 * Language: Java
 * Author: Jason Hicks
 * Lab 09, ShipHitStatusObserver.java
 */
package view;

import controller.BattleShip;
import controller.Observer;
import model.Location;
import model.ShipModel;

public class ShipHitStatusObserver extends ShipModel implements Observer<Integer> {

    public Location hitRegion;

    public ShipHitStatusObserver(String name, int shipSize, Location bow, Orientation orient){
        super(name, shipSize, bow, orient);

    }

    @Override
    public void update(Integer pushValue) {
        if(this.orientation == Orientation.VERTICAL){
            hitRegion = new Location(bow.getRow() + pushValue, bow.getCol());


        }else{
            hitRegion = new Location(bow.getRow(), bow.getCol() + pushValue);

        }
    }
}
