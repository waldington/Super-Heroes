package superheroes.model;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode (callSuper = true)
public class SuperHero extends  AbstractHero {

    public SuperHero(String name, HeroeStatistics stats, TeamType type) {
        super(name, stats, type);
    }

    @Override
    public int getPower() {
        return ((stats.getDefense() + stats.getAttack()) * stats.getHealth());
    }
}
