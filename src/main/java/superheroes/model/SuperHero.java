package superheroes.model;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode (callSuper = true)
public class SuperHero extends  AbstractHero {

    public SuperHero(String name, HeroeStatistics stats, TeamType type) {
        super(name, stats, type);
    }

    @Override
    public int getPower() {
//        int powerSuperHero = (getStats().getAttack() + getStats().getDefense()) * getStats().getHealth();
//        return powerSuperHero;

        // opcja alternatywna jezeli pola w AbstractHero sa private;
        return ((stats.getDefense() + stats.getAttack()) * stats.getHealth());
    }
}
