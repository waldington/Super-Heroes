package superheroes.model;

import org.junit.Test;
import superheroes.testingutils.AbstractHeroDataBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AbstractHeroTest {

    @Test
    public void shouldParseSuperHeroToString (){
        // given
        AbstractHero hero = new AbstractHeroDataBuilder()
                .buildSuperHero();

        //when
        String parsedHero = hero.parseToString ();

        //then
        assertEquals("SuperHero;" + hero.getName() + ";" + hero.getStats().getHealth() + ";"
                + hero.getStats().getAttack() + ";" + hero.getStats().getDefense() + ";" + hero.getType(), parsedHero);

    }

    @Test
    public void shouldParseVillainToString (){
        // given
        AbstractHero villain = new AbstractHeroDataBuilder()
                .buildVillain();

        //when
        String result = villain.parseToString ();

        //then
        assertEquals("Villain;" + villain.getName() + ";" + villain.getStats().getHealth() + ";"
                + villain.getStats().getAttack() + ";" + villain.getStats().getDefense() + ";" + villain.getType(), result);
    }




}
