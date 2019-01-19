/**
 * Language: Java
 * Author: Jason Hicks
 * Lab 4, Healer.java
 */
package heroes;
import game.*;

/**
 * Our dependable medic healing class.
 */
public class Healer extends Hero{
    protected static int DAMAGE_AMOUNT = 10;
    protected static int HEAL_AMOUNT = 10;
    protected static int HEALER_HIT_POINTS = 35;
    private Team team;
    private Party party;

    /**
     * creates a healer
     * @param team healers team
     * @param party healers party
     */
    protected Healer(Team team, Party party){
        super(Heroes.getName(team, Heroes.Role.HEALER), HEALER_HIT_POINTS);
        this.team = team;
        this.party = party;
    }

    /**
     * a healer heals themselves, then the rest of their party.
     * then they attack the enemy with their maximum damage
     * @param enemy the enemy being attacked
     */
    public void attack(Hero enemy){
        if(this.party != null && !this.hasFallen()){
            this.heal(HEAL_AMOUNT);
            for(Hero hero: party.getHeroes()){
                if(!hero.hasFallen()){
                    hero.heal(HEAL_AMOUNT);
                }
            }
            enemy.takeDamage(DAMAGE_AMOUNT);
        }
    }

    /**
     * gets this heroes role
     * @return
     */
    public Heroes.Role getRole(){
        return Heroes.Role.HEALER;
    }

}
