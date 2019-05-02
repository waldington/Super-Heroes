package superheroes.testingutils;

import superheroes.model.*;

public class AbstractHeroDataBuilder {

    private String name;
    private HeroeStatistics stats;
    private TeamType type;

    public AbstractHeroDataBuilder (){
        name = "default hero";
        stats = new HeroeStatistics();
        type = TeamType.BLUE;
    }

    public AbstractHeroDataBuilder withName (String name){
        this.name = name;
        return this;
    }
    public AbstractHeroDataBuilder withStats (HeroeStatistics stats){
        this.stats = stats;
        return this;
    }
    public  AbstractHeroDataBuilder withType (TeamType type){
        this.type = type;
        return this;

    }

    public AbstractHero buildSuperHero (){
        return new SuperHero( name, stats, type);
    }

    public AbstractHero buildVillain (){
        return  new Villain(name, stats, type);
    }

    public AbstractHeroDataBuilder witWeakStats (){
        return this.withStats(new HeroeStatistics(1,1,1));
    }

    public AbstractHeroDataBuilder withStrongStats (){
        return this.withStats(new HeroeStatistics(10,10,10));
    }
}
