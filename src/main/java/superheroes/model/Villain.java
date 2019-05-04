package superheroes.model;

public class Villain extends AbstractHero{

    public Villain(String name, HeroeStatistics stats, TeamType type) {
        super(name, stats, type);
    }

    @Override
    public int getPower() {
        return ((stats.getAttack() + stats.getHealth()) * stats.getDefense());
    }
}
