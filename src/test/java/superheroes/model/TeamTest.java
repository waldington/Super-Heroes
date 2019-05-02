package superheroes.model;

import org.junit.Test;
import superheroes.util.InvalidHeroTeamException;

import static org.junit.jupiter.api.Assertions.*;

public class TeamTest {

    @Test
    public void shouldReturnTrueIfHeroHasTheSameTypaAsTeam () throws InvalidHeroTeamException {
        // inicjacja danych testowych
        SuperHero hero = new SuperHero("hero", new HeroeStatistics(1,1,1), TeamType.BLUE);

        Team team = new Team(TeamType.BLUE);

        // wywolanie metody ktora chcemy przetestowac

        team.addHeroToTeam(hero);

        // sprawdzenie wyniku
//        assertTrue(result);

        assertTrue(team.getHeroes().contains(hero));

    }

    @Test
    public void shouldReturnFalseIfHeroHasDifferentTypaAsTeam () {
        SuperHero hero = new SuperHero("hero", new HeroeStatistics(1,1,1), TeamType.RED);

        Team team = new Team(TeamType.BLUE);

        // team.addHeroToTeam(hero);                    podwojne obsluzenie wyjatku błąd wyjatek tylko moze byc obsluzony w assertThrows
//        assertFalse(result);

        assertThrows(InvalidHeroTeamException.class, () -> team.addHeroToTeam(hero));// jezeli spodziewamy sie  exception to musimy go
        assertFalse(team.getHeroes().contains(hero));           // musimy go obsluzyc tylko w metodzie assertThrows

    }

    @Test
    public  void shouldReturnMostPowerfullHerosAsLeader() throws InvalidHeroTeamException {
        SuperHero weakHero = new SuperHero("hero1", new HeroeStatistics(1,1,1), TeamType.BLUE);
        SuperHero powerfullHero = new SuperHero("hero2", new HeroeStatistics(10,10,10), TeamType.BLUE);

        Team team = new Team(TeamType.BLUE);
        team.addHeroToTeam(weakHero);
        team.addHeroToTeam(powerfullHero);

        AbstractHero teamLeader = team.getTeamLeader();
        assertEquals(powerfullHero, teamLeader);

    }

    @Test
    public void shouldBuffTeamOnlyOnce () throws InvalidHeroTeamException {
        SuperHero superHero = new SuperHero("hero1", new HeroeStatistics(1,1,1), TeamType.BLUE);
        Villain villain = new Villain("hero2", new HeroeStatistics(100,100,100), TeamType.BLUE);

        Team team = new Team(TeamType.BLUE);
        team.addHeroToTeam(superHero);
        team.addHeroToTeam(villain);

       team.buffTeamPower();
       team.buffTeamPower();

       assertEquals(11, superHero.getStats().getDefense());
       assertEquals(110, villain.getStats().getHealth());
    }

}