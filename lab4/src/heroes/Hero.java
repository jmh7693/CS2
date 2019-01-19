/**
 * Language: Java
 * Author: Jason Hicks
 * Lab 4, Hero.java
 */
package heroes;
import game.*;

/**
 * Represents a virtuous hero in the venerable game of storms.
 */
public abstract class Hero extends Object{
    private int hitPoints;
    private String name;
    private int maxHP;

    protected Hero(){}

    /**
     * creates a new hero
     * @param name
     * @param hitPoints
     */
    protected Hero(String name, int hitPoints){
        this.name = name;
        this.hitPoints = hitPoints;
        this.maxHP = hitPoints;
    }

    /**
     * creates a hero of a particular role for a certain team
     * @param role
     * @param team
     * @param party
     * @return
     */
    public static Hero create(Heroes.Role role, Team team, Party party){
        if(role == Heroes.Role.BERSERKER){
            return new Berserker(team);
        }
        else if(role == Heroes.Role.TANK){
            return new Tank(team);
        }
        else if(role == Heroes.Role.HEALER){
            return new Healer(team, party);
        }
        else {
            return null;
        }
    }

    /**
     * a hero attacks an enemy
     * @param enemy
     */
    public abstract void attack(Hero enemy);

    /**
     * gets the name of a hero
     * @return
     */
    public String getName(){
        return this.name;
    }

    /**
     * gets this heroes role
     * @return
     */
    public abstract Heroes.Role getRole();

    /**
     * a hero has sadly fallen when their hit points are <=0
     * @return
     */
    public boolean hasFallen(){
        if(hitPoints <= 0){
            return true;
        }
        else
            return false;
    }

    /**
     * heals an individual hero by an amount without exceeding maxHP
     * @param amount
     */
    public void heal(int amount){
        if(hitPoints + amount > maxHP){
            hitPoints = maxHP;
        }
        else{
            hitPoints = hitPoints + amount;
        }
        System.out.println(this.name + " heals " + amount + " points");
    }

    /**
     * a hero takes damage that lowers their HP
     * @param amount
     */
    public void takeDamage(int amount){
        System.out.println(this.name + " takes " + amount + " damage");
        this.hitPoints -= amount;
        if(this.hitPoints <= 0){
            this.hitPoints = 0;
        }
    }

    /**
     * returns the string representation of a hero in a specific layout
     * @return
     */
    @Override
    public String toString(){
        return name + ", " + getRole() + ", " + hitPoints + "/" + maxHP;
    }

}
