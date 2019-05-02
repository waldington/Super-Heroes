package superheroes.factory;

import superheroes.model.AbstractHero;
import superheroes.model.HeroeStatistics;
import superheroes.model.TeamType;
import superheroes.model.Villain;

public interface HeroCreator {

     String CONFIG = "config";
     String DEFAULT_ATTACK = "defaultAttack";
     String DEFAULT_HEALTH = "defaultHealth";
     String DEFAULT_DEFENSE = "defaultDefense";

     AbstractHero createHero (String name, HeroeStatistics stats, TeamType type);

     AbstractHero createHeroWithDefaultStats (String name, TeamType type);

}
