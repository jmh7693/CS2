/**
 * Language: Java
 * Author: Jason Hicks
 * Lab 4, HeroParty.java
 */
package game;
import heroes.*;
import java.util.*;

/**
 * A party is a collection of non-fallen allies that represent a team.
 */
public class HeroParty extends Object implements Party{
    private ArrayList<Hero> partyLst = new ArrayList<>();
    private Team team;

    /**
     * creates a party
     * @param team
     * @param seed
     */
    public HeroParty(Team team, int seed){
        this.team = team;

        if(team == Team.LION) {
            this.addHero(Hero.create(Heroes.Role.BERSERKER, team, this));
            this.addHero(Hero.create(Heroes.Role.HEALER, team, this));
            this.addHero(Hero.create(Heroes.Role.TANK, team, this));
        }
        else if(team == Team.DRAGON){
            this.addHero(Hero.create(Heroes.Role.BERSERKER, team, this));
            this.addHero(Hero.create(Heroes.Role.HEALER, team, this));
            this.addHero(Hero.create(Heroes.Role.TANK, team, this));
        }
        Collections.shuffle(this.getHeroes(), new Random(seed));
    }

    /**
     * adds a hero to the back of the collection
     * @param hero the new hero
     */
    public void addHero(Hero hero){
        partyLst.add(hero);
    }

    /**
     * gets the team which the party is affiliated with
     * @return
     */
    public Team getTeam(){
        return this.team;
    }

    /**
     * get all the undefeated heroes in the party
     * @return
     */
    public List<Hero> getHeroes(){
        ArrayList<Hero> currentHeroes = new ArrayList<>();

        if(this.getTeam() == Team.LION){
            for(Hero h : partyLst){
                currentHeroes.add(h);
            }
        }else if (this.getTeam() == Team.DRAGON){
            for(Hero h : partyLst){
                currentHeroes.add(h);
            }
        }else {
            return null;
        }

        return currentHeroes;
    }

    /**
     * get the number of the non-fallen heroes
     * @return
     */
    public int numHeroes(){
        return partyLst.size();
    }

    /**
     * removes the first hero in the collection
     * @return
     */
    public Hero removeHero(){
        return partyLst.remove(0);
    }

    /**
     * returns a string representation of the party
     * @return
     */
    @Override
    public String toString(){
        String partyStr = "";

        if(this.getTeam() == Team.LION){
            partyStr = "LION:\n";
        }
        else if(this.getTeam() == Team.DRAGON){
            partyStr = "DRAGON:\n";
        }
        for(int i = 0; i < partyLst.size(); i++){
            partyStr += partyLst.get(i).toString() + "\n";
        }
        return partyStr;
    }

}
