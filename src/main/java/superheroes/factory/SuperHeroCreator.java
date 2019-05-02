package superheroes.factory;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import superheroes.model.*;
import superheroes.util.PropertiesLoader;
@ToString
@EqualsAndHashCode
public class SuperHeroCreator implements HeroCreator {

    private  static final  String SUPER_HERO = "superHero";

    PropertiesLoader propertiesLoader = PropertiesLoader.getInstace();

    @Override
    public AbstractHero createHero(String name, HeroeStatistics stats, TeamType type) {
        return new SuperHero(name, stats, type);
    }

    @Override
    public AbstractHero createHeroWithDefaultStats(String name, TeamType type) {        // wczytywanie z pliku application.properties                                                                           //wlasciwosci dla SuperHero;

       int attack = propertiesLoader.getPropertyValue("confg.superHero.defaultAttack");  //alternatywny zapis w jednej lini
                                                                                            //z wykorzystaniem metody getPropertyValue
        // zapis z wykorzystaniem metody join z Klasy String
        String key = String.join(".", CONFIG, SUPER_HERO, DEFAULT_HEALTH);

        String defaultHealth  = System.getProperty("confg.superHero.defaultHealth");        // jeszce inny zapis :D
//        int health = propertiesLoader.getPropertyValue(String.join(".", CONFIG, SUPER_HERO, DEFAULT_HEALTH));

        int health = propertiesLoader.getPropertyValue("confg.superHero.defaultHealth");

        String defaultDefense  = System.getProperty("confg.superHero.defaultDefense");
        int defense = Integer.parseInt(defaultDefense);

        return new SuperHero(name, new HeroeStatistics( health, attack, defense), type);
    }
}
