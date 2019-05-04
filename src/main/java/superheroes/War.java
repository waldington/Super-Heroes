package superheroes;

import lombok.AllArgsConstructor;
import superheroes.model.AbstractHero;
import superheroes.model.Team;

@AllArgsConstructor
public class War {

    private Team teamA;
    private Team teamB;

    public Team startWar(){
        while (teamA.isAnyheroesStillAlive() && teamB.isAnyheroesStillAlive()){
            AbstractHero heroA = teamA.getRandomAliveHero();
            AbstractHero heroB = teamB.getRandomAliveHero();
            duel(heroA, heroB);
        }
        return getWinnerTeam(teamA, teamB);
    }

    public  void duel (AbstractHero heroA, AbstractHero heroB){
        if (heroA.getPower() > heroB.getPower()){
            heroA.killHero();
            System.out.println(heroA + " won over " + heroB);
        }else if (heroA.getPower() < heroB.getPower()){
            heroB.killHero();
            System.out.println(heroB + " won over " + heroA);
        }else {
            heroA.killHero();
            heroB.killHero();
            System.out.println(heroA + " and " + heroB + " kiled each other");
        }
    }

    private Team getWinnerTeam (Team teamA, Team teamB){
        if (teamA.isAnyheroesStillAlive()){
            System.out.println("wygrala A");
            return teamA;
        }else if (teamB.isAnyheroesStillAlive()){
            System.out.println("wygrala b");
            return teamB;
        }else{
            return null;
        }
    }
}
