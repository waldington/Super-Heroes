package superheroes.util;

import lombok.EqualsAndHashCode;
import org.junit.Test;
import superheroes.model.*;
import superheroes.testingutils.AbstractHeroDataBuilder;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
@EqualsAndHashCode
public class AbstractHeroUtilsTest {

    @Test

   public void shouldcreateSuperHeroFromString () throws InvalidHeroTeamException {
        // given
        String superHeroString = "SuperHero;name;5;10;15;RED";

        //when
        AbstractHero result =  AbstractHeroUtils.createHeroFromString (superHeroString);

       //then
        assertEquals( new SuperHero("name", new HeroeStatistics(5,10,15), TeamType.RED), result);

        }

        @Test
    public void shouldCreateVillainFromString () throws InvalidHeroTeamException {
        // given
            String villainString = "Villain;name;5;10;15;BLUE";
            // when
            AbstractHero result = AbstractHeroUtils.createHeroFromString(villainString);
            //then
            assertEquals(new Villain("name", new HeroeStatistics(5,10,15), TeamType.BLUE),result);
        }

        @Test
    public void shouldThrowExceptionIfToManyParts (){
        //given
            String invalidString = "1;2;3;4;5;6;7";
            //when & then
            assertThrows(InvalidHeroDataException.class,
                    ()-> AbstractHeroUtils.createHeroFromString(invalidString));
        }

    public void shouldThrowExceptionIfToFewParts (){
        // given
            String invalidString = "1;2;3;4;5";
            //when & then
            assertThrows(InvalidHeroTeamException.class,
                    ()-> AbstractHeroUtils.createHeroFromString(invalidString));
        }

    public void shouldThrowExceptionIfHealthParamHasWrongFormat (){

        //given
            String invalidFormatString = "SuperHero;name;a;10;15;RED";
        // when&then
            assertThrows(InvalidHeroTeamException.class,
                    ()-> AbstractHeroUtils.createHeroFromString(invalidFormatString));

        }


}
