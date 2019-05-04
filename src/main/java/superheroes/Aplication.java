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
    public static void main(String[] args) throws InvalidHeroTeamException{

        PropertiesLoader.getInstace().loadProperties();

        HeroCreator creatorA = new SuperHeroCreator();
        HeroCreator creatorB = new VillainCreator();

        generateHero(creatorA);
        generateHero(creatorB);

        generateDefaultHero(creatorA);
        generateDefaultHero(creatorB);

        Team teamDC = new Team(TeamType.RED);
        teamDC.addHeroToTeam(new SuperHero(
                "Superman",
                new HeroeStatistics(20, 20, 20),
                TeamType.RED));
        teamDC.addHeroToTeam(new SuperHero(
                "Batman",
                new HeroeStatistics(10, 10, 10),
                TeamType.RED));
        teamDC.addHeroToTeam(new SuperHero(
                "Aquaman",
                new HeroeStatistics(15, 20, 10),
                TeamType.RED));

        Team teamMarvel = new Team(TeamType.BLUE);
        teamMarvel.addHeroToTeam(new SuperHero(
                "Iron Man",
                new HeroeStatistics(10, 10, 20),
                TeamType.BLUE));
        teamMarvel.addHeroToTeam(new SuperHero(
                "Thor",
                new HeroeStatistics(30, 10, 20),
                TeamType.BLUE));
        teamMarvel.addHeroToTeam(new SuperHero(
                "Hulk",
                new HeroeStatistics(15, 30, 30),
                TeamType.BLUE));

        War war = new War(teamDC, teamMarvel);
        System.out.println(war.startWar());

        AbstractHeroUtils.saveHeroesToFile(teamMarvel.getHeroes(), "./MarvelHeroes");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(AbstractHeroUtils.readHeroesFromFile("./MarvelHeroes"));
    }

    public static void generateHero(HeroCreator creator) {
        AbstractHero hero = creator
                .createHero("Zenek", new HeroeStatistics(10, 5, 15), TeamType.RED);
        System.out.println(hero);
    }

    public static void generateDefaultHero(HeroCreator creator) {
        AbstractHero hero = creator.createHeroWithDefaultStats("Bartek", TeamType.BLUE);
        System.out.println(hero);
    }
}
