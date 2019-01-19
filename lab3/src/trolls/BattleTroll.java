package trolls;

import goats.IGoat;

public class BattleTroll implements ITroll {

    int healthLvl = 100;
    boolean isActive = true;

    public BattleTroll(int healthLvl){
        this.healthLvl = healthLvl;
    }

    //function holds an interaction string that occurs when true
    public void interact(IGoat goat){
        if (isActive)
            System.out.println("The troll eats " + goat.toString());
        else
            finished(goat);
    }

    //changes the health levels in the troll
    public void adjustPower(int pwrAdj){
        healthLvl -= pwrAdj;
        if (healthLvl <= 0)
            isActive = false;
    }

    //function holds an interaction string that occurs when true
    public void finished(IGoat goat){
        System.out.println("The troll is vanquished by" + goat.toString());
    }

    public boolean isActive(){
        return isActive;
    }
}
