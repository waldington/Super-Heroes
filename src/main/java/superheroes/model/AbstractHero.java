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
        switch (type) {
            case RED:
                this.stats.increaseHealth(50);
                break;
            case BLUE:
                this.stats.increaseAttack(50);
                break;
            case GREEN:
                this.stats.increaseDefense(50);
        }

    }

    public void killHero(){
        this.isAlive = false;
    }

    public abstract int getPower ();

    public String parseToString (){
       return join (/*";",this instanceof SuperHero ? "SuperHero" : "Villain",*/
               this.getClass().getSimpleName(),
               this.name,
               Integer.toString(this.stats.getHealth()),
               Integer.toString(this.stats.getAttack()),
               Integer.toString(this.stats.getDefense()),
               this.type.toString());

       //return "SuperHero"
//                this.name,
//                Integer.toString(this.stats.getHealth()),
//                Integer.toString(this.stats.getAttack()),
//                Integer.toString(this.stats.getDefense()),
//                this.type.toString());

    }

    public String[] wyciagnie (){
        String[] split = parseToString().split(";");
       return split;
    }


}
