/**
 * Language: Java
 * Author: Jason Hicks
 * Lab 4, Berserker.java
 */
package heroes;
import game.*;

/**
 * Our glass cannon damage class.
 */
public class Berserker extends Hero{
    protected static int BERSERKER_HIT_POINTS = 30;
    protected static int DAMAGE_AMOUNT = 20;
    private Team team;

    /**
     * creates a berserker
     * @param team berserkers team
     */
    protected Berserker(Team team){
        super(Heroes.getName(team, Heroes.Role.BERSERKER), BERSERKER_HIT_POINTS);
        this.team = team;
    }

    /**
     * deal maximum damage to the enemy
     * @param enemy enemy being attacked
     */
    public void attack(Hero enemy){
        enemy.takeDamage(DAMAGE_AMOUNT);
    }

    /**
     * gets this heroes role
     * @return
     */
    public Heroes.Role getRole(){
        return Heroes.Role.BERSERKER;
    }

}
