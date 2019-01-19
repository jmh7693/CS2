/**
 * Language: Java
 * Author: Jason Hicks
 * Lab 4, Tank.java
 */
package heroes;
import game.Team;

/**
 * Our ruggedly reliable damage sponge class.
 */
public class Tank extends Hero{
    protected static int DAMAGE_AMOUNT = 15;
    protected static double SHIELD_DMG_MULTIPLIER = 0.9;
    protected static int TANK_HIT_POINTS = 40;
    private Team team;

    protected Tank(Team team){
        super(Heroes.getName(team, Heroes.Role.TANK), TANK_HIT_POINTS);
        this.team = team;
    }

    /**
     * deals damage to enemy
     * @param enemy the bad guy from this guys POV
     */
    public void attack(Hero enemy){
        enemy.takeDamage(DAMAGE_AMOUNT);
    }

    /**
     * gets this heroes role
     * @return the heroes role of being an awesome TANK
     */
    public Heroes.Role getRole(){
        return Heroes.Role.TANK;
    }

    /**
     * the tank mitigates the damage they are about to take by using their shield
     * @param amount the damage amount
     */
    public void takeDamage(int amount){
        double mitigatedDMG = amount * SHIELD_DMG_MULTIPLIER;
        super.takeDamage((int)mitigatedDMG);
    }

}
