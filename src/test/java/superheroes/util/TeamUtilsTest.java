package superheroes.util;

import org.junit.Before;
import org.junit.Test;

import superheroes.model.*;
import superheroes.testingutils.AbstractHeroDataBuilder;
import superheroes.testingutils.TeamDataBuilder;

import static org.junit.jupiter.api.Assertions.*;

public class TeamUtilsTest {
//    private AbstractHero weakHero;
//    private AbstractHero strongHero;
    private Team weakTeam;
    private Team strongTeam;

    @Before
    public void setUp() throws InvalidHeroTeamException {

//        standardowe stworzenie instancji AbstractHero

//       AbstractHero  weakHero = new SuperHero("weakhero", new HeroeStatistics(1, 1, 1), TeamType.RED);
//       AbstractHero strongHero = new SuperHero("strongHero", new HeroeStatistics(10, 10, 10)
//                , TeamType.BLUE);

//        AbstractHero weakHero = new AbstractHeroDataBuilder ()      // stworzenie instancji AbstractHero z uzyciem Buildera z
//                .witWeakStats()                                     //package superheroes.testingutils;
//                .buildSuperHero();
//
//        AbstractHero strongHero = new AbstractHeroDataBuilder()
//                .withStrongStats()
//                .buildSuperHero();

//        weakTeam = new Team(TeamType.RED);
//         strongTeam = new Team(TeamType.BLUE);
//
//        weakTeam.addHeroToTeam(weakHero);
//        strongTeam.addHeroToTeam(strongHero);

        weakTeam = new TeamDataBuilder()                    // stworzenie instancji teamu z wykorzystaniem
                .withWeakHeroes()
                .build();
        strongTeam = new TeamDataBuilder()
                .buildPowerfullTeam();
    }

    @Test
    public void shoudReturnTruIfFirstTeamIsStronger (){
        boolean result = TeamUtils.compareTeamPower(strongTeam, weakTeam);
        assertTrue(result);
    }

    @Test
    public void shoudReturnFalseIfFirstTeamIsWeaker () {
        boolean result = TeamUtils.compareTeamPower(weakTeam, strongTeam);
        assertFalse(result);
    }

}