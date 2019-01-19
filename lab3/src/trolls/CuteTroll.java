package trolls;

import goats.IGoat;

public class CuteTroll implements ITroll {

    int happinessLvl = 0;
    int happinessMax;
    boolean isActive = true;

    public CuteTroll(int happinessMax){
        this.happinessMax = happinessMax;
    }

    //function holds an interaction string that occurs when true
    public void interact(IGoat goat){
        if (isActive)
            System.out.println(goat.toString() + " returns to the back of the line.");
        else
            finished(goat);
    }

    //function controls the changing happiness levels
    public void adjustPower(int pwrAdj){
        happinessLvl += pwrAdj;
        if (happinessLvl > happinessMax)
            isActive = false;
    }

    //function holds an interaction string that occurs when true
    public void finished(IGoat goat){
        System.out.println("The troll fell asleep after petting." + goat.toString());
    }

    //returns a found boolean value
    public boolean isActive(){
        return isActive;
    }
}
