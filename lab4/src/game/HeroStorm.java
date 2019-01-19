/**
 * Language: Java
 * Author: Jason Hicks
 * Lab 4, HeroStorm.java
 */
package game;

import heroes.Hero;

/**
 * The main class for the RPG game, Super Fantasy Hero Storm.
 */
public class HeroStorm {
    private HeroParty lionParty;
    private HeroParty dragonParty;
    private static int battleRound;

    /**
     * creates the parties and initializes the round counter
     *
     * @param dragonSeed
     * @param lionSeed
     */
    public HeroStorm(int dragonSeed, int lionSeed) {
        HeroParty lions = new HeroParty(Team.LION, lionSeed);
        HeroParty dragons = new HeroParty(Team.DRAGON, dragonSeed);
        battleRound = 1;
    }

    /**
     * the main method
     *
     * @param args
     */
    public static void main(String[] args) {
        if (args.length == 2) {
            HeroStorm heroStorm = new HeroStorm(Integer.valueOf(args[0]), Integer.valueOf(args[1]));
            while (heroStorm.lionParty.numHeroes() > 0 && heroStorm.dragonParty.numHeroes() > 0) {
                System.out.println("\nBattle # " + battleRound + "\n =======");
                System.out.println(heroStorm.lionParty.toString());
                System.out.println(heroStorm.dragonParty.toString());
                heroStorm.play();
            }
            if (heroStorm.dragonParty.numHeroes() == 0) {
                System.out.println("\nTeam Lion Wins");
            } else {
                System.out.println("\nTeam Dragon Wins");
            }
        } else
            System.out.println("Please provide correct seeds.");

    }

    /**
     * shows if a hero has fallen or not
     */
    private void fallen(Hero hero) {
        System.out.println(hero.getName() + " has fallen!");
    }


    /**
     * the game is played in the battle rounds
     */
    private void play() {
        Hero lionHero = lionParty.removeHero();
        Hero dragonHero = dragonParty.removeHero();

        if (battleRound % 2 == 1) { //dragon
            System.out.println("**** " + dragonHero.getName() + " VS. " + lionHero.getName() + "\n");
            dragonHero.attack(lionHero);
            if (!lionHero.hasFallen()) {
                lionHero.attack(dragonHero);
                lionParty.addHero(lionHero);
                if (!dragonHero.hasFallen()) {
                    dragonParty.addHero(dragonHero);
                }
                else {
                    fallen(dragonHero);
                }
            }
            else{
                fallen(lionHero);
                lionParty.addHero(dragonHero);
            }
        }else{
            System.out.println("**** " + lionHero.getName() + " VS. " + dragonHero.getName() + "\n");
            lionHero.attack(dragonHero);
            if (!dragonHero.hasFallen()) {
                dragonHero.attack(lionHero);
                dragonParty.addHero(dragonHero);
                if (!lionHero.hasFallen()) {
                    lionParty.addHero(lionHero);
                }
                else {
                    fallen(lionHero);
                }
            }
            else{
                fallen(dragonHero);
                dragonParty.addHero(lionHero);
            }
        }
        battleRound++;
    }

}
