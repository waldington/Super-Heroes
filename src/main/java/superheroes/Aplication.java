package superheroes;

import superheroes.factory.HeroCreator;
import superheroes.factory.SuperHeroCreator;
import superheroes.factory.VillainCreator;
import superheroes.model.*;
import superheroes.util.AbstractHeroUtils;
import superheroes.util.InvalidHeroTeamException;
import superheroes.util.PropertiesLoader;
import superheroes.util.TeamUtils;

import java.io.IOException;
import java.util.List;

public class Aplication {
    public static void main(String[] args) throws InvalidHeroTeamException, IOException {
        PropertiesLoader.getInstace().loadProperties();

        HeroCreator creatorA = new SuperHeroCreator();
        generateHero(creatorA);

        HeroCreator creatorB = new SuperHeroCreator();
        generateHeroDefault(creatorB);

        HeroCreator creatorC = new VillainCreator();
        generateVillain(creatorC);

        HeroCreator creatorD = new VillainCreator();
        createVillainWithDefaultStats(creatorD);



        Team teamDC = new Team(TeamType.RED);
        teamDC.addHeroToTeam(new SuperHero("SuperMan", new HeroeStatistics(20,20,20),TeamType.RED));
        teamDC.addHeroToTeam(new SuperHero("Batman", new HeroeStatistics(10,10,10),TeamType.RED));
        teamDC.addHeroToTeam(new SuperHero("AquaMan", new HeroeStatistics(15,20,10),TeamType.RED));

       AbstractHeroUtils.saveHeroesToFile(teamDC.getHeroes(), "./heroes.txt");
//
//        Team teamMarvel = new Team(TeamType.BLUE);
//        teamMarvel.addHeroToTeam(new SuperHero("Iron Man", new HeroeStatistics(10,10,20),TeamType.BLUE));
//        teamMarvel.addHeroToTeam(new SuperHero("Thor", new HeroeStatistics(30,10,20),TeamType.BLUE));
//        teamMarvel.addHeroToTeam(new SuperHero("Hulk", new HeroeStatistics(15,30,30),TeamType.BLUE));
//
//        War war = new War();
//        war.startWar(teamDC, teamMarvel);

    }

    public static  void generateHero (HeroCreator creator){
        AbstractHero hero = creator.createHero("Zenek", new HeroeStatistics( 1,2,3), TeamType.RED);
        System.out.println(hero);

    }

    public static  void  generateHeroDefault (HeroCreator creator){
        AbstractHero hero2 = creator.createHeroWithDefaultStats("Zbyszek", TeamType.GREEN);
        System.out.println(hero2);
    }

    public  static void generateVillain (HeroCreator creatorVillain){
        AbstractHero villlain = creatorVillain.createHero("Jurek",new HeroeStatistics( 5,4,6),TeamType.RED);
        System.out.println(villlain);
    }

    public static void createVillainWithDefaultStats (HeroCreator creatorVillain){
        AbstractHero villlain2 = creatorVillain.createHeroWithDefaultStats("Marta", TeamType.GREEN);
        System.out.println(villlain2);
    }




}
