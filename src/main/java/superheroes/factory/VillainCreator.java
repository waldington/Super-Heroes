package superheroes.factory;

import lombok.EqualsAndHashCode;
import superheroes.model.*;
import superheroes.util.PropertiesLoader;
@EqualsAndHashCode

public class VillainCreator implements HeroCreator {

    PropertiesLoader propertiesLoader = PropertiesLoader.getInstace();

    @Override
    public AbstractHero createHero(String name, HeroeStatistics stats, TeamType type) {
        return new Villain( name, stats, type);
    }

    @Override
    public AbstractHero createHeroWithDefaultStats(String name, TeamType type) {

        String defaultAttack  = System.getProperty("confg.Villain.defaultAttack");     //wlasciwosci dla Villain;
        int attack = Integer.parseInt(defaultAttack);

        String defaultHealth  = System.getProperty("confg.Villain.defaultHealth");
        int health = Integer.parseInt(defaultHealth);

        String defaultDefense  = System.getProperty("confg.Villain.defaultDefense");
        int defense = Integer.parseInt(defaultDefense);

        return new Villain(name, new HeroeStatistics( health, attack, defense), type);
    }
}
