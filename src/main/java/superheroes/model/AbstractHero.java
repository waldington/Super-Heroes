package superheroes.model;

import lombok.*;

import static java.lang.String.join;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractHero {

    protected String name;
    protected HeroeStatistics stats;
    protected TeamType type;
    protected boolean isAlive;

    public AbstractHero(String name, HeroeStatistics stats, TeamType type) {
        this.name = name;
        this.type = type;
        this.isAlive = true;
        this.stats = stats;
        int extraPoint = 50;
        switch (type) {
            case RED:
                this.stats.increaseHealth(extraPoint);
                break;
            case BLUE:
                this.stats.increaseAttack(extraPoint);
                break;
            case GREEN:
                this.stats.increaseDefense(extraPoint);
        }
    }

    public void killHero() {
        this.isAlive = false;
    }

    public String parseToString() {
        return join(";",
                this.getClass().getSimpleName(),
                this.name,
                Integer.toString(this.stats.getHealth()),
                Integer.toString(this.stats.getAttack()),
                Integer.toString(this.stats.getDefense()),
                this.type.toString());
    }

    public abstract int getPower();
}
