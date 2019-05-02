package superheroes.model;

import lombok.EqualsAndHashCode;

public class Villain extends AbstractHero{

public Villain(String name, HeroeStatistics stats, TeamType type) {
        super(name, stats, type);
    }

    @Override
    public int getPower() {
//        int powerVillain = (getStats().getAttack() + getStats().getHealth()) * getStats().getDefense();
//        return powerVillain;

        // opcja alternatywna jezeli pola w AbstractHero sa private;

        return ((stats.getAttack() + stats.getHealth()) * stats.getDefense());
    }
}
